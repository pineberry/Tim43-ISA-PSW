package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.ClinicAdministratorDTO;
import isapsw.tim43.ISCC.dto.ClinicDTO;
import isapsw.tim43.ISCC.dto.PatientDTO;
import isapsw.tim43.ISCC.dto.RequestDeniedDTO;
import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.ClinicAdministrator;
import isapsw.tim43.ISCC.model.Patient;
import isapsw.tim43.ISCC.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/center/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClinicCenterAdministratorController {

    @Autowired
    PatientService patientService;

    @Autowired
    ClinicCenterAdministratorService clinicCenterAdminService;

    @Autowired
    EmailService emailService;

    @Autowired
    ClinicService clinicService;

    @Autowired
    ClinicAdministratorService clinicAdministratorService;



    @PutMapping(value = "/activate/{id}")
    public ResponseEntity<PatientDTO> activateAccount(@PathVariable Long id) {

        Patient patient = patientService.findById(id);

        patient.setStatus("activated");
        patientService.save(patient);
        return new ResponseEntity<>(new PatientDTO(patient), HttpStatus.OK);
    }

    @PutMapping(value = "/accept/{id}")
    public ResponseEntity<PatientDTO> acceptRequest(@PathVariable Long id) throws InterruptedException {

        Patient patient = patientService.findById(id);

        patient.setStatus("accepted");

        try {
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        patientService.save(patient);
        return new ResponseEntity<>(new PatientDTO(patient), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/deny/{id}", consumes = "application/json")
    public void denyRequest(@RequestBody RequestDeniedDTO requestDeniedDTO, @PathVariable Long id) throws InterruptedException {

        patientService.remove(id);

        //emailService.sendNotificationAsync(requestDeniedDTO.getEmail(), requestDeniedDTO.getExplanation());
    }

    @PostMapping(value = "/register/clinic", consumes = "application/json")
    public ResponseEntity<ClinicDTO> registerClinic(@RequestBody ClinicDTO clinicDTO){

        if (clinicDTO.getName() == null || clinicDTO.getName().isEmpty() || clinicDTO.getAddress() == null ||
                clinicDTO.getAddress().isEmpty() || clinicDTO.getDescription() == null ||
                clinicDTO.getDescription().isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

        Clinic clinic = new Clinic();
        clinic.setName(clinicDTO.getName());
        clinic.setAddress(clinicDTO.getAddress());
        clinic.setDescription(clinicDTO.getDescription());
        clinic.setAverageRating(0.0);

        clinicService.save(clinic);
        return new ResponseEntity<>(new ClinicDTO(clinic), HttpStatus.CREATED);
    }

    @PostMapping(value = "/register/clinic/administrator", consumes = "application/json")
    public ResponseEntity<ClinicAdministratorDTO> registerClinicAdministrator(@RequestBody ClinicAdministratorDTO
                                                                                          clinicAdministratorDTO) {

        if (clinicAdministratorDTO.getEmail() == null || clinicAdministratorDTO.getEmail().isEmpty() ||
                clinicAdministratorDTO.getPassword() == null || clinicAdministratorDTO.getPassword().isEmpty() ||
                clinicAdministratorDTO.getFirstName() == null || clinicAdministratorDTO.getFirstName().isEmpty() ||
                clinicAdministratorDTO.getLastName() == null || clinicAdministratorDTO.getLastName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Clinic clinic = clinicService.findByName(clinicAdministratorDTO.getClinicName());

        ClinicAdministrator clinicAdministrator = new ClinicAdministrator();
        clinicAdministrator.setEmail(clinicAdministratorDTO.getEmail());
        clinicAdministrator.setPassword(clinicAdministratorDTO.getPassword());
        clinicAdministrator.setFirstName(clinicAdministratorDTO.getFirstName());
        clinicAdministrator.setLastName(clinicAdministratorDTO.getLastName());
        clinicAdministrator.setPhoneNumber(clinicAdministratorDTO.getPhoneNumber());
        clinicAdministrator.setAddress(clinicAdministratorDTO.getAddress());
        clinicAdministrator.setCity(clinicAdministratorDTO.getCity());
        clinicAdministrator.setState(clinicAdministratorDTO.getState());
        clinicAdministrator.setClinic(clinic);

        clinicAdministratorService.save( clinicAdministrator );
        return new ResponseEntity<>( new ClinicAdministratorDTO( clinicAdministrator ), HttpStatus.CREATED );
    }
}
