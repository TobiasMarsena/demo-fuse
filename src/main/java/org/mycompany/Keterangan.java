package org.mycompany;

//@Entity
public class Keterangan {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long nip;
	
	private String description;

	public Keterangan(long id, String description) {
		setNip(id);
		setDescription(description);
	}
	public long getNip() {
		return nip;
	}
	public void setNip(long nip) {
		this.nip = nip;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
}
