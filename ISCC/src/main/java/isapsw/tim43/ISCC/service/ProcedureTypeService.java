package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.ProcedureTypeDTO;
import isapsw.tim43.ISCC.model.ProcedureType;
import isapsw.tim43.ISCC.repository.ProcedureTypeRepository;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        public ProcedureType findOne(long id){
            return procedureTypeRepository.findById(id).orElseGet(null);
        }

        public List<ProcedureTypeDTO> searchTypeByBame(String name){
            List<ProcedureTypeDTO> procedureTypeDTOList = new ArrayList<ProcedureTypeDTO>();
            List<ProcedureType> procedureTypes = procedureTypeRepository.findProcedureTypeByTypeName(name);
            for (ProcedureType procedureType: procedureTypes) {
                ProcedureTypeDTO procedureTypeDTO = new ProcedureTypeDTO(procedureType);
                procedureTypeDTOList.add(procedureTypeDTO);
            }
            return  procedureTypeDTOList;
        }

        public List<ProcedureTypeDTO> findAll(){
            List<ProcedureType> procedureTypes = procedureTypeRepository.findAll();
            List<ProcedureTypeDTO> procedureTypeDTOList = new ArrayList<ProcedureTypeDTO>();

            for (ProcedureType procedureType: procedureTypes) {
                ProcedureTypeDTO procedureTypeDTO = new ProcedureTypeDTO(procedureType);
                procedureTypeDTOList.add(procedureTypeDTO);
            }

            return procedureTypeDTOList;
        }

        public boolean remove(long id){
            ProcedureType procedureType = findOne(id);

            if (procedureType == null || procedureType.getMedicalProcedures().size() != 0) {
                return false;
            }

            procedureTypeRepository.deleteById(id);
            return true;
        }

        public ProcedureTypeDTO update(ProcedureTypeDTO procedureTypeDTO){
            if (procedureTypeDTO.getTypeName() == null || procedureTypeDTO.getTypeName().isEmpty()
                    || procedureTypeDTO.getTypeDescription() == null || procedureTypeDTO.getTypeDescription().isEmpty())
            {
                return null;
            }

            ProcedureType procedureType = findOne(procedureTypeDTO.getId());

            if (procedureType == null) {
                return null;
            }

            procedureType.setTypeName(procedureTypeDTO.getTypeName());
            procedureType.setTypeDescription(procedureTypeDTO.getTypeDescription());

            procedureType = procedureTypeRepository.save(procedureType);

            return new ProcedureTypeDTO(procedureType);
        }
}
