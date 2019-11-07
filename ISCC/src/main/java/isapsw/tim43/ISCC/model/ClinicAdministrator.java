package isapsw.tim43.ISCC.model;

import java.util.List;

public class ClinicAdministrator {

	private long id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private String city;
	private String state;
	
	private List<VacationRequest> vacationRequests;
	private List<BookingRequest> bookingRequests;
	
	public ClinicAdministrator(long id, String email, String password, String firstName, String lastName,
			String phoneNumber, String address, String city, String state, List<VacationRequest> vacationRequests,
			List<BookingRequest> bookingRequests) {
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
		this.vacationRequests = vacationRequests;
		this.bookingRequests = bookingRequests;
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

	public List<VacationRequest> getVacationRequests() {
		return vacationRequests;
	}

	public void setVacationRequests(List<VacationRequest> vacationRequests) {
		this.vacationRequests = vacationRequests;
	}

	public List<BookingRequest> getBookingRequests() {
		return bookingRequests;
	}

	public void setBookingRequests(List<BookingRequest> bookingRequests) {
		this.bookingRequests = bookingRequests;
	}
	
	
}
