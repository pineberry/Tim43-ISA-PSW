package isapsw.tim43.ISCC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private ClinicAdministratorService clinicAdministratorService;
	
	@Autowired
	private ClinicCenterAdministratorService clinicCenterAdministratorService;
	
	@Autowired
	private PatientService patientService;
	
	public Object findUserByEmailAddress(String emailAddress)
	{
		if (patientService.findUserByEmailAddress(emailAddress) != null)
		{
			return patientService.findUserByEmailAddress(emailAddress);
		} 
		else if (clinicCenterAdministratorService.findByEmail(emailAddress) != null )
		{
			return clinicCenterAdministratorService.findByEmail(emailAddress);
		}
		else if (clinicAdministratorService.findUserByEmailAddress(emailAddress) != null )
		{
			return clinicAdministratorService.findUserByEmailAddress(emailAddress);
		}
		else if (doctorService.findUserByEmailAddress(emailAddress) != null )
		{
			return doctorService.findUserByEmailAddress(emailAddress);
		}
		else return null;

	}
}
