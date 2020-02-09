package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.model.MedicalRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import isapsw.tim43.ISCC.dto.MedicalRoomDTO;
import isapsw.tim43.ISCC.service.MedicalRoomService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/medical/room")
public class MedicalRoomController {
	
	@Autowired
	private MedicalRoomService medicalRoomService;
	
	@PostMapping(value = "/add/{id}", consumes = "application/json")
	public ResponseEntity<MedicalRoomDTO> addRoom(@PathVariable Long id, @RequestBody MedicalRoomDTO medicalRoomDTO){
		medicalRoomDTO = medicalRoomService.save(medicalRoomDTO, id);

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

	@GetMapping(value = "/clinic/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicalRoomDTO>> getClinicRooms(@PathVariable Long id){
		List<MedicalRoomDTO> medicalRoomDTOList = medicalRoomService.findByClinic(id);

		if (medicalRoomDTOList != null) {
			return new ResponseEntity<>(medicalRoomDTOList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<MedicalRoomDTO> getRoomById(@PathVariable Long id){
		MedicalRoom medicalRoom = medicalRoomService.findOne(id);

		if (medicalRoom != null) {
			return new ResponseEntity<>(new MedicalRoomDTO(medicalRoom), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/name/{roomName}/{date}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicalRoomDTO>> getRoomsByName(@PathVariable String roomName,
															   		@PathVariable
																	@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
																	@PathVariable Long id){
		List<MedicalRoomDTO> medicalRoomDTOList = medicalRoomService.searchRoomsByName(roomName, date, id);
		return new ResponseEntity<>(medicalRoomDTOList, HttpStatus.OK);
	}

	@GetMapping(value = "/number/{roomNumber}/{date}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicalRoomDTO>> getRoomsByNumber(@PathVariable int roomNumber,
															  	 	@PathVariable
															   		@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
																 	@PathVariable Long id){
		List<MedicalRoomDTO> medicalRoomDTOList = medicalRoomService.searchRoomsByNumber(roomNumber, date, id);
		return new ResponseEntity<>(medicalRoomDTOList, HttpStatus.OK);
	}

	@PutMapping(value = "/update", consumes = "application/json")
	public ResponseEntity<MedicalRoomDTO> updateRoom(@RequestBody MedicalRoomDTO medicalRoomDTO) {
		medicalRoomDTO = medicalRoomService.update(medicalRoomDTO);
		if (medicalRoomDTO != null) {
			return new ResponseEntity<>(medicalRoomDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteRoom(@PathVariable Long id){
		if (medicalRoomService.remove(id)){
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
