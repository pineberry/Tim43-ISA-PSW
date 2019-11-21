package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.PatientDTO;
import isapsw.tim43.ISCC.model.Patient;
import isapsw.tim43.ISCC.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {

        Patient patient = patientService.findById(id);

        return new ResponseEntity<>(new PatientDTO(patient), HttpStatus.OK);
    }
}
