package isapsw.tim43.ISCC.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "checked")
	private boolean checked;

	@OneToOne(fetch = FetchType.EAGER)
	private Medicine medicine;

	@ManyToOne(fetch = FetchType.EAGER)
	private Clinic clinic;

	@ManyToOne(fetch = FetchType.EAGER)
	private Report report;

	@ManyToOne(fetch = FetchType.EAGER)
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
