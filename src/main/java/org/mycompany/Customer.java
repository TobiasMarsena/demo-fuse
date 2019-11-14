package org.mycompany;

import org.apache.camel.Exchange;

//@Entity
public class Customer {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
		
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Customer setCustomerName(Exchange exchange) {
		Customer cust = new Customer();
		
		cust.setName("Tobias");
		
		return cust;
	}
	
	
}
