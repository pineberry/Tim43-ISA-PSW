package isapsw.tim43.ISCC.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class MedicalRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "record", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Report> reports = new HashSet<Report>();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Patient patient;

	@Column(name = "height")
	private float height;

	@Column(name = "weight")
	private float weight;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "blood_type")
	private String bloodType;


	public MedicalRecord(Set<Report> reports, Patient patient, float height, float weight, Date dateOfBirth,
						 String bloodType) {
		this.reports = reports;
		this.patient = patient;
		this.height = height;
		this.weight = weight;
		this.dateOfBirth = dateOfBirth;
		this.bloodType = bloodType;
	}

	public MedicalRecord() {
		super();
	}
}
