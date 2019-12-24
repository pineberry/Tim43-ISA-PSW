package isapsw.tim43.ISCC.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Nurse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@JsonIgnore
	@Column(name = "password", nullable = false)
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

	/* true - Nurse is on vacation
	 * false - Nurse is not on vacation
	 * */
	@Column(name = "on_vacation")
	private boolean onVacation;

	@OneToMany(mappedBy = "nurse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Prescription> checkedPrescriptions;

	@Column(name = "first_login")
	private boolean firstLogin;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Clinic clinic;

	public Nurse(long id, String email, String password, String firstName, String lastName, String phoneNumber,
			String address, String city, String state, boolean onVacation, List<Prescription> checkedPrescriptions, boolean firstLogin) {
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
		this.onVacation = onVacation;
		this.checkedPrescriptions = checkedPrescriptions;
		this.firstLogin = firstLogin;
	}

	public Nurse() {
	}
}
