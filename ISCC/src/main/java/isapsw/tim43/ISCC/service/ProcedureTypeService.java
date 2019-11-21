package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.ProcedureTypeDTO;
import isapsw.tim43.ISCC.model.ProcedureType;
import isapsw.tim43.ISCC.repository.ProcedureTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcedureTypeService {

        @Autowired
        private ProcedureTypeRepository procedureTypeRepository;

        public ProcedureTypeDTO save(ProcedureTypeDTO procedureTypeDTO){

            if (procedureTypeDTO.getTypeName() == null || procedureTypeDTO.getTypeName().isEmpty()
                    || procedureTypeDTO.getTypeDescription() == null || procedureTypeDTO.getTypeDescription().isEmpty())
            {
                return null;
            }

            ProcedureType procedureType = new ProcedureType();
            procedureType.setTypeName(procedureTypeDTO.getTypeName());
            procedureType.setTypeDescription(procedureTypeDTO.getTypeDescription());

            procedureType = procedureTypeRepository.save(procedureType);

            return new ProcedureTypeDTO(procedureType);
        }
}
