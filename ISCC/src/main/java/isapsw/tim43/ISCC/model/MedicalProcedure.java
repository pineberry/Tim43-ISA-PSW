package isapsw.tim43.ISCC.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class MedicalProcedure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	private ProcedureType procedureType;

	@Column(name = "dateOfProcedure", nullable = false)
	private Date dateOfProcedure;

	@ManyToOne(fetch = FetchType.EAGER)
	private MedicalRoom medicalRoom;

	@ManyToOne(fetch = FetchType.EAGER)
	private Doctor doctor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Patient patient;

	@Column(name = "discount")
	private float discount;

	@Column(name = "startTime")
	private String startTime;

	@Column(name = "endTime")
	private String endTime;

	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "operating", joinColumns = @JoinColumn(name="procedure_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="doctor_id", referencedColumnName = "id"))
	private List<Doctor> doctors;

	/*booked 
	 * - false: za unapred definisane preglede koji nisu zakazani od strane pacijenta
	 * - true: pacijent zakazao unapred definisan pregled
	 * - true: admin potvrdio zakazivanje pregleda */

	@Column(name = "booked")
	private boolean booked;
//	private Report report;

	public MedicalProcedure(long id, ProcedureType procedureType, Date dateOfProcedure, MedicalRoom medicalRoom,
			Doctor doctor, float discount, boolean booked) {
		super();
		this.id = id;
		this.procedureType = procedureType;
		this.dateOfProcedure = dateOfProcedure;
		this.medicalRoom = medicalRoom;
		this.doctor = doctor;
		this.discount = discount;
		this.booked = booked;
	}
	public MedicalProcedure(ProcedureType procedureType, Date dateOfProcedure, MedicalRoom medicalRoom,
			Doctor doctor, Patient patient, float discount, boolean booked) {
		super();
		this.procedureType = procedureType;
		this.dateOfProcedure = dateOfProcedure;
		this.medicalRoom = medicalRoom;
		this.patient = patient;
		this.doctor = doctor;
		this.discount = discount;
		this.booked = booked;
	}

	

	public MedicalProcedure() {
		super();
	} 
}
