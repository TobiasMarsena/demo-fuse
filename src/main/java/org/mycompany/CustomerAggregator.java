package org.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerAggregator implements AggregationStrategy {

	Logger logger = (Logger) LoggerFactory.getLogger(CustomerAggregator.class);
	
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		logger.info("========= Hello from aggregator ==========");
		CustomerFull full = new CustomerFull(
				( (Customer) newExchange.getIn().getHeader("customer")).getName(), 
				( (CustomerDetail) newExchange.getIn().getHeader("detail")).getDescription());
		newExchange.getIn().setHeader("result", full);
		
		return newExchange;
	}
	
	public void createFullBody(Exchange exchange) {
		Message inbound = exchange.getIn();
		String name = ((Customer) inbound.getHeader("customer")).getName();
		String detail = ((CustomerDetail) inbound.getHeader("detail")).getDescription();
		exchange.getOut().setBody(new CustomerFull(name, detail));
	}
}
