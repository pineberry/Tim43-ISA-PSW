package isapsw.tim43.ISCC.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MedicalProcedure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//	private String name;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ProcedureType procedureType;

	@Column(name = "dateOfProcedure", unique = true, nullable = false)
	private Date dateOfProcedure;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private MedicalRoom medicalRoom;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Doctor doctor;

	@Column(name = "price")
	private float price;

	@Column(name = "discount")
	private float discount;
	/*booked 
	 * - false: za unapred definisane preglede koji nisu zakazani od strane pacijenta
	 * - true: pacijent zakazao unapred definisan pregled
	 * - true: admin potvrdio zakazivanje pregleda */

	@Column(name = "booked")
	private boolean booked;
//	private Report report;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
/*
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
*/
	public ProcedureType getType() {
		return procedureType;
	}

	public void setType(ProcedureType type) {
		this.procedureType = type;
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

	public ProcedureType getProcedureType() {
		return procedureType;
	}

	public void setProcedureType(ProcedureType procedureType) {
		this.procedureType = procedureType;
	}
	/*
    public Report getReport() {
    	return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
    */
	public MedicalProcedure(long id, ProcedureType procedureType, Date dateOfProcedure, MedicalRoom medicalRoom,
			Doctor doctor, float price, float discount, boolean booked) {
		super();
		this.id = id;
		this.procedureType = procedureType;
		this.dateOfProcedure = dateOfProcedure;
		this.medicalRoom = medicalRoom;
		this.doctor = doctor;
		this.price = price;
		this.discount = discount;
		this.booked = booked;
	}

	public MedicalProcedure() {
		super();
	} 
}
