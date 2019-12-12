package org.mycompany.route;

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
		from("direct:apiToApi")
			.to("direct:getCustomer")
			.setHeader("customer", simple("${body}"))
			.to("direct:getDetail")
			.setHeader("detail", simple("${body}"))
			.to("direct:aggregateHeaders");
		from("direct:aggregateHeaders")
			.bean(customerAggregator, "createFullBody");
		from("direct:getCustomer")
			.bean(customerGenerator, "generateCustomer");
		from("direct:getDetail")
			.bean(customerGenerator, "generateDetail");
	}
}
