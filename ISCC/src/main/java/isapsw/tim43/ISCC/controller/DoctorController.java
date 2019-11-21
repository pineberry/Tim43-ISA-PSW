package isapsw.tim43.ISCC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isapsw.tim43.ISCC.dto.DoctorDTO;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.service.DoctorService;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping(value = "/add", consumes = "application/json")
	public ResponseEntity<DoctorDTO> saveDoctor(@RequestBody DoctorDTO doctorDTO){
		
		 if(doctorDTO.getEmail() == null || doctorDTO.getEmail().isEmpty() || doctorDTO.getFirstName() == null  || doctorDTO.getFirstName().isEmpty()
                 || doctorDTO.getLastName() == null  || doctorDTO.getLastName().isEmpty() || doctorDTO.getPassword() == null
                 || doctorDTO.getPassword().isEmpty() || doctorDTO.getState() == null  || doctorDTO.getState().isEmpty() || doctorDTO.getCity() == null
                 || doctorDTO.getCity().isEmpty() || doctorDTO.getAddress() == null  || doctorDTO.getAddress().isEmpty() || doctorDTO.getPhoneNumber() == null  || doctorDTO.getPhoneNumber().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
		 
		Doctor doctor = new Doctor();
		
		doctor.setEmail(doctorDTO.getEmail());
		doctor.setFirstName(doctorDTO.getFirstName());
		doctor.setLastName(doctorDTO.getLastName());
		doctor.setCity(doctorDTO.getCity());
		doctor.setState(doctorDTO.getState());
		doctor.setAddress(doctorDTO.getAddress());
		doctor.setPassword(doctorDTO.getPassword());
		doctor.setAverageRating(doctorDTO.getAverageRating());
		doctor.setWorkingtimeStart(doctorDTO.getWorkingtimeStart());
		doctor.setWorkingtimeEnd(doctorDTO.getWorkingtimeEnd());
		doctor.setPhoneNumber(doctorDTO.getPhoneNumber());
		doctor.setOnVacation(false);
		
		doctor = doctorService.save(doctor);
		
		return new ResponseEntity<>(new DoctorDTO(doctor), HttpStatus.CREATED);
	}

}
