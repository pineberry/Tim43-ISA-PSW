package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.MedicalRecord;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class MedicalRecordDTO {


    private Long id;
    private String patientEmail;
    private float height;
    private float weight;
    private Date dateOfBirth;
    private String bloodType;
    private List<ReportDTO> reports;
    private PatientDTO patientDTO;
    
    public MedicalRecordDTO() {
    }

    public MedicalRecordDTO(Long id, String patientEmail, float height, float weight, Date dateOfBirth,
                            String bloodType, List<ReportDTO> reports) {
        this.id = id;
        this.patientEmail = patientEmail;
        this.height = height;
        this.weight = weight;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.reports = reports;
    }

    public MedicalRecordDTO(MedicalRecord medicalRecord) {
        this.id = medicalRecord.getId();
        this.patientDTO = new PatientDTO(medicalRecord.getPatient());
        this.height = medicalRecord.getHeight();
        this.weight = medicalRecord.getWeight();
        this.dateOfBirth = medicalRecord.getDateOfBirth();
        this.bloodType = medicalRecord.getBloodType();
        this.reports = null;
        this.patientDTO = new PatientDTO(medicalRecord.getPatient());
    }
}

