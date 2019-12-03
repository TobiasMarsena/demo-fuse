package org.mycompany;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerAggregator implements AggregationStrategy {

	Logger logger = LoggerFactory.getLogger(CustomerAggregator.class);
	
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		logger.info("========= Hello from aggregator ==========");
		CustomerFull full = new CustomerFull(
				( (Pegawai) newExchange.getIn().getHeader("customer")), 
				( (Keterangan) newExchange.getIn().getHeader("detail")));
		newExchange.getIn().setHeader("result", full);
		
		return newExchange;
	}
	
	public void processPegawai(Exchange exchange) {
		List<Map<String, Object>> rows = exchange.getIn().getBody(List.class);
		List<Pegawai> pegawaiList = new ArrayList<>();
		for (Map<String, Object> row : rows) {
			pegawaiList.add(new Pegawai(
					((Integer) row.get("nip")).longValue(), 
					(String) row.get("name"), 
					(String) row.get("email")));
		}
		exchange.getIn().setHeader("customer", pegawaiList);
	}
	
	public void processKeterangan(Exchange exchange) {
		List<Map<String, Object>> rows = exchange.getIn().getBody(List.class);
		List<Keterangan> keteranganList = new ArrayList<>();
		for (Map<String, Object> row : rows) {
			keteranganList.add(new Keterangan(
					((Integer) row.get("nip")).longValue(),
					(String) row.get("keterangan")));
		}
		exchange.getIn().setHeader("detail", keteranganList);
	}
	
	public void createFullBody(Exchange exchange) {
		Message inbound = exchange.getIn();
		List<Pegawai> customers = (List<Pegawai>) inbound.getHeader("customer");
		List<Keterangan> details = (List<Keterangan>) inbound.getHeader("detail");
		Map<Long, Keterangan> detailMap = new HashMap<>();
		for (Keterangan customerDetail : details) {
			detailMap.put(customerDetail.getNip(), customerDetail);
		}
		List<CustomerFull> aggregatedCustomer = new ArrayList<>();
		for (Pegawai customer : customers) {
			long key = customer.getNip();
			if (detailMap.containsKey(key)) {
				aggregatedCustomer.add(new CustomerFull(customer, detailMap.get(key)));
			} else {
				aggregatedCustomer.add(new CustomerFull(customer));
			}
		}
		inbound.setBody(aggregatedCustomer);
	}
}
