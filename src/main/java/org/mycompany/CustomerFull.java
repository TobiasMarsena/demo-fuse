package org.mycompany;

public class CustomerFull extends Pegawai {
	
	private String description;
	
	public CustomerFull() {}
	public CustomerFull(Pegawai customer, Keterangan detail) {
		this.nip = customer.getNip();
		this.name = customer.getName();
		this.email = customer.getEmail();
		this.description = detail.getDescription();
	}
	
	public CustomerFull(long id, String name, String email, String description) {
		super(id, name, email);
		this.description = description;
	}

	public CustomerFull(Pegawai customer) {
		this.nip = customer.getNip();
		this.name = customer.getName();
		this.email = customer.getEmail();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CustomerFull [description=" + description + ", nip=" + nip + ", name=" + name + ", email=" + email
				+ "]";
	}
	
	
}
