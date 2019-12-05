package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    public List<Prescription> findByReport();
}
