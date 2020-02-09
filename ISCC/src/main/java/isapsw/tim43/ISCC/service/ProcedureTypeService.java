package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.ProcedureTypeDTO;
import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.ClinicAdministrator;
import isapsw.tim43.ISCC.model.ProcedureType;
import isapsw.tim43.ISCC.repository.ProcedureTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProcedureTypeService {

        @Autowired
        private ProcedureTypeRepository procedureTypeRepository;

        @Autowired
        private ClinicAdministratorService clinicAdministratorService;

        public ProcedureTypeDTO save(ProcedureTypeDTO procedureTypeDTO) {

            if (procedureTypeDTO.getTypeName() == null || procedureTypeDTO.getTypeName().isEmpty()
                    || procedureTypeDTO.getTypeDescription() == null || procedureTypeDTO.getTypeDescription().isEmpty()
                    || procedureTypeDTO.getPrice() < 0)
            {
                return null;
            }

            ProcedureType procedureType = new ProcedureType();
            procedureType.setTypeName(procedureTypeDTO.getTypeName());
            procedureType.setTypeDescription(procedureTypeDTO.getTypeDescription());
            procedureType.setPrice(procedureTypeDTO.getPrice());

            procedureType = procedureTypeRepository.save(procedureType);

            return new ProcedureTypeDTO(procedureType);
        }

        public ProcedureType findOne(long id){
        	Optional<ProcedureType> procedureType = procedureTypeRepository.findById(id);
        	if(procedureType.isPresent())
        	{
        		return procedureType.get();
        	} else
        		return null;
        }

        public List<ProcedureTypeDTO> searchTypeByName(String name, Long id) {
            ClinicAdministrator clinicAdministrator = clinicAdministratorService.findOne(id);
            List<ProcedureTypeDTO> procedureTypeDTOList = new ArrayList<ProcedureTypeDTO>();
            List<ProcedureType> procedureTypes = procedureTypeRepository.findProcedureTypeByTypeName(name);

            for (ProcedureType procedureType : procedureTypes) {
                if (procedureType.getClinics().contains(clinicAdministrator.getClinic())) {
                    ProcedureTypeDTO procedureTypeDTO = new ProcedureTypeDTO(procedureType);
                    procedureTypeDTOList.add(procedureTypeDTO);
                }
            }

            return  procedureTypeDTOList;
        }

        public List<ProcedureTypeDTO> findAll() {
            List<ProcedureType> procedureTypes = procedureTypeRepository.findAll();
            List<ProcedureTypeDTO> procedureTypeDTOList = new ArrayList<ProcedureTypeDTO>();

            for (ProcedureType procedureType: procedureTypes) {
                ProcedureTypeDTO procedureTypeDTO = new ProcedureTypeDTO(procedureType);
                procedureTypeDTOList.add(procedureTypeDTO);
            }

            return procedureTypeDTOList;
        }

        public List<ProcedureTypeDTO> findByClinic(Long id) {
            ClinicAdministrator clinicAdministrator = clinicAdministratorService.findOne(id);

            if (clinicAdministrator == null) {
                return null;
            }

            Clinic clinic = clinicAdministrator.getClinic();

            List<ProcedureTypeDTO> procedureTypeDTOList = new ArrayList<ProcedureTypeDTO>();
            List<ProcedureType> types = procedureTypeRepository.findAll();

            for (ProcedureType type: types) {
                if (type.getClinics().contains(clinic)) {
                    procedureTypeDTOList.add(new ProcedureTypeDTO(type));
                }
            }

            return procedureTypeDTOList;
        }

        public boolean remove(long id) {
            ProcedureType procedureType = findOne(id);

            if (procedureType == null || procedureType.getMedicalProcedures().size() != 0) {
                return false;
            }

            for (Clinic clinic: procedureType.getClinics()) {
                clinic.getTypes().remove(procedureType);
            }

            procedureTypeRepository.deleteById(id);
            return true;
        }

        public ProcedureTypeDTO update(ProcedureTypeDTO procedureTypeDTO){
            if (procedureTypeDTO.getTypeName() == null || procedureTypeDTO.getTypeName().isEmpty()
                    || procedureTypeDTO.getTypeDescription() == null || procedureTypeDTO.getTypeDescription().isEmpty()
                    || procedureTypeDTO.getPrice() < 0)
            {
                return null;
            }

            ProcedureType procedureType = findOne(procedureTypeDTO.getId());

            if (procedureType == null) {
                return null;
            }

            procedureType.setTypeName(procedureTypeDTO.getTypeName());
            procedureType.setTypeDescription(procedureTypeDTO.getTypeDescription());
            procedureType.setPrice(procedureTypeDTO.getPrice());

            procedureType = procedureTypeRepository.save(procedureType);

            return new ProcedureTypeDTO(procedureType);
        }
}
