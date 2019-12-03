package org.mycompany.route;

import java.io.IOException;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.spi.DataFormat;
import org.mycompany.CustomerAggregator;
import org.mycompany.CustomerGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

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
