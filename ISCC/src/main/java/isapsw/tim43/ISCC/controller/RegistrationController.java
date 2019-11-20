package isapsw.tim43.ISCC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isapsw.tim43.ISCC.dto.UserDTO;
import isapsw.tim43.ISCC.model.Patient;
import isapsw.tim43.ISCC.service.DataValidationService;
import isapsw.tim43.ISCC.service.PatientService;

@RestController
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private DataValidationService dataValidator;
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Patient> registerUser(@RequestBody UserDTO user)
	{
		if(patientService.findByHealthCareNumber(user.getHealthCareNumber()) == null)
		{//pacijent ne postoji u bazi, sledece je provera ostalih podataka 
			if(dataValidator.validateUserData(user))
			{
				Patient patient = new Patient(user.getEmail(), user.getPassword(), user.getFirstName(),
						user.getLastName(), user.getAddress(), user.getCity(), user.getState(),
						user.getPhoneNumber(), user.getHealthCareNumber(), "pending");
				return new ResponseEntity<>(patientService.save(patient), HttpStatus.CREATED); //201
			}
			else
			{
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE); //406
			}
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //400
		}
		
		
	}

}
