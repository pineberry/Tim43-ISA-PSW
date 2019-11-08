package isapsw.tim43.ISCC.model;

import java.util.Date;
import java.util.List;

public class Doctor {
	
	private long id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String phoneNumber;
	private double averageRating;
	private Date workingtimeStart;
	private Date workingtimeEnd;
	private List<Report> reports;
	private Boolean onVacation;
	private ProcedureType specialized;
	
	public Doctor(long id, String email, String password, String firstName, String lastName, String address,
			String city, String state, String phoneNumber, double averageRating, Date workingtimeStart,
			Date workingtimeEnd, List<Report> reports, Boolean onVacation, ProcedureType specialized) {
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
		this.reports = reports;
		this.onVacation = onVacation;
		this.specialized = specialized;
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
	public Date getWorkingtimeStart() {
		return workingtimeStart;
	}
	public void setWorkingtimeStart(Date workingtimeStart) {
		this.workingtimeStart = workingtimeStart;
	}
	public Date getWorkingtimeEnd() {
		return workingtimeEnd;
	}
	public void setWorkingtimeEnd(Date workingtimeEnd) {
		this.workingtimeEnd = workingtimeEnd;
	}
	public List<Report> getReports() {
		return reports;
	}
	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	public Boolean getOnVacation() {
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
	
	
	
	

}
