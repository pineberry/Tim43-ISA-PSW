package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.model.ClinicCenterAdministrator;
import isapsw.tim43.ISCC.service.ClinicCenterAdministratorService;
import isapsw.tim43.ISCC.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/center/admin")
public class ClinicCenterAdministratorController {

    @Autowired
    PatientService patientService;

    @Autowired
    ClinicCenterAdministratorService cliniCenterAdminService;

    
}
