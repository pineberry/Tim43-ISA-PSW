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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
            medicalProcedureDTOS.add(medicalProcedureDTO);
        }

        return medicalProcedureDTOS;
    }

    public List<MedicalProcedureDTO> proceduresByRoom(Long id){
        MedicalRoom medicalRoom = medicalRoomService.findOne(id);
        List<MedicalProcedure> procedures = medicalProcedureRepository.findByMedicalRoom(medicalRoom);

        return formatProcedures(procedures);
    }

    public List<MedicalProcedureDTO> formatProcedures(List<MedicalProcedure> procedures){
        List<MedicalProcedureDTO> medicalProcedureDTOS = new ArrayList<>();
        for (MedicalProcedure proc : procedures) {
            MedicalProcedureDTO medicalProcedureDTO = new MedicalProcedureDTO();
            medicalProcedureDTO.setDateOfProcedure(proc.getDateOfProcedure());
            medicalProcedureDTO.setStartTime(proc.getStartTime());
            medicalProcedureDTO.setEndTime(proc.getEndTime());
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

        List<String> timesOfProcedures = medicalRoomService.getTimesForChosenDate(medicalProcedure.getDateOfProcedure(),
                                                                                        medicalRoom.getMedicalProcedures());
        String timeOfNewProcedure = medicalProcedure.getStartTime() + ":" + medicalProcedure.getEndTime();

        if (!medicalRoomService.overlapingTimes(timesOfProcedures, timeOfNewProcedure))
            medicalProcedure.setMedicalRoom(medicalRoom);
        else
            return null;

        String emailContent = "Test: Your appointment has been scheduled for " + medicalProcedure.getDateOfProcedure();
        emailService.sendNotificationAsync("isa.pws43@gmail.com", emailContent);

        return medicalProcedureRepository.save(medicalProcedure);
    }

    public MedicalProcedureDTO autoBookRoom(Long id) {
        MedicalProcedure medicalProcedure = findOne(id);

        if (medicalProcedure == null) {
            return null;
        }

        medicalProcedure = medicalRoomService.findAvailableAppoinment(medicalProcedure);
        medicalProcedure = medicalProcedureRepository.save(medicalProcedure);
        return new MedicalProcedureDTO(medicalProcedure);
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

    // Svaki dan u 23:01 svim pregledima koji nemaju dodeljene sale, dodeli jednu
    @Scheduled(cron = "0 1 23 * * ?")
//    @Scheduled(cron = "0/60 * * * * ?")
    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    public void timerBooking() {
        List<MedicalProcedure> medicalProcedures = medicalProcedureRepository.findAllWithoutRoom();

        for (MedicalProcedure medicalProcedure: medicalProcedures) {
            medicalProcedure = medicalRoomService.findAvailableAppoinment(medicalProcedure);
            medicalProcedureRepository.save(medicalProcedure);
        }
    }
}
