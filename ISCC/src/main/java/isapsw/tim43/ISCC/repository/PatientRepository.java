package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
