package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.Prescription;
import isapsw.tim43.ISCC.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public Prescription save(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public void remove(Long id) {
        prescriptionRepository.deleteById(id);
    }

    public void findByDoctor(Doctor doctor) {
    }
}
