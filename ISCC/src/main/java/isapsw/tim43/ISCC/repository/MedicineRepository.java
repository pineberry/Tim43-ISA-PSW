package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    public Medicine findByCode(String code);
}
