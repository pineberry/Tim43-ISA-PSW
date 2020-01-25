package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByHealthCareNumber(String healthCareNumber);

    List<Patient> findByFirstNameAndLastName(String name, String lastname);

    List<Patient> findPatientByFirstName(String name);

    List<Patient> findPatientByLastName(String lastName);
}
