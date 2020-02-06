package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.NurseDTO;
import isapsw.tim43.ISCC.dto.PrescriptionDTO;
import isapsw.tim43.ISCC.dto.UserDTO;
import isapsw.tim43.ISCC.model.MedicalRecord;
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

    @GetMapping(value = "/checked/prescriptions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PrescriptionDTO>> getCheckedPrescritpions(@PathVariable Long id) {
        List<PrescriptionDTO> prescriptionDTOS = nurseService.checkedPrescriptions(id);
        return new ResponseEntity<>(prescriptionDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NurseDTO> getNurse(@PathVariable Long id) {
        NurseDTO nurseDTO = nurseService.getNurse(id);

        if (nurseDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(nurseDTO, HttpStatus.OK);
    }


    @PutMapping(value = "/check/prescription/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PrescriptionDTO>> checkPrescription(@RequestBody NurseDTO nurseDTO, @PathVariable Long id) {

        List<PrescriptionDTO> prescriptionDTOS = nurseService.checkPrescription(nurseDTO, id);

        if(prescriptionDTOS == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(prescriptionDTOS, HttpStatus.OK);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NurseDTO> editNurse(@RequestBody NurseDTO nurseDTO){
        nurseDTO = nurseService.update(nurseDTO);

        if (nurseDTO != null) {
            return new ResponseEntity<>(nurseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = "/change/password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> changePassword(@RequestBody UserDTO userDTO){
        userDTO = nurseService.changePassword(userDTO);

        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<NurseDTO> addNurse(@RequestBody NurseDTO nurseDTO) {
        NurseDTO nurseDTO1 = nurseService.addNurse(nurseDTO);

        if(nurseDTO1 != null) {
            return new ResponseEntity<>(nurseDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
