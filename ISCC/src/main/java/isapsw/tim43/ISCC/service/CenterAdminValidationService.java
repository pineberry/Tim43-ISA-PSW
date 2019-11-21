package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.model.ClinicCenterAdministrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CenterAdminValidationService {

    @Autowired
    ClinicCenterAdministratorService clinicCenterAdministratorService;

    public boolean validateAdministrator(ClinicCenterAdministrator centerAdmin) {

        if (centerAdmin.getEmail() == null || centerAdmin.getPassword() == null || centerAdmin.getFirstName() == null ||
        centerAdmin.getLastName() == null) {
            return false;
        }

        if (centerAdmin.getEmail().isEmpty() || centerAdmin.getPassword().isEmpty() ||
                centerAdmin.getFirstName().isEmpty() || centerAdmin.getLastName().isEmpty()) {
            return false;
        }

        ClinicCenterAdministrator admin = clinicCenterAdministratorService.findUserByEmailAddress(centerAdmin.getEmail());

        if (admin != null) {
            return false;
        }

        return true;
    }
}
