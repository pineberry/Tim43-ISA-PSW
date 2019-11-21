package isapsw.tim43.ISCC.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
