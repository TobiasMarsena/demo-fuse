package org.mycompany;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogService {
	Logger logger = (Logger) LoggerFactory.getLogger(LogService.class);
	
	public void logCustomerAndDetail(Exchange exchange) {
		CustomerFull full = (CustomerFull) exchange.getIn().getHeader("result");
		logger.info(full.toString());
		
		exchange.getOut().setBody("Hey");
	}
}
