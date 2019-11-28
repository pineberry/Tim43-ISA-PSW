package isapsw.tim43.ISCC.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class MedicalRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "record", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Report> reports = new HashSet<Report>();

	public MedicalRecord(long id) {
		super();
		this.id = id;
	}

	public MedicalRecord() {
		super();
	}
}
