package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.model.Diagnosis;
import isapsw.tim43.ISCC.model.Medicine;
import isapsw.tim43.ISCC.repository.DiagnosisRepository;
import isapsw.tim43.ISCC.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodebookValidationService {

    @Autowired
    MedicineService medicineService;

    @Autowired
    DiagnosisService diagnosisService;

    public boolean validateMedicine(Medicine medicine) {
        if (medicine.getName() == null || medicine.getCode() == null || medicine.getName().isEmpty() ||
        medicine.getCode().isEmpty()) {
            return false;
        }

        Medicine medicine1 = medicineService.findByCode(medicine.getCode());

        if(medicine1 != null) {
            return false;
        }

        return true;
    }

    public boolean validateDiagnosis(Diagnosis diagnosis) {

        if (diagnosis.getName() == null || diagnosis.getLatinName() == null || diagnosis.getCode() == null ||
            diagnosis.getName().isEmpty() || diagnosis.getLatinName().isEmpty() || diagnosis.getCode().isEmpty()) {
            return  false;
        }

        Diagnosis diagnosis1 = diagnosisService.findByCode(diagnosis.getCode());

        if(diagnosis1 != null) {
            return false;
        }

        return true;
    }
}
