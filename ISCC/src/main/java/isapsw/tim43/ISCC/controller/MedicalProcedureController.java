package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.BookOperationDTO;
import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.service.MedicalProcedureService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    
    @GetMapping(value = "/patient")
    public ResponseEntity<List<MedicalProcedureDTO>> getPatientsProcedures(@RequestParam(name="patient") Long patientID)
    {
    	if (medicalProcedureService.getPatientsProcedures(patientID).isEmpty()) 
    	{
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} else {
    		return new ResponseEntity<>(medicalProcedureService.getPatientsProcedures(patientID), HttpStatus.OK);
    	}
    }

    @GetMapping(value = "/doctor/{id}")
    public ResponseEntity<List<MedicalProcedureDTO>> getProceduresByDoctor(@PathVariable Long id) {
        List<MedicalProcedureDTO> medicalProcedureDTOS = medicalProcedureService.proceduresByDoctor(id);
        if(medicalProcedureDTOS != null) {
            return new ResponseEntity<>(medicalProcedureDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

    @PutMapping(value = "/book/operation", consumes = "application/json")
    public ResponseEntity<Void> bookOperation(@RequestBody BookOperationDTO operationDTO) throws InterruptedException{
        MedicalProcedureDTO medicalProcedureDTO = medicalProcedureService.bookOperationRoom(operationDTO.getProcedureId(),
                                                                    operationDTO.getRoomId(), operationDTO.getDoctorsId());

        if(medicalProcedureDTO != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping(value = "/appointment")
    public ResponseEntity<List<MedicalProcedureDTO>> confirmAppointment(@RequestBody MedicalProcedureDTO procedure) {
    	
    	List<MedicalProcedureDTO> procedures = medicalProcedureService.confirmAppointment(procedure);
    	
    	if (procedures.isEmpty()) 
    	{
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} else {
    		return new ResponseEntity<>(procedures, HttpStatus.OK);
    	}
    }
    
    @DeleteMapping(value = "/appointment/{id}/{patient}")
    public ResponseEntity<List<MedicalProcedureDTO>> denyAppointment(@PathVariable("id") Long procedureID, @PathVariable("patient") Long patientID) {
    	
    	List<MedicalProcedureDTO> procedures = medicalProcedureService.denyAppointment(procedureID, patientID);
    	
    	if (procedures.isEmpty()) 
    	{
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} else {
    		return new ResponseEntity<>(procedures, HttpStatus.OK);
    	}
    }

    @PutMapping(value = "/auto/book/{id}")
    public ResponseEntity<MedicalProcedureDTO> automaticallyBookRoom(@PathVariable("id") Long procedureId){
        MedicalProcedureDTO medicalProcedureDTO = medicalProcedureService.autoBookRoom(procedureId);

        if (medicalProcedureDTO != null) {
            return new ResponseEntity<>(medicalProcedureDTO, HttpStatus.OK);
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
