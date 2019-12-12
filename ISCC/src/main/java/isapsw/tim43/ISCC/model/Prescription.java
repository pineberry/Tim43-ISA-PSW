package isapsw.tim43.ISCC.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "checked")
	private boolean checked;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Medicine medicine;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Clinic clinic;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Report report;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Nurse nurse;

	public Prescription(Long id,  Medicine medicine, boolean checked, Report report, Nurse nurse) {
		super();
		this.id = id;
		this.medicine = medicine;
		this.report = report;
		this.checked = checked;
		this.nurse = nurse;
	}

	public Prescription() {
		super();
	}
}
