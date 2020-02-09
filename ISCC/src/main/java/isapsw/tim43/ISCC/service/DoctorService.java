package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.*;
import isapsw.tim43.ISCC.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private ProcedureTypeService procedureTypeService;

	@Autowired
	private ReportService reportService;

	@Autowired
	private ClinicService clinicService;

	@Autowired
	private MedicalRoomService medicalRoomService;

	@Autowired
	private ClinicAdministratorService clinicAdministratorService;
	
	public DoctorDTO save(DoctorDTO doctorDTO) {
		if (doctorDTO.getEmail() == null || doctorDTO.getEmail().isEmpty() || doctorDTO.getFirstName() == null
				|| doctorDTO.getFirstName().isEmpty() || doctorDTO.getLastName() == null
				|| doctorDTO.getLastName().isEmpty() ||  doctorDTO.getState() == null
				|| doctorDTO.getState().isEmpty() || doctorDTO.getCity() == null
				|| doctorDTO.getCity().isEmpty() || doctorDTO.getAddress() == null
				|| doctorDTO.getAddress().isEmpty() || doctorDTO.getPhoneNumber() == null
				|| doctorDTO.getPhoneNumber().isEmpty()) {
			return null;
		}

		ProcedureType procedureType = procedureTypeService.findOne(doctorDTO.getSpecialized().getId());
		Clinic clinic = clinicService.findOne(doctorDTO.getClinic().getId());

		if (!checkWorkingTime(doctorDTO.getWorkingtimeStart(), doctorDTO.getWorkingtimeEnd())) {
			return null;
		}

		Doctor doctor = new Doctor();

		doctor.setEmail(doctorDTO.getEmail());
		doctor.setFirstName(doctorDTO.getFirstName());
		doctor.setLastName(doctorDTO.getLastName());
		doctor.setCity(doctorDTO.getCity());
		doctor.setState(doctorDTO.getState());
		doctor.setAddress(doctorDTO.getAddress());
		doctor.setPassword(doctorDTO.getPassword());
		doctor.setAverageRating(doctorDTO.getAverageRating());
		doctor.setWorkingtimeStart(doctorDTO.getWorkingtimeStart());
		doctor.setWorkingtimeEnd(doctorDTO.getWorkingtimeEnd());
		doctor.setPhoneNumber(doctorDTO.getPhoneNumber());
		doctor.setOnVacation(false);
		doctor.setSpecialized(procedureType);
		doctor.setClinic(clinic);

		doctor = doctorRepository.save(doctor);

		return new DoctorDTO(doctor);
	}

	public Doctor saveD(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	public List<Doctor> findAllByClinic(Long id) {
		Clinic clinic = clinicService.findOne(id);
		return doctorRepository.findAllByClinic(clinic);
	}

	public List<DoctorDTO> searchByNameAndLastName(String name, String lastName, Long id) {
		if ((name == null || name.trim().isEmpty() || name.equals("empty")) &&
				(lastName == null || lastName.trim().isEmpty() || lastName.equals("empty"))) {
			return null;
		}

		ClinicAdministrator clinicAdministrator = clinicAdministratorService.findOne(id);
		Clinic clinic = clinicAdministrator.getClinic();

		if (clinic == null) {
			return null;
		}

		List<DoctorDTO> doctorDTOList = new ArrayList<DoctorDTO>();
		List<Doctor> doctors;

		if (name.equals("empty")) {
			doctors = doctorRepository.findDoctorByLastNameAndClinic(lastName, clinic);
		} else if(lastName.equals("empty")) {
			doctors = doctorRepository.findDoctorByFirstNameAndClinic(name, clinic);
		} else {
			doctors = doctorRepository.findByFirstNameAndLastNameAndClinic(name, lastName, clinic);
		}

		for (Doctor doctor: doctors) {
			doctorDTOList.add(new DoctorDTO(doctor));
		}

		return doctorDTOList;
	}

	public List<DoctorDTO> getDoctorsByClinic(Long id) {
		List<Doctor> doctors = findAllByClinic(id);

		List<DoctorDTO> doctorDTOS = new ArrayList<>();
		for (Doctor doc: doctors) {
			DoctorDTO doctorDTO = new DoctorDTO();
			doctorDTO.setId(doc.getId());
			doctorDTO.setEmail(doc.getEmail());
			doctorDTO.setFirstName(doc.getFirstName());
			doctorDTO.setLastName(doc.getLastName());
			doctorDTOS.add(doctorDTO);
		}

		return doctorDTOS;
	}

	public List<DoctorDTO> findByClinic(Long id) {
		ClinicAdministrator clinicAdministrator = clinicAdministratorService.findOne(id);

		if (clinicAdministrator == null) {
			return null;
		}
		return getDoctorsByClinic(clinicAdministrator.getClinic().getId());
	}

	public DoctorDTO update(DoctorDTO doctorDTO){
		Doctor doctor = findOne(doctorDTO.getId());

		doctor.setFirstName(doctorDTO.getFirstName());
		doctor.setLastName(doctorDTO.getLastName());
		doctor.setCity(doctorDTO.getCity());
		doctor.setState(doctorDTO.getState());
		doctor.setAddress(doctorDTO.getAddress());
		doctor.setPhoneNumber(doctorDTO.getPhoneNumber());

		doctor = doctorRepository.save(doctor);
		return new DoctorDTO(doctor);

	}

	public void remove(Long id) {
		doctorRepository.deleteById(id);
	}

	public UserDTO changePassword(UserDTO userDTO){
		Doctor doctor = findUserByEmailAddress(userDTO.getEmail());

		if (doctor == null || !userDTO.getPassword().equals(userDTO.getPasswordF())) {
			return null;
		}

		doctor.setPassword(userDTO.getPassword());
		doctor.setFirstLogin(false);
		doctorRepository.save(doctor);

		return userDTO;
	}

	public Doctor findUserByEmailAddress(String emailAddress) {
		Doctor doctor = null;

		for (Doctor d : doctorRepository.findAll()) {
			if(d.getEmail().equals(emailAddress))
			{
				doctor = d;
				break;
			}
		}
		return doctor;
	}

	public Doctor findOne(long id){
		Optional<Doctor> doctor = doctorRepository.findById(id);
		if(doctor.isPresent())
		{
			return doctor.get();
		} else
			return null;
	}
	
	public DoctorDTO findOne_(Long id){
		Doctor doctor = findOne(id);
		DoctorDTO doctorDTO = new DoctorDTO(doctor);
		for(MedicalProcedure medProc : doctor.getProcedures()) {
			MedicalProcedureDTO medicalProcedureDTO = new MedicalProcedureDTO(medProc);
			doctorDTO.getAttendingProcedures().add(medicalProcedureDTO);
		}
		return doctorDTO;
	}

	public List<DoctorDTO> findAll(){
		List<Doctor> doctors = doctorRepository.findAll();
		List<DoctorDTO> doctorDTOList = new ArrayList<DoctorDTO>();

		for(Doctor doctor: doctors){
			DoctorDTO doctorDTO = new DoctorDTO(doctor);
			doctorDTOList.add(doctorDTO);
		}

		return doctorDTOList;
	}
	
	public List<Doctor> findAll_(){
		return doctorRepository.findAll();
	}

	private boolean checkWorkingTime(String workingtimeStart, String workingtimeEnd){
		boolean retVal = true;
		String[] startTimeParts = workingtimeStart.split(":");
		String[] endTimeParts = workingtimeEnd.split(":");
		int startHour;
		int startMinute;
		int endHour;
		int endMinute;
		try{
			startHour = Integer.parseInt(startTimeParts[0]);
			startMinute = Integer.parseInt(startTimeParts[1]);
			endHour = Integer.parseInt(endTimeParts[0]);
			endMinute = Integer.parseInt(endTimeParts[1]);
		}catch(Exception e){
			return false;
		}
		if (startHour > endHour) {
			retVal = false;
		} else if ((startHour == endHour) && (startMinute >= endMinute)){
			retVal = false;
		}

		return retVal;
	}

	public List<ReportDTO> getExamReports(Long id) {
		Doctor doctor = findOne(id);

		if (doctor == null) {
			return null;
		}

		List<Report> reports = reportService.findAllByDoctor(doctor);
		List<ReportDTO> reportDTOS = new ArrayList<>();
		for (Report report:
			 reports) {
			ReportDTO reportDTO = reportService.modelToDto(report);
			reportDTOS.add(reportDTO);
		}

		return reportDTOS;
	}

	public ReportDTO getReport(Long id) {
		Report report = reportService.findById(id);

		if(report == null) {
			return null;
		}

		ReportDTO reportDTO = reportService.modelToDto(report);

		return reportDTO;
	}

	public boolean deleteDoctor(long id){
		Doctor doctor = findOne(id);

		if (doctor == null || !doctor.getMedicalProcedures().isEmpty() || !doctor.getProcedures().isEmpty()) {
			return false;
		}

		remove(id);

		return true;
	}

	/*
	 * Provera da li je za slobodan termin sale slobodan i doktor koji je zatrazen u pregledu, ukoliko nije, trazi se
	 * prvi sledeci doktor koji je slobodan, a ima istu specijalizaciju i radi na istoj klinici.
	 */
	public Doctor getAvailableDoctor(MedicalProcedure medicalProcedure, String requestedTimes) {
		Doctor doctor = medicalProcedure.getDoctor();

		// Vremena pregleda za odabrani datum, gde je doktor glavni za pregled
		List<String> times = medicalRoomService.getTimesForChosenDate(medicalProcedure.getDateOfProcedure(),
																				doctor.getMedicalProcedures());
		// Vremena pregleda za odabrani datum, gde je doktor prisutan operaciji
		times.addAll(medicalRoomService.getTimesForChosenDate(medicalProcedure.getDateOfProcedure(),
																				doctor.getProcedures()));
		if (!medicalRoomService.overlapingTimes(times, requestedTimes)) {
			return doctor;
		} else {
			for (Doctor doc: doctorRepository.findAllBySpecializedAndClinic(doctor.getSpecialized(),
																								doctor.getClinic())) {
				List<String> timesForChosenDate = medicalRoomService
						.getTimesForChosenDate(medicalProcedure.getDateOfProcedure(), doc.getMedicalProcedures());
				if (!medicalRoomService.overlapingTimes(timesForChosenDate, requestedTimes)) {
					return doctor;
				}
			}
		}

		return null;
	}

	public void updateDoctorRate(Long id, int rate) {
		Optional<Doctor> doctor = doctorRepository.findById(id);
		doctor.get().setAverageRating((doctor.get().getAverageRating()+rate)/2);
		doctorRepository.save(doctor.get());
		
	}

	public List<DoctorDTO> getClinicDoctors(Long id){
		ClinicAdministrator clinicAdministrator = clinicAdministratorService.findOne(id);
		Clinic clinic = clinicService.findOne(clinicAdministrator.getClinic().getId());
		List<DoctorDTO> doctorDTOList = new ArrayList<DoctorDTO>();

		for (Doctor doctor: clinic.getDoctors()) {
			doctorDTOList.add(new DoctorDTO(doctor));
		}

		return doctorDTOList;
	}


}
