package org.mycompany.route;

import java.io.IOException;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.mycompany.CustomerAggregator;
import org.mycompany.CustomerGenerator;
import org.springframework.beans.factory.annotation.Autowired;

public class HTTPRoute extends RouteBuilder {


	@Autowired
	CustomerAggregator customerAggregator;
	@Autowired
	CustomerGenerator customerGenerator;
	
	@Override
	public void configure() throws Exception {
		
		onException(IOException.class)
			.log("${exception}")
			.to("mock:IOHandler");
		
		from("direct:apiToApi")
			.setExchangePattern(ExchangePattern.InOut)
			.log("Reached API to API Route. Consuming Customer API . . .")
			.to("direct:getCustomer")
			.log("Set customer header with body: ${body}")
			.setHeader("customer", simple("${body}"))
			.to("direct:getDetail")
			.setHeader("detail", simple("${body}"))
			.bean(customerAggregator, "createFullBody")
			.log("Route api-to-api Aggregate body: ${body}");
		from("direct:getCustomer")
			.log("Reached GetCustomer. Generating values . . .")
			.bean(customerGenerator, "generateCustomer")
			.log("GetCustomer API returns == ${body}");
		from("direct:getDetail")
			.log("Reached GetDetail. Generating values . . .")
			.bean(customerGenerator, "generateDetail")
			.log("GetDetail API returns == ${body}");
	}
	
	

}
