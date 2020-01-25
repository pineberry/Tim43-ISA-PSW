package isapsw.tim43.ISCC.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class MedicalProcedure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//	private String name;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonManagedReference(value="procedure-type")
	private ProcedureType procedureType;

	@Column(name = "dateOfProcedure", nullable = false)
	private Date dateOfProcedure;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonManagedReference(value="procedure-room")
	@JsonIgnore
	private MedicalRoom medicalRoom;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonManagedReference(value="doctor-procedure")
	@JsonIgnore
	private Doctor doctor;

	@Column(name = "price")
	private float price;

	@Column(name = "discount")
	private float discount;

	@Column(name = "startTime")
	private String startTime;

	@Column(name = "endTime")
	private String endTime;

	/*booked 
	 * - false: za unapred definisane preglede koji nisu zakazani od strane pacijenta
	 * - true: pacijent zakazao unapred definisan pregled
	 * - true: admin potvrdio zakazivanje pregleda */

	@Column(name = "booked")
	private boolean booked;
//	private Report report;

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
	public MedicalProcedure(ProcedureType procedureType, Date dateOfProcedure, MedicalRoom medicalRoom,
			Doctor doctor, float price, float discount, boolean booked) {
		super();
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
