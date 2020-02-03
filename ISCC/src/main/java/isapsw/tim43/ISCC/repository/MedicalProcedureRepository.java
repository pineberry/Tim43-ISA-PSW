package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalProcedureRepository extends JpaRepository<MedicalProcedure, Long> {

    public List<MedicalProcedure> findByDoctor(Doctor doctor);
}
