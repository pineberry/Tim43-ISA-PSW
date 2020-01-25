package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.MedicalRecordDTO;
import isapsw.tim43.ISCC.model.MedicalRecord;
import isapsw.tim43.ISCC.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/record")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalRecordDTO> editMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        MedicalRecordDTO medicalRecordDTO1 = medicalRecordService.editMedicalRecord(medicalRecordDTO);

        if (medicalRecordDTO1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(medicalRecordDTO1, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicalRecordDTO> getMedicalRecord(@PathVariable Long id) {
        MedicalRecord medicalRecord = medicalRecordService.findById(id);
        MedicalRecordDTO medicalRecordDTO = medicalRecordService.modelToDto(medicalRecord);

        return new ResponseEntity<>(medicalRecordDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/patient/{id}")
    public ResponseEntity<MedicalRecordDTO> getMPatientMedicalRecord(@PathVariable Long id) {
        MedicalRecordDTO medicalRecordDTO = medicalRecordService.getPatientRecord(id);

        if (medicalRecordDTO != null) {
            return new ResponseEntity<>(medicalRecordDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
