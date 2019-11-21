package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.model.ClinicCenterAdministrator;
import isapsw.tim43.ISCC.repository.ClinicCenterAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicCenterAdministratorService {

    @Autowired
    ClinicCenterAdministratorRepository clinicCenterAdminRepo;

    public ClinicCenterAdministrator save(ClinicCenterAdministrator clinicCenterAdministrator) {
        return clinicCenterAdminRepo.save(clinicCenterAdministrator);
    }

    public void remove(Long id) {
        clinicCenterAdminRepo.deleteById(id);
    }

<<<<<<< HEAD
	public ClinicCenterAdministrator findUserByEmailAddress(String emailAddress) {
		ClinicCenterAdministrator clinicCenterAdmin = null;

		for (ClinicCenterAdministrator c : clinicCenterAdminRepo.findAll()) {
			if(c.getEmail().equals(emailAddress))
			{
				clinicCenterAdmin = c;
				break;
			}
		}
		return clinicCenterAdmin;
	}
=======
    public ClinicCenterAdministrator findByEmail(String email) {
        return clinicCenterAdminRepo.findByEmail(email);
    }
>>>>>>> e457d6d388b8fb253c9443c134ff2ee314be8b93
}
