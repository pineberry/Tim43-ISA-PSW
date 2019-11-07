package isapsw.tim43.ISCC.model;

import java.util.List;

public class ClinicCenterAdministrator {
	
	private long id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private String city;
	private String state;
	private ClinicCenter clinicCenter;
	
	private List<Patient> registrationRequests;

	public ClinicCenterAdministrator(long id, String email, String password, String firstName, String lastName,
			String phoneNumber, String address, String city, String state, ClinicCenter clinicCenter,
			List<Patient> registrationRequests) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;
		this.state = state;
		this.clinicCenter = clinicCenter;
		this.registrationRequests = registrationRequests;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public ClinicCenter getClinicCenter() {
		return clinicCenter;
	}

	public void setClinicCenter(ClinicCenter clinicCenter) {
		this.clinicCenter = clinicCenter;
	}

	public List<Patient> getRegistrationRequests() {
		return registrationRequests;
	}

	public void setRegistrationRequests(List<Patient> registrationRequests) {
		this.registrationRequests = registrationRequests;
	}
	
	
}
