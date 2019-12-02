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
import org.springframework.util.LinkedCaseInsensitiveMap;

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
	
	public void createFullBody(Exchange exchange) {
		Message inbound = exchange.getIn();
		List<LinkedCaseInsensitiveMap<Pegawai>> customers = (List<Pegawai>) inbound.getHeader("customer");
		List<LinkedCaseInsensitiveMap<Keterangan>> details = (List<Keterangan>) inbound.getHeader("detail");
		Map<Long, Keterangan> detailMap = new HashMap<>();
		for (Keterangan customerDetail : details) {
			detailMap.put(customerDetail.getNip(), customerDetail);
		}
		List<CustomerFull> aggregatedCustomer = new ArrayList<>();
		for (LinkedCaseInsensitiveMap<Pegawai> customer : customers) {
			logger.info("customer:  "+ customer.toString());
			logger.info("KeySet:  " + customer.keySet().toString());
			long key = customer.get(1).getNip();
			if (detailMap.containsKey(key)) {
				aggregatedCustomer.add(new CustomerFull(customer.get(1), detailMap.get(key)));
			} else {
				aggregatedCustomer.add(new CustomerFull(customer.get(1)));
			}
		}
		inbound.setBody(aggregatedCustomer);
	}
}
