package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.*;
import isapsw.tim43.ISCC.model.*;
import isapsw.tim43.ISCC.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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

    @Autowired
    CenterAdminValidationService centerAdminValidationService;

    @Autowired
    ProcedureTypeService procedureTypeService;



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
            emailService.sendNotificationAsync(patient.getEmail(), "Your account is accepted please activate on" +
                    " link bellow:\n\n http://localhost:8081/activateAccount/" + patient.getId());
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        patientService.save(patient);
        return new ResponseEntity<>(new PatientDTO(patient), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deny/{id}", consumes = "application/json")
    public void denyRequest(@RequestBody RequestDeniedDTO requestDeniedDTO, @PathVariable Long id) throws
            InterruptedException {

        patientService.remove(id);

        try {
            emailService.sendNotificationAsync(requestDeniedDTO.getEmail(), requestDeniedDTO.getExplanation());
        } catch ( Exception e ) {
            e.printStackTrace();
        }
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

        List<ProcedureType> types = new ArrayList<ProcedureType>();

        for (ProcedureType procedureType: clinicDTO.getTypes()) {
            procedureType = procedureTypeService.findOne(procedureType.getId());
            types.add(procedureType);
        }

        clinic.setTypes(types);

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

        Clinic clinic = clinicService.findOne(clinicAdministratorDTO.getClinic().getId());

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

    @PostMapping(value = "/center/administrator", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicCenterAdministratorDTO> addCenterAdministrator(@RequestBody ClinicCenterAdministratorDTO
                                                                               clinicCenterAdministratorDTO) {

        ClinicCenterAdministrator admin = new ClinicCenterAdministrator();

        admin.setEmail(clinicCenterAdministratorDTO.getEmail());
        admin.setPassword(clinicCenterAdministratorDTO.getPassword());
        admin.setFirstName(clinicCenterAdministratorDTO.getFirstName());
        admin.setLastName(clinicCenterAdministratorDTO.getLastName());
        admin.setPhoneNumber(clinicCenterAdministratorDTO.getPhoneNumber());
        admin.setAddress(clinicCenterAdministratorDTO.getAddress());
        admin.setCity(clinicCenterAdministratorDTO.getCity());
        admin.setState(clinicCenterAdministratorDTO.getState());
        admin.setFirstLogin(true);

        if (!centerAdminValidationService.validateAdministrator(admin)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        clinicCenterAdminService.save(admin);
        return new ResponseEntity<>(new ClinicCenterAdministratorDTO(admin), HttpStatus.CREATED);
    }

    @PostMapping(value = "/change/password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> changePassword(@RequestBody UserDTO userDTO) {

        ClinicCenterAdministrator admin = clinicCenterAdminService.findByEmail(userDTO.getEmail());

        if (admin != null) {
           if (userDTO.getPassword().equals(userDTO.getPasswordF())) {
               admin.setPassword(userDTO.getPassword());
               admin.setFirstLogin(false);
               clinicCenterAdminService.save(admin);
           }
           else {
               return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
           }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
