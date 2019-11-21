package isapsw.tim43.ISCC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isapsw.tim43.ISCC.dto.MedicalRoomDTO;
import isapsw.tim43.ISCC.model.MedicalRoom;
import isapsw.tim43.ISCC.service.MedicalRoomService;

@RestController
@RequestMapping(value = "/medical/room")
public class MedicalRoomController {
	
	@Autowired
	private MedicalRoomService medicalRoomService;
	
	@PostMapping(value = "/add", consumes = "application/jason")
	public ResponseEntity<MedicalRoomDTO> saveRoom(@RequestBody MedicalRoomDTO medicalRoomDTO){
		
		if(medicalRoomDTO.getRoomName() == null || medicalRoomDTO.getRoomName().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		MedicalRoom medicalRoom = new MedicalRoom();
		medicalRoom.setRoomName(medicalRoomDTO.getRoomName());
		medicalRoom.setRoomNumber(medicalRoom.getRoomNumber());
		
		medicalRoom = medicalRoomService.save(medicalRoom);
		return new ResponseEntity<>(new MedicalRoomDTO(medicalRoom), HttpStatus.CREATED);
	}

}
