package isapsw.tim43.ISCC.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

@Entity
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
	
	@Column(name = "adress", nullable = false)
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	public String getWorkingtimeStart() {
		return workingtimeStart;
	}
	public void setWorkingtimeStart(String workingtimeStart) {
		this.workingtimeStart = workingtimeStart;
	}
	public String getWorkingtimeEnd() {
		return workingtimeEnd;
	}
	public void setWorkingtimeEnd(String workingtimeEnd) {
		this.workingtimeEnd = workingtimeEnd;
	}
/*	public List<Report> getReports() {
		return reports;
	}
	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
*/	public Boolean getOnVacation() {
		return onVacation;
	}
	public void setOnVacation(Boolean onVacation) {
		this.onVacation = onVacation;
	}
	public ProcedureType getSpecialized() {
		return specialized;
	}
	public void setSpecialized(ProcedureType specialized) {
		this.specialized = specialized;
	}

	public Set<MedicalProcedure> getMedicalProcedures() {
		return medicalProcedures;
	}

	public void setMedicalProcedures(Set<MedicalProcedure> medicalProcedures) {
		this.medicalProcedures = medicalProcedures;
	}
}
