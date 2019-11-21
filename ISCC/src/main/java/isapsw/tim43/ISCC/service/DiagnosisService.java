package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.model.Diagnosis;
import isapsw.tim43.ISCC.model.Medicine;
import isapsw.tim43.ISCC.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosisService {

    @Autowired
    DiagnosisRepository diagnosisRepository;

    public Diagnosis save(Diagnosis diagnosis) {
        return diagnosisRepository.save(diagnosis);
    }

    public void remove(Long id) {
        diagnosisRepository.deleteById(id);
    }

    public Diagnosis findByCode(String code) {
        return diagnosisRepository.findByCode(code);
    }
}
