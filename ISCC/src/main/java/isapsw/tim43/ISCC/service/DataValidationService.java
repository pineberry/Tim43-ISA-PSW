package isapsw.tim43.ISCC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.dto.UserDTO;

@Service
public class DataValidationService {

	
	@Autowired
	private PatientService patientService;
	
	public boolean validateUserData(UserDTO user) {
		
		if(patientService.findByHealthCareNumber(user.getHealthCareNumber()) == null)
		{//znaci da ga nema u bazi sa tim brojem tako da
			return true;
		}
		else
		{
			return false;
		}
	}
}
