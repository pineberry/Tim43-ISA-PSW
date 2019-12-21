package isapsw.tim43.ISCC.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ClinicAdministrator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Clinic clinic;

	@Column(name = "firstLogin")
	private boolean firstLogin;
	/*
	private List<VacationRequest> vacationRequests;
	private List<BookingRequest> bookingRequests;*/

	public ClinicAdministrator() {
	}

	public ClinicAdministrator(long id, String email, String password, String firstName, String lastName,
							   String phoneNumber, String address, String city, String state, Clinic clinic,
							   List<VacationRequest> vacationRequests, List<BookingRequest> bookingRequests) {
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
		this.clinic = clinic;
		/*this.vacationRequests = vacationRequests;
		this.bookingRequests = bookingRequests;*/
	}
}
