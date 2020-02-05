package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.DoctorDTO;
import isapsw.tim43.ISCC.dto.ReportDTO;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.Medicine;
import isapsw.tim43.ISCC.model.Prescription;
import isapsw.tim43.ISCC.model.Report;
import isapsw.tim43.ISCC.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        report.setDoctor(doctorService.findOne(reportDTO.getDoctor().getId()));
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

    public ReportDTO modelToDto(Report report) {

        ReportDTO reportDTO = new ReportDTO();

        reportDTO.setId(report.getId());
        reportDTO.setDiagnosis(report.getDiagnosis().getName());
        reportDTO.setDoctor(new DoctorDTO(report.getDoctor()));
        for (Prescription prescription :
             report.getPrescription()) {
            reportDTO.getMedicines().add(prescription.getMedicine().getName());
        }
        reportDTO.setNotes(report.getNotes());

        return reportDTO;
    }

    public ReportDTO editReport(ReportDTO reportDTO) {
        Report report = findById(reportDTO.getId());

        report.setNotes(reportDTO.getNotes());
        report.setDiagnosis(diagnosisService.findByCode(reportDTO.getDiagnosis()));
        report.setDoctor(doctorService.findOne(reportDTO.getDoctor().getId()));
        for (String code:
             reportDTO.getMedicines()) {
            boolean exist = false;
           for (Prescription prescription : report.getPrescription()) {
               if(prescription.getMedicine().getCode().equals(code)) {
                   exist = true;
                   break;
               }
           }
           if (!exist) {
               Prescription pres = new Prescription();
               Medicine medicine = medicineService.findByCode(code);
               pres.setMedicine(medicine);
               pres.setReport(report);
               pres.setClinic(report.getDoctor().getClinic());
               prescriptionService.save(pres);
           }
        }

        save(report);

        return modelToDto(report);
    }

    public List<Report> findAllByDoctor(Doctor doctor) {
        return reportRepository.findAllByDoctor(doctor);
    }

    public Report findById(Long id) { return reportRepository.findById(id).orElse(null); }
}
