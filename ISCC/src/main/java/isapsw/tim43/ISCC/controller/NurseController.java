package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.NurseDTO;
import isapsw.tim43.ISCC.dto.PrescriptionDTO;
import isapsw.tim43.ISCC.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping(value = "/nurse")
public class NurseController {

    @Autowired
    NurseService nurseService;

    @PostMapping(value = "/unchecked/prescriptions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PrescriptionDTO>> getUncheckedPrescriptions(@RequestBody NurseDTO nurseDTO) {

        List<PrescriptionDTO> prescriptionDTOList = nurseService.uncheckedPrescriptions(nurseDTO);

        if(prescriptionDTOList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(prescriptionDTOList, HttpStatus.OK);
    }

    @PutMapping(value = "/check/prescription/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PrescriptionDTO>> checkPrescription(@RequestBody NurseDTO nurseDTO, @PathVariable Long id) {

        List<PrescriptionDTO> prescriptionDTOS = nurseService.checkPrescription(nurseDTO, id);

        if(prescriptionDTOS == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(prescriptionDTOS, HttpStatus.OK);
    }
}
