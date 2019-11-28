package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.model.MedicalRecord;
import isapsw.tim43.ISCC.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public MedicalRecord save(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    public void remove(Long id) {
        medicalRecordRepository.deleteById(id);
    }
}
