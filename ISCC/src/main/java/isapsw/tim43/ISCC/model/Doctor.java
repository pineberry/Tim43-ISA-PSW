package isapsw.tim43.ISCC.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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
	private ProcedureType specialized;

	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<MedicalProcedure> medicalProcedures = new HashSet<MedicalProcedure>();
	
	public Doctor() {
		super();
	}
	
	public Doctor(long id, String email, String password, String firstName, String lastName, String address,
			String city, String state, String phoneNumber, double averageRating, String workingtimeStart,
			String workingtimeEnd, boolean onVacation) {
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
	}
}
