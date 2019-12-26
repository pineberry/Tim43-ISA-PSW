package isapsw.tim43.ISCC.model;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

import java.util.List;

@Entity
@Data
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@JsonIgnore
	@Column(name = "password")
	private String password;
	
	@Column(name = "firstName", nullable = false)
	private String firstName;
	
	@Column(name = "lastName", nullable = false)
	private String lastName;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "state", nullable = false)
	private String state;
	
	@Column(name = "phoneNumber", nullable = false)
	private String phoneNumber;
	
	@Column(name = "averageRating", nullable = false)
	private double averageRating;
	
	@Column(name = "workingtimeStart", nullable = false)
	private String workingtimeStart;
	
	@Column(name = "workingtimeEnd", nullable = false)
	private String workingtimeEnd;
	
//	private List<Report> reports;
	
	@Column(name = "onVacation", nullable = false)
	private Boolean onVacation;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonManagedReference(value="doctor-type")
	private ProcedureType specialized;

	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JsonBackReference(value="doctor-procedure")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.NO_ACTION)
	private List<MedicalProcedure> medicalProcedures;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonManagedReference(value="doctor-clinic")
	private Clinic clinic;
	
	public Doctor() {
		super();
	}

	public Doctor(long id, String email, String password, String firstName, String lastName, String address,
			String city, String state, String phoneNumber, double averageRating, String workingtimeStart,
			String workingtimeEnd, Boolean onVacation, ProcedureType specialized,
			List<MedicalProcedure> medicalProcedures, Clinic clinic) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phoneNumber = phoneNumber;
		this.averageRating = averageRating;
		this.workingtimeStart = workingtimeStart;
		this.workingtimeEnd = workingtimeEnd;
		this.onVacation = onVacation;
		this.specialized = specialized;
		this.medicalProcedures = medicalProcedures;
		this.clinic = clinic;
	}
	
}
