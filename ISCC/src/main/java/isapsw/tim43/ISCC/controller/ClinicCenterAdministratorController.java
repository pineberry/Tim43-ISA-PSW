package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.PatientDTO;
import isapsw.tim43.ISCC.model.ClinicCenterAdministrator;
import isapsw.tim43.ISCC.model.Patient;
import isapsw.tim43.ISCC.service.ClinicCenterAdministratorService;
import isapsw.tim43.ISCC.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/center/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClinicCenterAdministratorController {

    @Autowired
    PatientService patientService;

    @Autowired
    ClinicCenterAdministratorService clinicCenterAdminService;



    @PutMapping(value = "/activate/{id}")
    public ResponseEntity<PatientDTO> activateAccount(@PathVariable Long id) {

        Patient patient = patientService.findById(id);
        patient.setActivated(true);

        patientService.save(patient);
        return new ResponseEntity<>(new PatientDTO(patient), HttpStatus.OK);
    }

    @PostMapping(value = "/accept", consumes = "application/json")
    public ResponseEntity<PatientDTO> acceptRequest(@RequestBody PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setEmail(patientDTO.getEmail());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setPassword(patientDTO.getPassword());
        patient.setHealthCareNumber(patientDTO.getHealthCareNumber());
        patient.setActivated(false);

        patientService.save(patient);
        return new ResponseEntity<>(new PatientDTO(patient), HttpStatus.CREATED);
    }

    @GetMapping(value = "/deny")
    public void denyRequest() {

    }
}
