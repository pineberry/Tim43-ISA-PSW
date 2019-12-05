package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.model.Nurse;
import isapsw.tim43.ISCC.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NurseService {

    @Autowired
    NurseRepository nurseRepository;

    public Nurse save(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    public void remove(Long id) {
        nurseRepository.deleteById(id);
    }


}
