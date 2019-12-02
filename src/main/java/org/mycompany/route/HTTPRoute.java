package org.mycompany.route;

import java.io.IOException;

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
			.to("direct:getCustomer")
			.setHeader("customer", simple("${body}"))
			.to("direct:getDetail")
			.setHeader("detail", simple("${body}"))
			.bean(customerAggregator, "createFullBody");
		from("direct:getCustomer")
			.bean(customerGenerator, "generateCustomer");
		from("direct:getDetail")
			.bean(customerGenerator, "generateDetail");
		from("direct:mysqlToApi")
			.to("sql:SELECT * FROM Pegawai?dataSource=jdbc-mysql")
			.setHeader("customer", simple("${body}"))
			.to("direct:getDetail")
			.setHeader("detail", simple("${body}"))
			.bean(customerAggregator, "createFullBody");
		from("direct:mysqlToMariadb")
			.to("sql:SELECT * FROM Pegawai?dataSource=jdbc-mysql")
			.setHeader("customer", simple("${body}"))
			.to("sql:SELECT * FROM Keterangan?datasource=jdbc-mariadb")
			.setHeader("detail", simple("${body}"))
			.bean(customerAggregator, "createFullBody");
	}
	
	

}
