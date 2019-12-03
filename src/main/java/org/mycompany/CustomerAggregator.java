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
	
	public void unmarshalPegawai(Exchange exchange) {
		List<Pegawai> pegawaiList = (List<Pegawai>) exchange.getIn().getBody();
		for (Pegawai pegawai : pegawaiList) {
			logger.info(pegawai.toString());
		}
		exchange.getIn().setHeader("customer", pegawaiList);
	}
	public void unmarshalKeterangan(Exchange exchange) {
		List<Keterangan> keteranganList = (List<Keterangan>) exchange.getIn().getBody();
		for (Keterangan keterangan : keteranganList) {
			logger.info(keterangan.toString());
		}
		exchange.getIn().setHeader("detail", keteranganList);
	}
	
	public void createFullBody(Exchange exchange) {
		Message inbound = exchange.getIn();
		logger.info("Create Full Body. Converting to List<Pegawai>");
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
