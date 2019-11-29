package isapsw.tim43.ISCC.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date firstFreeDate;
	private List<Date> scheduledDates = new ArrayList<Date>();

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
}
