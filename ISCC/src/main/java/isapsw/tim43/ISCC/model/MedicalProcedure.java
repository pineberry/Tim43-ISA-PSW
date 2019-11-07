package isapsw.tim43.ISCC.model;

import java.util.Date;

public class MedicalProcedure {

	private long id;
	private String name;
	private String type;
	private Date dateOfProcedure;
	private MedicalRoom medicalRoom;
	private Doctor doctor;
	private float price;
	private float discount;
	/*booked 
	 * - false: za unapred definisane preglede koji nisu zakazani od strane pacijenta
	 * - true: pacijent zakazao unapred definisan pregled
	 * - true: admin potvrdio zakazivanje pregleda */
	private boolean booked;
	private Report report;
	
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
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Date getDateOfProcedure() {
		return dateOfProcedure;
	}
	
	public void setDateOfProcedure(Date dateOfProcedure) {
		this.dateOfProcedure = dateOfProcedure;
	}
	
	public MedicalRoom getMedicalRoom() {
		return medicalRoom;
	}
	
	public void setMedicalRoom(MedicalRoom medicalRoom) {
		this.medicalRoom = medicalRoom;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getDiscount() {
		return discount;
	}
	
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	public boolean isBooked() {
		return booked;
	}
	
	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public MedicalProcedure(long id, String name, String type, Date dateOfProcedure, MedicalRoom medicalRoom,
			Doctor doctor, float price, float discount, boolean booked, Report report) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.dateOfProcedure = dateOfProcedure;
		this.medicalRoom = medicalRoom;
		this.doctor = doctor;
		this.price = price;
		this.discount = discount;
		this.booked = booked;
		this.report = report;
	}

	public MedicalProcedure() {
		super();
	} 
}
