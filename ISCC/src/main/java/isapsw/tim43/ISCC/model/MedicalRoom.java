package isapsw.tim43.ISCC.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	@JsonIgnore
	private List<MedicalProcedure> medicalProcedures;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Clinic clinic;

	public MedicalRoom(long id, String roomName, int roomNumber, Clinic clinic) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.roomNumber = roomNumber;
		this.clinic = clinic;
	}
	
	public MedicalRoom() {
		super();
	}	
}
