package isapsw.tim43.ISCC.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "checked")
	private boolean checked;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Medicine medicine;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Report report;

	public Prescription(long id,  Medicine medicine, boolean checked, Report report) {
		super();
		this.id = id;
		this.medicine = medicine;
		this.report = report;
		this.checked = checked;
	}

	public Prescription() {
		super();
	}
}
