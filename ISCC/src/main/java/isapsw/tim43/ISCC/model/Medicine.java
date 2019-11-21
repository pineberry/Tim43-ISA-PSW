package isapsw.tim43.ISCC.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	public Medicine(long id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public Medicine() {
		super();
	}
}
