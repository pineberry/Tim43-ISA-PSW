package isapsw.tim43.ISCC.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
	public Set<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}
	public Set<MedicalProcedure> getMedicalProcedures() {
		return medicalProcedures;
	}
	public void setMedicalProcedures(Set<MedicalProcedure> medicalProcedures) {
		this.medicalProcedures = medicalProcedures;
	}
}
