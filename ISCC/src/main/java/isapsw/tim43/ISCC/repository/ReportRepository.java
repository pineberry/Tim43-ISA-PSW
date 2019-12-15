package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    public List<Report> findAllByDoctor(Doctor doctor);
}
