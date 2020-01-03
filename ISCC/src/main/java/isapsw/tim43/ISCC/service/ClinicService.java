package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.ClinicDTO;
import isapsw.tim43.ISCC.dto.DoctorDTO;
import isapsw.tim43.ISCC.dto.MedicalRoomDTO;
import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalRoom;
import isapsw.tim43.ISCC.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClinicService {

    @Autowired
    ClinicRepository clinicRepository;

    public Clinic save(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    public Clinic findByName(String name) {
        return clinicRepository.findByName(name);
    }

    public void remove(Long id) {
        clinicRepository.deleteById(id);
    }

    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    public Clinic findOne(long id) {return clinicRepository.findById(id).orElseGet(null);}

    public ClinicDTO edit(ClinicDTO clinicDTO){
        Clinic clinic = findByName(clinicDTO.getName());

        if (clinic == null || clinicDTO.getName() == null || clinicDTO.getName().isEmpty()
                ||  clinicDTO.getName() == null || clinicDTO.getAddress().isEmpty()
                || clinicDTO.getDescription() == null || clinicDTO.getDescription().isEmpty()){
            return null;
        }

        clinic.setName(clinicDTO.getName());
        clinic.setDescription(clinicDTO.getDescription());
        clinic.setAddress(clinicDTO.getAddress());

        clinic = save(clinic);
        return new ClinicDTO(clinic);
    }

    public ClinicDTO getClinic(String name){
        Clinic clinic = findByName(name);

        if (clinic == null){
            return null;
        }

        return new ClinicDTO(clinic);
    }

    public List<MedicalRoomDTO> getRooms(String name){
        Clinic clinic = findByName(name);
        List<MedicalRoomDTO> medicalRoomDTOList = new ArrayList<MedicalRoomDTO>();

        if (clinic == null) {
            return null;
        }

        for (MedicalRoom medicalRoom: clinic.getMedicalRooms()) {
            medicalRoomDTOList.add(new MedicalRoomDTO(medicalRoom));
        }

        return medicalRoomDTOList;
    }

    public List<DoctorDTO> getDoctors(String name){
        Clinic clinic = findByName(name);
        List<DoctorDTO> doctorDTOList = new ArrayList<DoctorDTO>();

        if (clinic == null) {
            return null;
        }

        for (Doctor doctor: clinic.getDoctors()) {
            doctorDTOList.add(new DoctorDTO(doctor));
        }

        return doctorDTOList;
    }
}
