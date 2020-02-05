package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.ClinicDTO;
import isapsw.tim43.ISCC.dto.DoctorDTO;
import isapsw.tim43.ISCC.dto.MedicalRoomDTO;
import isapsw.tim43.ISCC.model.*;
import isapsw.tim43.ISCC.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClinicService {

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    ClinicAdministratorService clinicAdministratorService;

    @Autowired
    MedicalRoomService medicalRoomService;

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

    public double getClinicIncome(Long id, Date dateStart, Date dateEnd) {
        ClinicAdministrator clinicAdministrator = clinicAdministratorService.findOne(id);
        Clinic clinic = clinicAdministrator.getClinic();

        double income = 0;

        for (MedicalRoom medicalRoom: clinic.getMedicalRooms()) {
            for (MedicalProcedure medicalProcedure: medicalRoom.getMedicalProcedures()) {
                if (medicalProcedure.getDateOfProcedure().compareTo(dateStart) >= 0 &&
                        medicalProcedure.getDateOfProcedure().compareTo(dateEnd) <=0) {
                    income += medicalProcedure.getPrice() -
                                            (medicalProcedure.getPrice() * (medicalProcedure.getDiscount()/100));
                }
            }
        }

        return income;
    }

    public ClinicDTO getClinicReport(Long id){
        ClinicAdministrator clinicAdministrator = clinicAdministratorService.findOne(id);
        Clinic clinic = clinicAdministrator.getClinic();

        ClinicDTO clinicDTO = new ClinicDTO(clinic);
        clinicDTO.setDoctors(clinic.getDoctors());
        Map<String, Integer> map = new HashMap<String, Integer>();
        int day = 0;
        int week = 0;
        int month = 0;

        Date today = new Date();

        for (MedicalRoom room: clinic.getMedicalRooms()) {
            for (MedicalProcedure medicalProcedure: room.getMedicalProcedures()) {
                if (medicalProcedure.getDateOfProcedure().compareTo(today) == 0) {
                    day++;
                }

                if (dateOfWeek(medicalProcedure.getDateOfProcedure())) {
                    week++;
                }

                if (dateOfMonth(medicalProcedure.getDateOfProcedure())) {
                    month++;
                }
            }
        }

        map.put("day", day);
        map.put("week", week);
        map.put("month", month);

        clinicDTO.setNumberOfProcedures(map);
        return clinicDTO;
    }

    private boolean dateOfWeek(Date date) {
        Calendar currentCalendar = Calendar.getInstance();
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return week == targetWeek && year == targetYear;
    }

    private boolean dateOfMonth(Date date) {
        Calendar currentCalendar = Calendar.getInstance();
        int month = currentCalendar.get(Calendar.MONTH);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetMonth = targetCalendar.get(Calendar.MONTH);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return month == targetMonth && year == targetYear;
    }
}
