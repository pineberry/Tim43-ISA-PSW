package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicService {

    @Autowired
    ClinicRepository clinicRepository;

    public Clinic save(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    public Clinic findByName(String name) {
        return clinicRepository.findByName(name);
    }

    public void remove(Long id) {
        clinicRepository.deleteById(id);
    }
}
