package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.model.MedicalRecord;
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
import isapsw.tim43.ISCC.service.EmailService;
import isapsw.tim43.ISCC.service.PatientService;

@RestController
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private DataValidationService dataValidator;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Patient> registerUser(@RequestBody UserDTO user)
	{
		if(patientService.findByHealthCareNumber(user.getHealthCareNumber()) == null)
		{//pacijent ne postoji u bazi, sledece je provera ostalih podataka 
			if(dataValidator.validateUserData(user))
			{
				Patient patient = new Patient(user.getEmail(), user.getPassword(), user.getFirstName(),
						user.getLastName(), user.getAddress(), user.getCity(), user.getState(),
						user.getPhoneNumber(), user.getHealthCareNumber(), "pending", new MedicalRecord());
				Patient p = patientService.save(patient);
				try {
					String emailContent = "User " + p.getFirstName() + " " + p.getLastName() + " has requested to register on ISCC!\n\n" +
										"To accept click on the link below:\n"+
										 "http://localhost:8081/registrationRequest/"+ p.getId();
					emailService.sendNotificationAsync("isa.pws43@gmail.com", emailContent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return new ResponseEntity<>(p, HttpStatus.CREATED); //201
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
