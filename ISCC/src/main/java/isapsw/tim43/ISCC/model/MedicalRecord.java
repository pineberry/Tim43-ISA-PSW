package isapsw.tim43.ISCC.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class MedicalRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "record", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Report> reports = new ArrayList<Report>();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Patient patient;

	@Column(name = "height")
	private float height;

	@Column(name = "weight")
	private float weight;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "blood_type")
	private String bloodType;


	public MedicalRecord(List<Report> reports, Patient patient, float height, float weight, Date dateOfBirth,
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
