package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.model.MedicalRoom;
import isapsw.tim43.ISCC.model.ProcedureType;
import isapsw.tim43.ISCC.repository.MedicalProcedureRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalProcedureService {

    @Autowired
    private MedicalProcedureRepository medicalProcedureRepository;

    @Autowired
    private ProcedureTypeService procedureTypeService;

    @Autowired
    private MedicalRoomService medicalRoomService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private EmailService emailService;

    public MedicalProcedureDTO save(MedicalProcedureDTO medicalProcedureDTO){
        if(medicalProcedureDTO.getProcedureType() == null || medicalProcedureDTO.getDateOfProcedure() == null ||
                medicalProcedureDTO.getDoctor() == null) {
            return null;
        }

        MedicalRoom medicalRoom = null;

        if(medicalProcedureDTO.getMedicalRoom() != null)
            medicalRoom = medicalRoomService.findOne(medicalProcedureDTO.getMedicalRoom().getId());

        ProcedureType procedureType = procedureTypeService.findOne(medicalProcedureDTO.getProcedureType().getId());
        Doctor doctor = doctorService.findOne(medicalProcedureDTO.getDoctor().getId());

        if(procedureType == null || doctor == null){
            return null;
        }

        MedicalProcedure medicalProcedure = new MedicalProcedure();
        medicalProcedure.setProcedureType(procedureType);
        medicalProcedure.setDateOfProcedure(medicalProcedureDTO.getDateOfProcedure());
        medicalProcedure.setMedicalRoom(medicalRoom);
        medicalProcedure.setDoctor(doctor);
        medicalProcedure.setPrice(medicalProcedureDTO.getPrice());
        medicalProcedure.setDiscount(0);
        medicalProcedure.setBooked(false);
        medicalProcedure.setStartTime(medicalProcedureDTO.getStartTime());
        medicalProcedure.setEndTime(medicalProcedureDTO.getEndTime());

        medicalProcedure = medicalProcedureRepository.save(medicalProcedure);
        return new MedicalProcedureDTO(medicalProcedure);
    }
    
    public List<MedicalProcedure> findAll(){
    	return medicalProcedureRepository.findAll();
    }

    public MedicalProcedure findOne(Long id) {return medicalProcedureRepository.findById(id).orElseGet(null);}

    public List<MedicalProcedure> findByDoctor(Long id) {
        Doctor doctor = doctorService.findOne(id);
        return medicalProcedureRepository.findByDoctor(doctor);
    }

    public MedicalProcedureDTO getProcedureById(Long id){
        MedicalProcedure medicalProcedure = findOne(id);

        if (medicalProcedure == null){
            return null;
        }

        return new MedicalProcedureDTO(medicalProcedure);
    }

    public List<MedicalProcedureDTO> proceduresByDoctor(Long id) {
        List<MedicalProcedure> procedures = findByDoctor(id);

        List<MedicalProcedureDTO> medicalProcedureDTOS = new ArrayList<>();
        for (MedicalProcedure proc : procedures) {
            MedicalProcedureDTO medicalProcedureDTO = new MedicalProcedureDTO();
            medicalProcedureDTO.setDateOfProcedure(proc.getDateOfProcedure());
            medicalProcedureDTO.setStartTime(proc.getStartTime());
            medicalProcedureDTO.setEndTime(proc.getEndTime());
            medicalProcedureDTO.setBooked(proc.isBooked());
            medicalProcedureDTOS.add(medicalProcedureDTO);
        }

        return medicalProcedureDTOS;
    }

    public MedicalProcedure bookRoom(Long procedureId, Long roomId) throws InterruptedException {
        MedicalRoom medicalRoom = medicalRoomService.findOne(roomId);
        MedicalProcedure medicalProcedure = findOne(procedureId);

        if (medicalProcedure == null || medicalRoom == null) {
           return null;
        }

        medicalProcedure.setMedicalRoom(medicalRoom);

        String emailContent = "Test: Your appointment has been scheduled for " + medicalProcedure.getDateOfProcedure();
        emailService.sendNotificationAsync("isa.pws43@gmail.com", emailContent);

        return medicalProcedureRepository.save(medicalProcedure);
    }

    public MedicalProcedureDTO scheduleExam(MedicalProcedureDTO medicalProcedureDTO) throws InterruptedException {
        medicalProcedureDTO = save(medicalProcedureDTO);

        if (medicalProcedureDTO == null) {
            return null;
        }

        String emailContent = "Dr. " + medicalProcedureDTO.getDoctor().getFirstName() + " "
                + medicalProcedureDTO.getDoctor().getLastName() + " has requested an examination appointment for date: "
                + medicalProcedureDTO.getDateOfProcedure() + " in period from: " + medicalProcedureDTO.getStartTime()
                + " to " + medicalProcedureDTO.getEndTime() + "\n\n"
                + "To accept click on the link below:\n"
                + "http://localhost:8081/searchRooms/" + medicalProcedureDTO.getId();
        emailService.sendNotificationAsync("isa.pws43@gmail.com", emailContent);

        return medicalProcedureDTO;
    }

    public MedicalProcedureDTO scheduleSurgery(MedicalProcedureDTO medicalProcedureDTO) throws InterruptedException {
        medicalProcedureDTO = save(medicalProcedureDTO);

        if (medicalProcedureDTO == null) {
            return null;
        }

        String emailContent = "Dr. " + medicalProcedureDTO.getDoctor().getFirstName() + " "
                + medicalProcedureDTO.getDoctor().getLastName() + " has requested a surgery appointment for date: "
                + medicalProcedureDTO.getDateOfProcedure() + " in period from: " + medicalProcedureDTO.getStartTime()
                + " to" + medicalProcedureDTO.getEndTime() + "\n\n"
                + "To accept click on the link below:\n"
                + "TBA";
        emailService.sendNotificationAsync("isa.pws43@gmail.com", emailContent);

        return medicalProcedureDTO;
    }
}
