package org.mycompany;

import org.apache.camel.Exchange;

//@Entity
public class CustomerDetail {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String description;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public CustomerDetail setDetail(Exchange exchange) {
		CustomerDetail detail = new CustomerDetail();
		
		detail.setDescription("Java Developer");
		
		return detail;
	}
	
}
