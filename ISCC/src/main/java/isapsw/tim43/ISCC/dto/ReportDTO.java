package isapsw.tim43.ISCC.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportDTO {

    private Long id;
    private String notes;
    private DoctorDTO doctor;
    private String diagnosis;
    private List<String> medicines = new ArrayList<String>();
    private String doctorFirstName;
    private String doctorLastName;

    public ReportDTO() {
    }

    public ReportDTO(Long id, String notes, DoctorDTO doctor, String diagnosis, String doctorFirstName, String doctorLastName) {
        this.id = id;
        this.notes = notes;
        this.diagnosis = diagnosis;
        this.doctor = doctor;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
    }
}
