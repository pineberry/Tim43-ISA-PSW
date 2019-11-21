package isapsw.tim43.ISCC.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Diagnosis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "latinName")
	private String latinName;

	@Column(name = "code")
	private String code;

	public Diagnosis(long id, String name, String latinName, String code) {
		super();
		this.id = id;
		this.name = name;
		this.latinName = latinName;
		this.code = code;
	}

	public Diagnosis() {
		super();
	}
}
