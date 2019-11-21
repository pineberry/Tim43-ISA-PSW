package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.model.ClinicAdministrator;
import isapsw.tim43.ISCC.repository.ClinicAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicAdministratorService {

    @Autowired
    ClinicAdministratorRepository clinicAdministratorRepository;

    public ClinicAdministrator save(ClinicAdministrator clinicAdministrator) {
        return clinicAdministratorRepository.save(clinicAdministrator);
    }

    public void remove(Long id) {
        clinicAdministratorRepository.deleteById(id);
    }

	public ClinicAdministrator findUserByEmailAddress(String emailAddress) {
		ClinicAdministrator clinicAdmin = null;

		for (ClinicAdministrator c : clinicAdministratorRepository.findAll()) {
			if(c.getEmail().equals(emailAddress))
			{
				clinicAdmin = c;
				break;
			}
		}
		return clinicAdmin;
	}
}

