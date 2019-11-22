package isapsw.tim43.ISCC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import isapsw.tim43.ISCC.dto.DoctorDTO;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping(value = "/add", consumes = "application/json")
	public ResponseEntity<DoctorDTO> addDoctor(@RequestBody DoctorDTO doctorDTO){
		doctorDTO = doctorService.save(doctorDTO);

		if(doctorDTO != null){
			return new ResponseEntity<>(doctorDTO, HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DoctorDTO>> getAllDoctors(){
		List<DoctorDTO> doctorDTOList = doctorService.findAll();
		return new ResponseEntity<>(doctorDTOList, HttpStatus.OK);
	}

}
