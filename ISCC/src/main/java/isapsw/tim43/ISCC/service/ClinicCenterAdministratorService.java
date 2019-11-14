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


}
