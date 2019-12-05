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

            if (procedureTypeDTO != null) {
                return new ResponseEntity<>(procedureTypeDTO, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }

        @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<ProcedureTypeDTO>> getAllTypes(){
            List<ProcedureTypeDTO> procedureTypeDTOList = procedureTypeService.findAll();
            return new ResponseEntity<>(procedureTypeDTOList, HttpStatus.OK);
        }

        @DeleteMapping(value = "/{id}")
        public ResponseEntity<Void> deleteType(@PathVariable Long id){
            if (procedureTypeService.remove(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PutMapping(value = "/update", consumes = "application/json")
        public ResponseEntity<ProcedureTypeDTO> updateType(@RequestBody ProcedureTypeDTO procedureTypeDTO){
            procedureTypeDTO = procedureTypeService.update(procedureTypeDTO);

            if (procedureTypeDTO != null) {
                return new ResponseEntity<ProcedureTypeDTO>(procedureTypeDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        @GetMapping(value = "/search/{typeName}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<ProcedureTypeDTO>> searchTypes(@PathVariable String typeName){
            List<ProcedureTypeDTO> procedureTypeDTOList = procedureTypeService.searchTypeByBame(typeName);
            return new ResponseEntity<>(procedureTypeDTOList, HttpStatus.OK);
        }

        @GetMapping(value = "/{id}", produces = "application/json")
        public ResponseEntity<ProcedureTypeDTO> getTypeById(@PathVariable long id){
            ProcedureType procedureType = procedureTypeService.findOne(id);
            return new ResponseEntity<>(new ProcedureTypeDTO(procedureType), HttpStatus.OK);
        }

}
