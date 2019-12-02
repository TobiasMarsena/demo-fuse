package org.mycompany;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	
	public void createFullBody(Exchange exchange) {
		Message inbound = exchange.getIn();
		Map<Long, Pegawai> customers = (Map<Long, Pegawai>) inbound.getHeader("customer");
		Map<Long, Keterangan> details = (Map<Long, Keterangan>) inbound.getHeader("detail");
		List<CustomerFull> aggregatedCustomer = new ArrayList<>();
		for (Entry<Long, Pegawai> customer : customers.entrySet()) {
			long key = customer.getValue().getNip();
			if (details.containsKey(key)) {
				aggregatedCustomer.add(new CustomerFull(customer.getValue(), details.get(key)));
			} else {
				aggregatedCustomer.add(new CustomerFull(customer.getValue()));
			}
		}
		inbound.setBody(aggregatedCustomer);
	}
}
