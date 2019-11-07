package isapsw.tim43.ISCC.model;

public class Prescription {

	private long id;
	private String prescription; //sadrzaj recepta 
	private Medicine medicine;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getPrescription() {
		return prescription;
	}
	
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	
	public Medicine getMedicine() {
		return medicine;
	}
	
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Prescription(long id, String prescription, Medicine medicine) {
		super();
		this.id = id;
		this.prescription = prescription;
		this.medicine = medicine;
	}

	public Prescription() {
		super();
	}
}
