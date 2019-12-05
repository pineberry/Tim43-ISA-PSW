package isapsw.tim43.ISCC.dto;

import lombok.Data;

@Data
public class PrescriptionDTO {

    private Long id;
    private String medication;
    private String patientsName;
    private String patientsSurname;
    private String clinicName;
    private boolean checked;
    private String nurseEmail;

    public PrescriptionDTO() {
    }

    public PrescriptionDTO(Long id, String medication, String patientsName, String patientsSurname,String clinicName,
                           boolean checked, String nurseEmail) {
        this.id = id;
        this.medication = medication;
        this.patientsName = patientsName;
        this.patientsSurname = patientsSurname;
        this.clinicName = clinicName;
        this.checked = checked;
        this.nurseEmail = nurseEmail;
    }
}
