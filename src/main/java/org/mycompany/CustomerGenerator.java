package org.mycompany;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;

public class CustomerGenerator {
	
	public void generateCustomer(Exchange exchange) {
		List<Pegawai> customers = new ArrayList<>();
		customers.add(new Pegawai(1234, "RezaAPI", "reza-api@kemlu.go.id"));
		customers.add(new Pegawai(2345, "ChrisnaAPI", "chrisna-api@kemlu.go.id"));
		customers.add(new Pegawai(3456, "TobiasAPI", "tobias-api@kemlu.go.id"));
		exchange.getIn().setBody(customers);
	}
	
	public void generateDetail(Exchange exchange) {
		List<Keterangan> details = new ArrayList<>();
		details.add(new Keterangan(1234, "Keterangan 1234 API"));
		details.add(new Keterangan(2345, "Keterangan 2345 API"));
		exchange.getIn().setBody(details);
	}

}
