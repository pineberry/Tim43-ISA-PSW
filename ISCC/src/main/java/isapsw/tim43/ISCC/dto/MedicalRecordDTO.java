package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.Patient;
import lombok.Data;

import java.util.ArrayList;
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
    private List<ReportDTO> reports = new ArrayList<ReportDTO>();

    public MedicalRecordDTO() {
    }

    public MedicalRecordDTO(Long id, String patientEmail, float height, float weight, Date dateOfBirth,
                            String bloodType) {
        this.id = id;
        this.patientEmail = patientEmail;
        this.height = height;
        this.weight = weight;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
    }
}

