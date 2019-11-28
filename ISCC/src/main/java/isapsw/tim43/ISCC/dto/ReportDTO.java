package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.Medicine;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ReportDTO {

    private Long id;
    private String notes;
    private Long doctor;
    private String diagnosis;
    private List<String> medicines = new ArrayList<String>();

    public ReportDTO() {
    }

    public ReportDTO(Long id, String notes, Long doctor, String diagnosis) {
        this.id = id;
        this.notes = notes;
        this.diagnosis = diagnosis;
        this.doctor = doctor;
    }
}
