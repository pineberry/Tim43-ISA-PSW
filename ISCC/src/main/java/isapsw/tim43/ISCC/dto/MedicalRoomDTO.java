package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.MedicalRoom;

public class MedicalRoomDTO {
	
	private long id;
	private String roomName;
	private int roomNumber;
	
	public MedicalRoomDTO() {}
	
	public MedicalRoomDTO(MedicalRoom medicalRoom) {
		this(medicalRoom.getId(), medicalRoom.getRoomName(), medicalRoom.getRoomNumber());
	}
	
	public MedicalRoomDTO(long id, String roomName, int roomNumber) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.roomNumber = roomNumber;
	}
	public long getId() {
		return id;
	}
	public String getRoomName() {
		return roomName;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	
	

}
