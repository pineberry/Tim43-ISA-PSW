package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.Prescription;
import isapsw.tim43.ISCC.model.Report;
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
    private MedicalRecordDTO medicalRecord;

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

    public ReportDTO(Report report){
        this.id = report.getId();
        this.notes = report.getNotes();
        this.diagnosis = report.getDiagnosis().getName();
        this.doctor = new DoctorDTO(report.getDoctor());
        for(Prescription presc : report.getPrescription()) {
            this.medicines.add(presc.getMedicine().getName());
        }
        this.medicalRecord = new MedicalRecordDTO(report.getRecord());
    }
}
