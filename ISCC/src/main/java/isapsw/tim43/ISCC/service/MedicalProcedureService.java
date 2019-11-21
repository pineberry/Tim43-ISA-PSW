package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.repository.MedicalProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalProcedureService {

    @Autowired
    private MedicalProcedureRepository medicalProcedureRepository;

    public MedicalProcedureDTO save(MedicalProcedureDTO medicalProcedureDTO){
        if(medicalProcedureDTO.getProcedureType() == null || medicalProcedureDTO.getDateOfProcedure() == null ||
                medicalProcedureDTO.getDoctor() == null || medicalProcedureDTO.getMedicalRoom() == null){
            return null;
        }

        MedicalProcedure medicalProcedure = new MedicalProcedure();
        medicalProcedure.setProcedureType(medicalProcedureDTO.getProcedureType());
        medicalProcedure.setDateOfProcedure(medicalProcedureDTO.getDateOfProcedure());
        medicalProcedure.setMedicalRoom(medicalProcedureDTO.getMedicalRoom());
        medicalProcedure.setDoctor(medicalProcedure.getDoctor());
        medicalProcedure.setPrice(medicalProcedure.getPrice());
        medicalProcedure.setDiscount(0);
        medicalProcedure.setBooked(false);

        medicalProcedure = medicalProcedureRepository.save(medicalProcedure);
        return new MedicalProcedureDTO(medicalProcedure);
    }
}
