package org.mycompany;

public class CustomerFull {
	
	private long id;
	private String name;
	private String description;
	
	
	public CustomerFull() {
		super();
	}
	public CustomerFull(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "CustomerFull [name=" + name + ", description=" + description + "]";
	}
	
	
	
}
