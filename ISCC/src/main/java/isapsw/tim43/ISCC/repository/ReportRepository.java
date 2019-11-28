package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    
}
