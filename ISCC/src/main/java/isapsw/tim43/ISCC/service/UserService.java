package isapsw.tim43.ISCC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.model.Patient;

@Service
public class UserService {

//	@Autowired
//	private DoctorService doctorService;
//	
//	@Autowired
//	private ClinicAdministratorService clinicAdministratorService;
//	
//	@Autowired
//	private ClinicCenterAdministratorService clinicCenterAdministratorService;
	
	@Autowired
	private PatientService patientService;
	
	public Patient findUserByEmailAddress(String emailAddress)
	{
		try {
			Patient patient = patientService.findUserByEmailAddress(emailAddress);
			return patient;
		} catch (Exception e) {
			System.out.println("Patient does with email address: " + emailAddress + " not exist in database");
			e.printStackTrace();
		}
		return null;

	}
}
