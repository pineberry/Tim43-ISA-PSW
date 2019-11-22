package isapsw.tim43.ISCC.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class ProcedureType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "typeName", unique = true, nullable = false)
	private String typeName;

	@Column(name = "typeDescription", nullable = false)
	private String typeDescription;

	@OneToMany(mappedBy = "specialized", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Doctor> doctors = new HashSet<Doctor>();

	@OneToMany(mappedBy = "procedureType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<MedicalProcedure> medicalProcedures = new HashSet<MedicalProcedure>();

	public ProcedureType(){}
	
	public ProcedureType(String typeName, String typeDescription) {
		super();
		this.typeName = typeName;
		this.typeDescription = typeDescription;
	}
}
