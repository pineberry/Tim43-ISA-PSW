package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.Doctor;

public class DoctorDTO {
	
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
	private String workingtimeStart;
	private String workingtimeEnd;
	
	public DoctorDTO() {}
	
	public DoctorDTO(Doctor doctor) {
		this(doctor.getId(), doctor.getEmail(), doctor.getPassword(), doctor.getFirstName(), doctor.getLastName(),
				doctor.getAddress(), doctor.getCity(), doctor.getState(), doctor.getPhoneNumber(), doctor.getAverageRating(),
				doctor.getWorkingtimeStart(), doctor.getWorkingtimeEnd());
	}
	
	public DoctorDTO(long id, String email, String password, String firstName, String lastName, String address,
			String city, String state, String phoneNumber, double averageRating, String workingtimeStart,
			String workingtimeEnd) {
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
	}
	public long getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public String getWorkingtimeStart() {
		return workingtimeStart;
	}
	public String getWorkingtimeEnd() {
		return workingtimeEnd;
	}
	
	

}
