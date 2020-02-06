package isapsw.tim43.ISCC.model;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

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

	@ManyToOne(fetch = FetchType.EAGER)
	private Doctor doctor;

	@OneToMany(mappedBy = "report", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Prescription> prescription;

	@ManyToOne(fetch = FetchType.EAGER)
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
