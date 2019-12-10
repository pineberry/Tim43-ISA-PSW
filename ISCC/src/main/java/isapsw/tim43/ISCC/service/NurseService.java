package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.NurseDTO;
import isapsw.tim43.ISCC.dto.PrescriptionDTO;
import isapsw.tim43.ISCC.model.Nurse;
import isapsw.tim43.ISCC.model.Prescription;
import isapsw.tim43.ISCC.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NurseService {

    @Autowired
    NurseRepository nurseRepository;

    @Autowired
    PrescriptionService prescriptionService;

    public Nurse save(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    public void remove(Long id) {
        nurseRepository.deleteById(id);
    }

    public Nurse findByEmail(String email) {
        return nurseRepository.findByEmail(email);
    }

    public List<PrescriptionDTO> uncheckedPrescriptions(NurseDTO nurseDTO) {
        List<PrescriptionDTO> prescriptionDTOs = new ArrayList<PrescriptionDTO>();
        Nurse nurse = nurseRepository.findByEmail(nurseDTO.getEmail());
        if (nurse == null) {
            return null;
        }
        List<Prescription> prescriptions = prescriptionService.findAllByClinic(nurse.getClinic());
        for (Prescription prescription : prescriptions) {
            PrescriptionDTO prescriptionDTO = prescriptionService.modelToDto(prescription);
            if (!prescriptionDTO.isChecked())
                prescriptionDTOs.add(prescriptionDTO);
        }

        return prescriptionDTOs;
    }

    public List<PrescriptionDTO> checkPrescription(NurseDTO nurseDTO, Long id) {
        Nurse nurse = nurseRepository.findByEmail(nurseDTO.getEmail());
        if (nurse == null) {
            return null;
        }

        Prescription prescription = prescriptionService.findById(id);
        if (prescription != null) {
            prescription.setChecked(true);
            prescription.setNurse(nurse);
            prescriptionService.save(prescription);
        }

        return getCheckedPrescription(nurseDTO);
    }

    public List<PrescriptionDTO> getCheckedPrescription(NurseDTO nurseDTO) {
        Nurse nurse = nurseRepository.findByEmail(nurseDTO.getEmail());
        if (nurse == null) {
            return null;
        }

        List<Prescription> prescriptions = prescriptionService.findAllByNurse(nurse);
        List<PrescriptionDTO> prescriptionDTOS = new ArrayList<PrescriptionDTO>();
        for (Prescription prescription : prescriptions) {
            PrescriptionDTO prescriptionDTO = prescriptionService.modelToDto(prescription);
            prescriptionDTOS.add(prescriptionDTO);
        }

        return prescriptionDTOS;
    }

}
