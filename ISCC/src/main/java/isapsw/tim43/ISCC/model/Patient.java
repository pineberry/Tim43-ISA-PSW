package isapsw.tim43.ISCC.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "health_care_number", unique = true, nullable = false)
	private String healthCareNumber;

	@Column(name = "status")
	private String status;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private MedicalRecord record;
	
	public Patient() {
		super();
	}

	public Patient(String email, String password, String firstName, String lastName, String address,
			String city, String state, String phoneNumber, String healthCareNumber, String status, MedicalRecord record) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phoneNumber = phoneNumber;
		this.healthCareNumber = healthCareNumber;
		this.status = status;
		this.record = record;
	}

	public Patient(Long id, String email, String password, String firstName, String lastName, String address,
			String city, String state, String phoneNumber, String healthCareNumber, String status,
			MedicalRecord record) {
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
		this.healthCareNumber = healthCareNumber;
		this.status = status;
		this.record = record;
	}
	
	
}
