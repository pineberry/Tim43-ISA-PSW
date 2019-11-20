package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.ClinicDTO;
import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/clinic")
public class ClinicController {

    @Autowired
    ClinicService clinicService;

    @GetMapping(value = "/clinics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        List<Clinic> clinics = clinicService.findAll();
        List<ClinicDTO> clinicsDTO = new ArrayList<ClinicDTO>();
        for (Clinic clinic : clinics) {
            ClinicDTO clinicDTO = new ClinicDTO(clinic);
            clinicsDTO.add(clinicDTO);
        }

        return new ResponseEntity<>(clinicsDTO, HttpStatus.OK);
    }
}
