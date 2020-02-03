package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.List;

public interface MedicalProcedureRepository extends JpaRepository<MedicalProcedure, Long> {

    @Query("select mp from MedicalProcedure mp where mp.medicalRoom is null")
    List<MedicalProcedure> findAllWithoutRoom();

    public List<MedicalProcedure> findByDoctor(Doctor doctor);
}
