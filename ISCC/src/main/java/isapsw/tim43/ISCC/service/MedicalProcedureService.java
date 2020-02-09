package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.model.MedicalRoom;
import isapsw.tim43.ISCC.model.ProcedureType;
import isapsw.tim43.ISCC.repository.MedicalProcedureRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
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
    private PatientService patientService;

    @Autowired
    private EmailService emailService;

    public MedicalProcedureDTO save(MedicalProcedureDTO medicalProcedureDTO) {
        if (medicalProcedureDTO.getProcedureType() == null || medicalProcedureDTO.getDateOfProcedure() == null ||
                medicalProcedureDTO.getDoctor() == null) {
            return null;
        }

        ProcedureType procedureType = procedureTypeService.findOne(medicalProcedureDTO.getProcedureType().getId());
        Doctor doctor = doctorService.findOne(medicalProcedureDTO.getDoctor().getId());

        if(procedureType == null || doctor == null
                                 || procedureType.getTypeName() != doctor.getSpecialized().getTypeName()){
            return null;
        }

        // Provera da li doktor ima zakazanih pregelda u trazeno vreme
        List<String> times = medicalRoomService.getTimesForChosenDate(medicalProcedureDTO.getDateOfProcedure(),
                doctor.getMedicalProcedures());
        String requestedTimes = medicalProcedureDTO.getStartTime() + ":" + medicalProcedureDTO.getEndTime();

        if (medicalRoomService.overlapingTimes(times, requestedTimes)) {
            return null;
        }

        // Provera da li doktor mora biti prisutan nekoj operaciji u trazeno vreme
        times = medicalRoomService.getTimesForChosenDate(medicalProcedureDTO.getDateOfProcedure(),
                doctor.getProcedures());
        if (medicalRoomService.overlapingTimes(times, requestedTimes)) {
            return null;
        }

        MedicalRoom medicalRoom = null;

        if(medicalProcedureDTO.getMedicalRoom() != null) {
            medicalRoom = medicalRoomService.findOne(medicalProcedureDTO.getMedicalRoom().getId());
            // Provera da li je sala zauzeta u trazeno vreme
            times = medicalRoomService.getTimesForChosenDate(medicalProcedureDTO.getDateOfProcedure(),
                    medicalRoom.getMedicalProcedures());
            if (medicalRoomService.overlapingTimes(times, requestedTimes)) {
                return null;
            }
        }

        MedicalProcedure medicalProcedure = new MedicalProcedure();
        medicalProcedure.setProcedureType(procedureType);
        medicalProcedure.setDateOfProcedure(medicalProcedureDTO.getDateOfProcedure());
        medicalProcedure.setMedicalRoom(medicalRoom);
        medicalProcedure.setDoctor(doctor);
        if (medicalProcedureDTO.getPatient() != null) {
            medicalProcedure.setPatient(patientService.findById(medicalProcedureDTO.getPatient().getId()));
        }
        medicalProcedure.setDiscount(0);
        medicalProcedure.setBooked(false);
        medicalProcedure.setDoctorRated(false);
        medicalProcedure.setClinicRated(false);
        medicalProcedure.setStartTime(medicalProcedureDTO.getStartTime());
        medicalProcedure.setEndTime(medicalProcedureDTO.getEndTime());

        medicalProcedure = medicalProcedureRepository.save(medicalProcedure);
        return new MedicalProcedureDTO(medicalProcedure);
    }
    
    public List<MedicalProcedure> findAll(){
    	return medicalProcedureRepository.findAll();
    }

    public MedicalProcedure findOne(Long id) {
    	
    	Optional<MedicalProcedure> procedure = medicalProcedureRepository.findById(id);
    	
    	if(procedure.isPresent()) {
    		return procedure.get();
    	} else {
    		return null;
    	}
    	
    }

    public List<MedicalProcedure> findByDoctor(Long id) {
        Doctor doctor = doctorService.findOne(id);
        return doctor.getMedicalProcedures();
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

    public MedicalProcedureDTO bookRoom(Long procedureId, Long roomId) throws InterruptedException {
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

        String emailContent = "Dear " + medicalProcedure.getPatient().getFirstName() + " "
        		+ medicalProcedure.getPatient().getLastName() + ",\nYour appointment has been scheduled for "
        		+ medicalProcedure.getDateOfProcedure().toString() + ".";
        emailService.sendNotificationAsync("isa.pws43@gmail.com", emailContent);
        
        medicalProcedure = medicalProcedureRepository.save(medicalProcedure);

        return new MedicalProcedureDTO(medicalProcedure);
    }

    public MedicalProcedureDTO bookOperationRoom(Long procedureId, Long roomId, List<Long> doctorsId) throws InterruptedException {
        MedicalRoom medicalRoom = medicalRoomService.findOne(roomId);
        MedicalProcedure medicalProcedure = findOne(procedureId);
        List<Doctor> doctors = new ArrayList<>();
        for (Long id : doctorsId) {
            Doctor doctor = doctorService.findOne(id);
            doctors.add(doctor);
        }

        if(medicalProcedure == null || medicalRoom == null || doctors.isEmpty()) {
            return null;
        }

        medicalProcedure.setDoctors(doctors);
        medicalProcedure.setMedicalRoom(medicalRoom);
        medicalProcedure.setBooked(true);
        medicalProcedureRepository.save(medicalProcedure);

        String emailContent = "Dear " + medicalProcedure.getPatient().getFirstName() + " "
                + medicalProcedure.getPatient().getLastName() + ",\nYour surgery has been scheduled for "
                + medicalProcedure.getDateOfProcedure().toString() + " at " + medicalProcedure.getStartTime() + ".";
        emailService.sendNotificationAsync("isa.pws43@gmail.com", emailContent);

        for (Doctor doctor : doctors) {
            String doctorEmail = "Dear " + doctor.getFirstName() + " "
                    + doctor.getLastName() + ",\nYou have scheduled surgery for "
                    + medicalProcedure.getDateOfProcedure().toString() + " at " + medicalProcedure.getStartTime() + ".";
            emailService.sendNotificationAsync("isa.pws43@gmail.com", doctorEmail);
        }

        return new MedicalProcedureDTO(medicalProcedure);
    }

	public List<MedicalProcedureDTO> getPatientsProcedures(Long patientID) {

		List<MedicalProcedureDTO> procedures = new ArrayList<MedicalProcedureDTO>();
		for (MedicalProcedure procedure : findAll()) {
			if (procedure.getPatient() != null) {
				if (procedure.getPatient().getId() == patientID && procedure.getDateOfProcedure().after(new Date())) {
					procedures.add(new MedicalProcedureDTO(procedure));
				} 
			}
		}

		return procedures;
	}
	
	public List<MedicalProcedureDTO> getPatientsPastProcedures(Long patientID) {
		
		List<MedicalProcedureDTO> procedures = new ArrayList<MedicalProcedureDTO>();
		for (MedicalProcedure procedure : findAll()) {
			if (procedure.getPatient() != null) {
				if (procedure.getPatient().getId() == patientID && procedure.getDateOfProcedure().before(new Date())) {
					procedures.add(new MedicalProcedureDTO(procedure));
				} 
			}
		}
		
		return procedures;
	}
	

	public List<MedicalProcedureDTO> confirmAppointment(MedicalProcedureDTO procedure) {
		MedicalProcedure mp = findOne(procedure.getId());
		mp.setBooked(true);
		mp.setDoctorRated(false);
		mp.setClinicRated(false);

		medicalProcedureRepository.save(mp);

		return getPatientsProcedures(procedure.getPatient().getId());
	}

	public List<MedicalProcedureDTO> denyAppointment(Long procedureID, Long patientID) {
		medicalProcedureRepository.deleteById(procedureID);
		return getPatientsProcedures(patientID);
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
                + "http://localhost:8081/searchRooms?proc=" + medicalProcedureDTO.getId() + "&type=exam";
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
                + "http://localhost:8081/searchRooms?proc=" + medicalProcedureDTO.getId() + "&type=surgery";
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

	public void updateMedicalProcedure(Long appointmentId, String rated) {
		Optional<MedicalProcedure> procedure = medicalProcedureRepository.findById(appointmentId);
		if(rated.equals("clinic")) {
			procedure.get().setClinicRated(true);
		} else {
			procedure.get().setDoctorRated(true);
		}
		medicalProcedureRepository.save(procedure.get());
	}

	public List<MedicalProcedureDTO> getPredefinedProcedures(Long clinicID) {
		List<MedicalProcedureDTO> procedures = new ArrayList<MedicalProcedureDTO>();
		
		for (MedicalProcedure procedure : findAll()) {
			if(procedure.getPatient() == null && //predefinisana
					procedure.getDoctor().getClinic().getId()==clinicID) //trazena za datu kliniku  
			{
				procedures.add(new MedicalProcedureDTO(procedure));
			}
		}
		
		return procedures;
	}

	public List<MedicalProcedureDTO> confirmPredefinedAppointment(MedicalProcedureDTO procedure, Long patientID) throws MailException, InterruptedException {
		MedicalProcedure mp = findOne(procedure.getId());
		mp.setPatient(patientService.findById(patientID));
		mp.setBooked(true);
		mp.setDoctorRated(false);
		mp.setClinicRated(false);
		
		String emailContent = "Dear " + mp.getPatient().getFirstName() + " "
        		+ mp.getPatient().getLastName() + ",\nYour appointment has been scheduled for "
        		+ mp.getDateOfProcedure().toString() + ".";
        emailService.sendNotificationAsync("isa.pws43@gmail.com", emailContent);

		medicalProcedureRepository.save(mp);

		return getPatientsProcedures(patientID);
	}

    public MedicalProcedureDTO finishExam(Long id) {
        MedicalProcedure medicalProcedure = findOne(id);

        if(medicalProcedure == null ) {
            return null;
        }

        medicalProcedure.setMedicalRoom(null);
        medicalProcedure.setBooked(false);
        medicalProcedureRepository.save(medicalProcedure);

        return new MedicalProcedureDTO(medicalProcedure);
    }
}
