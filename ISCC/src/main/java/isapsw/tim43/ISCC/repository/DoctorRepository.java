package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.ProcedureType;
import org.springframework.data.jpa.repository.JpaRepository;

import isapsw.tim43.ISCC.model.Doctor;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findAllBySpecialized(ProcedureType procedureType);
    public List<Doctor> findAllByClinic(Clinic clinic);
}
