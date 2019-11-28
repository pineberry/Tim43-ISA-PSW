package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.DiagnosisDTO;
import isapsw.tim43.ISCC.dto.MedicineDTO;
import isapsw.tim43.ISCC.model.Diagnosis;
import isapsw.tim43.ISCC.model.Medicine;
import isapsw.tim43.ISCC.service.CodebookValidationService;
import isapsw.tim43.ISCC.service.DiagnosisService;
import isapsw.tim43.ISCC.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/codebook")
public class CodebookContoller {

    @Autowired
    MedicineService medicineService;

    @Autowired
    DiagnosisService diagnosisService;

    @Autowired
    CodebookValidationService codebookValidationService;

    @PostMapping(value = "/medicine", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicineDTO> addMedicine(@RequestBody MedicineDTO medicineDTO) {
        Medicine medicine = new Medicine();
        medicine.setName(medicineDTO.getName());
        medicine.setCode(medicineDTO.getCode());

        if (!codebookValidationService.validateMedicine(medicine)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        medicineService.save(medicine);
        return new ResponseEntity<>(new MedicineDTO(medicine), HttpStatus.CREATED);
    }

    @PostMapping(value = "/diagnosis", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DiagnosisDTO> addDiagnosis(@RequestBody DiagnosisDTO diagnosisDTO) {

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setName(diagnosisDTO.getName());
        diagnosis.setLatinName(diagnosisDTO.getLatinName());
        diagnosis.setCode((diagnosisDTO.getCode()));

        if(!codebookValidationService.validateDiagnosis(diagnosis)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        diagnosisService.save(diagnosis);
        return new ResponseEntity<>(new DiagnosisDTO(diagnosis), HttpStatus.CREATED);
    }

    @GetMapping(value = "/diagnoses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DiagnosisDTO>> getDiagnosis() {
        List<Diagnosis> diagnoses = diagnosisService.findAll();
        List<DiagnosisDTO> diagnosisDTOList = new ArrayList<DiagnosisDTO>();
        for (Diagnosis diag : diagnoses) {
            diagnosisDTOList.add(new DiagnosisDTO(diag));
        }

        return new ResponseEntity<>(diagnosisDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/medicines", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicineDTO>> getMedicines() {
        List<Medicine> medicines = medicineService.findAll();
        List<MedicineDTO> medicineDTOList = new ArrayList<MedicineDTO>();
        for (Medicine medicine : medicines) {
            medicineDTOList.add(new MedicineDTO(medicine));
        }

        return new ResponseEntity<>(medicineDTOList, HttpStatus.OK);
    }
}
