package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.ReportDTO;
import isapsw.tim43.ISCC.model.Medicine;
import isapsw.tim43.ISCC.model.Prescription;
import isapsw.tim43.ISCC.model.Report;
import isapsw.tim43.ISCC.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private DiagnosisService diagnosisService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private ClinicService clinicService;

    public Report save(Report report) {
        return reportRepository.save(report);
    }

    public void remove(Long id) {
        reportRepository.deleteById(id);
    }

    public Report mapDTO(ReportDTO reportDTO) {
        Report report = new Report();

        report.setNotes(reportDTO.getNotes());
        report.setDiagnosis(diagnosisService.findByCode(reportDTO.getDiagnosis()));
        report.setDoctor(doctorService.findOne(reportDTO.getDoctor()));
        for (String code: reportDTO.getMedicines()) {
            Prescription prescription = new Prescription();
            Medicine medicine = medicineService.findByCode(code);
            prescription.setMedicine(medicine);
            prescription.setReport(report);
            prescription.setClinic(report.getDoctor().getClinic());
            prescriptionService.save(prescription);
        }


        return report;
    }
}
