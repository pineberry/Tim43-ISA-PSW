package isapsw.tim43.ISCC.model;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "notes")
	private String notes;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Diagnosis diagnosis;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Doctor doctor;

	@OneToMany(mappedBy = "report", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Prescription> prescription = new HashSet<Prescription>();

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private MedicalRecord record;

	public Report(long id, Diagnosis diagnosis, MedicalRecord record) {
		super();
		this.id = id;
		this.diagnosis = diagnosis;
		this.record = record;
	}

	public Report() {
		super();
	}	
}
