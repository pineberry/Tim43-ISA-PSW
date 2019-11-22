package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.ProcedureTypeDTO;
import isapsw.tim43.ISCC.model.ProcedureType;
import isapsw.tim43.ISCC.service.ProcedureTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/procedure/type")
public class ProcedureTypeController {

        @Autowired
        private ProcedureTypeService procedureTypeService;

        @PostMapping(value = "/add", consumes = "application/json")
        public ResponseEntity<ProcedureTypeDTO> addProcedureType(@RequestBody ProcedureTypeDTO procedureTypeDTO){

            procedureTypeDTO = procedureTypeService.save(procedureTypeDTO);

            if(procedureTypeDTO != null){
                return new ResponseEntity<>(procedureTypeDTO, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }

        @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<ProcedureTypeDTO>> getAllTypes(){
            List<ProcedureTypeDTO> procedureTypeDTOList = procedureTypeService.findAll();
            return new ResponseEntity<>(procedureTypeDTOList, HttpStatus.OK);
        }
}
