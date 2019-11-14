package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.model.Patient;
import isapsw.tim43.ISCC.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public Patient findById(Long id) {
        Patient p = patientRepository.findById(id).orElse(null);
        return p;
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public void remove(Long id) {
        patientRepository.deleteById(id);
    }

}
