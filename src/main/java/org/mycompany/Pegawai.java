package org.mycompany;

public class Pegawai {

	protected long nip;
	protected String name;
	protected String email;
	
	public Pegawai() {}
	public Pegawai(long nip, String name, String email) {
		this.nip = nip;
		this.name = name;
		this.email = email;
	}
		
	public long getNip() {
		return nip;
	}
	public void setNip(long nip) {
		this.nip = nip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Pegawai [nip=" + nip + ", name=" + name + ", email=" + email + "]";
	}
	
}