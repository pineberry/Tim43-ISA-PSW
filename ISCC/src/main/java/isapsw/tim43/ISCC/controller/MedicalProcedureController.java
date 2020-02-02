package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.service.MedicalProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/medical/procedure")
public class MedicalProcedureController {

    @Autowired
    private MedicalProcedureService medicalProcedureService;

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<MedicalProcedureDTO> saveMedicalProcedure(@RequestBody MedicalProcedureDTO medicalProcedureDTO){
        medicalProcedureDTO = medicalProcedureService.save(medicalProcedureDTO);

        if (medicalProcedureDTO != null){
            return new ResponseEntity<>(medicalProcedureDTO, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicalProcedureDTO> getProcedure(@PathVariable Long id){
        MedicalProcedureDTO medicalProcedureDTO = medicalProcedureService.getProcedureById(id);

        if (medicalProcedureDTO != null) {
            return new ResponseEntity<>(medicalProcedureDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{procedureId}/{roomId}")
    public ResponseEntity<Void> bookRoom(@PathVariable("procedureId") Long procedureId,
                                         @PathVariable("roomId") Long roomId) throws InterruptedException {
        if (medicalProcedureService.bookRoom(procedureId, roomId) != null){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/exam", consumes = "application/json")
    public ResponseEntity<Void> examinationRequest(@RequestBody MedicalProcedureDTO medicalProcedureDTO) throws InterruptedException {
        if (medicalProcedureService.scheduleExam(medicalProcedureDTO) != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/surgery", consumes = "application/json")
    public ResponseEntity<Void> surgeryRequest(@RequestBody MedicalProcedureDTO medicalProcedureDTO) throws InterruptedException {
        if (medicalProcedureService.scheduleSurgery(medicalProcedureDTO) != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
