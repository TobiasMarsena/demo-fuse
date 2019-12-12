package org.mycompany;

public class Keterangan {
	
	private long nip;
	private String keterangan;

	public Keterangan(long id, String keterangan) {
		setNip(id);
		setKeterangan(keterangan);
	}
	public long getNip() {
		return nip;
	}
	public void setNip(long nip) {
		this.nip = nip;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	@Override
	public String toString() {
		return "Keterangan [nip=" + nip + ", keterangan=" + keterangan + "]";
	}	
	
}
