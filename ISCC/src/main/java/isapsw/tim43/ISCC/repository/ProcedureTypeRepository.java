package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.ProcedureType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcedureTypeRepository extends JpaRepository<ProcedureType, Long> {

    List<ProcedureType> findProcedureTypeByTypeName(String typeName);
}
