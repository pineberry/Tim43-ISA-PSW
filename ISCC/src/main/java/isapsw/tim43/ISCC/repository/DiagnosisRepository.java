package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    public Diagnosis findByCode(String code);
}
