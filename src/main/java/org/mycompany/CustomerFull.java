package org.mycompany;

public class CustomerFull extends Pegawai {
	
	private String keterangan;
	
	public CustomerFull() {}
	public CustomerFull(Pegawai customer, Keterangan detail) {
		this.nip = customer.getNip();
		this.name = customer.getName();
		this.email = customer.getEmail();
		this.keterangan = detail.getKeterangan();
	}
	
	public CustomerFull(long id, String name, String email, String keterangan) {
		super(id, name, email);
		this.keterangan = keterangan;
	}

	public CustomerFull(Pegawai customer) {
		this.nip = customer.getNip();
		this.name = customer.getName();
		this.email = customer.getEmail();
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	@Override
	public String toString() {
		return "CustomerFull [keterangan=" + keterangan + ", nip=" + nip + ", name=" + name + ", email=" + email
				+ "]";
	}
	
	
}
