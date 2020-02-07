package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.PrescriptionDTO;
import isapsw.tim43.ISCC.dto.ReportDTO;
import isapsw.tim43.ISCC.model.*;
import isapsw.tim43.ISCC.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public Prescription save(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public void remove(Long id) {
        prescriptionRepository.deleteById(id);
    }

    public List<Prescription> findAllByClinic(Clinic clinic) {
        return prescriptionRepository.findAllByClinic(clinic);
    }

    public List<Prescription> findAllByNurse(Nurse nurse) {
        return prescriptionRepository.findAllByNurse(nurse);
    }

    public PrescriptionDTO modelToDto(Prescription prescription) {
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
        prescriptionDTO.setId(prescription.getId());
        prescriptionDTO.setMedication(prescription.getMedicine().getName());
        /*Patient patient = prescription.getReport().getRecord().getPatient();
        prescriptionDTO.setPatientsName(patient.getFirstName());
        prescriptionDTO.setPatientsSurname(patient.getLastName());*/
        prescriptionDTO.setReportDTO(new ReportDTO(prescription.getReport()));
        prescriptionDTO.setClinicName(prescription.getClinic().getName());
        if(prescription.getNurse() != null) {
            prescriptionDTO.setNurseEmail(prescription.getNurse().getEmail());
        }
        prescriptionDTO.setChecked(prescription.isChecked());

        return prescriptionDTO;
    }

    public Prescription findById(Long id) {
        return prescriptionRepository.findById(id).orElse(null);
    }
}
