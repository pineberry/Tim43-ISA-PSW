package isapsw.tim43.ISCC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import isapsw.tim43.ISCC.dto.MedicalRoomDTO;
import isapsw.tim43.ISCC.model.MedicalRoom;
import isapsw.tim43.ISCC.service.MedicalRoomService;

import java.util.List;

@RestController
@RequestMapping(value = "/medical/room")
public class MedicalRoomController {
	
	@Autowired
	private MedicalRoomService medicalRoomService;
	
	@PostMapping(value = "/add", consumes = "application/json")
	public ResponseEntity<MedicalRoomDTO> addRoom(@RequestBody MedicalRoomDTO medicalRoomDTO){
		medicalRoomDTO = medicalRoomService.save(medicalRoomDTO);

		if (medicalRoomDTO != null){
			return new ResponseEntity<>(medicalRoomDTO, HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicalRoomDTO>> getAllRooms(){
		List<MedicalRoomDTO> medicalRoomDTOList = medicalRoomService.findAll();
		return new ResponseEntity<>(medicalRoomDTOList, HttpStatus.OK);
	}

}
