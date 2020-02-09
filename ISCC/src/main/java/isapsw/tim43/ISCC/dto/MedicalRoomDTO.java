package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.MedicalRoom;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MedicalRoomDTO {
	
	private long id;
	private String roomName;
	private int roomNumber;
	private Clinic clinic;
	
	private Date firstFreeDate;
	private List<Date> scheduledDates = new ArrayList<Date>();

	public MedicalRoomDTO() {}
	
	public MedicalRoomDTO(MedicalRoom medicalRoom) {
		this(medicalRoom.getId(), medicalRoom.getRoomName(), medicalRoom.getRoomNumber(), medicalRoom.getClinic());
	}
	
	public MedicalRoomDTO(long id, String roomName, int roomNumber, Clinic clinic) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.roomNumber = roomNumber;
		this.clinic = clinic;
	}
}
