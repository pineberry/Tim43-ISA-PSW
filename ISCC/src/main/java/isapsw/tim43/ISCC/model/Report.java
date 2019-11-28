package isapsw.tim43.ISCC.model;

import lombok.Data;

import javax.persistence.*;
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
	private Diagnosis diagnosis;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Doctor doctor;

	@OneToMany(mappedBy = "report", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Prescription> prescription = new HashSet<Prescription>();

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
