package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.MedicalRecordDTO;
import isapsw.tim43.ISCC.model.MedicalRecord;
import isapsw.tim43.ISCC.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public MedicalRecord save(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    public void remove(Long id) {
        medicalRecordRepository.deleteById(id);
    }

    public MedicalRecord findById(Long id) {
        return medicalRecordRepository.findById(id).orElse(null);
    }

    public MedicalRecordDTO editMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
        if (medicalRecordDTO.getHeight() < 0 || medicalRecordDTO.getWeight() < 0) {
            return null;
        }
        MedicalRecord medicalRecord = findById(medicalRecordDTO.getId());

        if (medicalRecord == null) {
            return null;
        }

        medicalRecord.setWeight(medicalRecordDTO.getWeight());
        medicalRecord.setHeight(medicalRecordDTO.getHeight());
        medicalRecord.setBloodType(medicalRecordDTO.getBloodType());
        medicalRecord.setDateOfBirth(medicalRecordDTO.getDateOfBirth());
        save(medicalRecord);

        return modelToDto(medicalRecord);
    }


    public MedicalRecordDTO modelToDto(MedicalRecord medicalRecord) {
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();

        medicalRecordDTO.setId(medicalRecord.getId());
        medicalRecordDTO.setBloodType(medicalRecord.getBloodType());
        medicalRecordDTO.setHeight(medicalRecord.getHeight());
        medicalRecordDTO.setWeight(medicalRecord.getWeight());
        medicalRecordDTO.setPatientEmail(medicalRecord.getPatient().getEmail());
        medicalRecordDTO.setDateOfBirth(medicalRecord.getDateOfBirth());
        medicalRecordDTO.setReports(medicalRecord.getReports());

        return  medicalRecordDTO;
    }
}
