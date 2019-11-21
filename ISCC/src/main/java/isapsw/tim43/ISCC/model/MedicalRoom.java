package isapsw.tim43.ISCC.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MedicalRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "roomName", nullable = false)
	private String roomName;
	
	@Column(name = "roomNumber", nullable = false)
	private long roomNumber;
	
//	private Map<Date, Boolean> availability;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public long getRoomNumber() {
		return roomNumber;
	}
	
	public void setRoomNumber(long roomNumber) {
		this.roomNumber = roomNumber;
	}
	
/*	public Map<Date, Boolean> getAvailability() {
		return availability;
	}
	
	public void setAvailability(Map<Date, Boolean> availability) {
		this.availability = availability;
	}
*/	
	public MedicalRoom(long id, String roomName, long roomNumber) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.roomNumber = roomNumber;
	}
	
	public MedicalRoom() {
		super();
	}	
}
