package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.MedicalRecord;
import isapsw.tim43.ISCC.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    MedicalRecord getMedicalRecordByPatient(Patient p);
}
