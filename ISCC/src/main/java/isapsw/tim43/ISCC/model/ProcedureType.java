package isapsw.tim43.ISCC.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

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

	@Column(name = "price")
	private float price;

	@ManyToMany(mappedBy = "types")
	@JsonIgnore
	private List<Clinic> clinics;

	@OneToMany(mappedBy = "specialized", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JsonBackReference(value="doctor-type")
    @JsonIgnore
	private List<Doctor> doctors;

	@OneToMany(mappedBy = "procedureType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JsonBackReference(value="procedure-type")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.NO_ACTION)
	private List<MedicalProcedure> medicalProcedures;

	public ProcedureType(){}
	
	public ProcedureType(String typeName, String typeDescription, float price) {
		super();
		this.typeName = typeName;
		this.typeDescription = typeDescription;
		this.price = price;
	}
	public ProcedureType(long id, String typeName, String typeDescription, float price) {
		super();
		this.id=id;
		this.typeName = typeName;
		this.typeDescription = typeDescription;
		this.price = price;
	}
}
