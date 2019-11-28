package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.model.Medicine;
import isapsw.tim43.ISCC.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    MedicineRepository medicineRepository;

    public Medicine save(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public void remove(Long id) {
        medicineRepository.deleteById(id);
    }

    public Medicine findByCode(String code) {
        return medicineRepository.findByCode(code);
    }

    public List<Medicine> findAll() { return medicineRepository.findAll(); }
}
