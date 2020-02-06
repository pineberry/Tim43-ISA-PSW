package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {

}
