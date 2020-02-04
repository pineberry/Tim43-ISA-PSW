package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.ClinicCenterAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicCenterAdministratorRepository extends JpaRepository<ClinicCenterAdministrator, Long> {

     ClinicCenterAdministrator findByEmail(String email);
}
