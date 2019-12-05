package isapsw.tim43.ISCC.model;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class MedicalRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "roomName", nullable = false)
	private String roomName;
	
	@Column(name = "roomNumber", nullable = false)
	private int roomNumber;

	@OneToMany(mappedBy = "medicalRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<MedicalProcedure> medicalProcedures = new HashSet<MedicalProcedure>();

	public MedicalRoom(long id, String roomName, int roomNumber) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.roomNumber = roomNumber;
	}
	
	public MedicalRoom() {
		super();
	}	
}
