package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.ClinicAdministratorDTO;
import isapsw.tim43.ISCC.dto.ClinicCenterAdministratorDTO;
import isapsw.tim43.ISCC.service.ClinicAdministratorService;
import isapsw.tim43.ISCC.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clinic/admin")
public class ClinicAdministratorController {

    @Autowired
    private ClinicAdministratorService clinicAdministratorService;

    @GetMapping(value = "/email")
    public ResponseEntity<ClinicAdministratorDTO> getPersonalData(@RequestParam(name = "email") String email){
        ClinicAdministratorDTO clinicCenterAdministratorDTO = clinicAdministratorService.findByEmail(email);

        if (clinicCenterAdministratorDTO != null) {
            return new ResponseEntity<>(clinicCenterAdministratorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClinicAdministratorDTO> getPersonalData(@PathVariable Long id){
        ClinicAdministratorDTO clinicCenterAdministratorDTO = clinicAdministratorService.findById(id);

        if (clinicCenterAdministratorDTO != null) {
            return new ResponseEntity<>(clinicCenterAdministratorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<ClinicAdministratorDTO> updateProfile(
                                                            @RequestBody ClinicAdministratorDTO clinicAdministratorDTO){
        clinicAdministratorDTO = clinicAdministratorService.save(clinicAdministratorDTO);
        if (clinicAdministratorDTO != null){
            return new ResponseEntity<>(clinicAdministratorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
