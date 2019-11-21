package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.ProcedureTypeDTO;
import isapsw.tim43.ISCC.model.ProcedureType;
import isapsw.tim43.ISCC.service.ProcedureTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/procedure/type")
public class ProcedureTypeController {

        @Autowired
        private ProcedureTypeService procedureTypeService;

        @PostMapping(value = "/add", consumes = "application/json")
        public ResponseEntity<ProcedureTypeDTO> saveProcedureType(@RequestBody ProcedureTypeDTO procedureTypeDTO){

            procedureTypeDTO = procedureTypeService.save(procedureTypeDTO);

            if(procedureTypeDTO != null){
                return new ResponseEntity<>(procedureTypeDTO, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }
}
