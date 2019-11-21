package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.service.MedicalProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
