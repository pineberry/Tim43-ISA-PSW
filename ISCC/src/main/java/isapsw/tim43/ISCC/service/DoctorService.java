package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.DoctorDTO;
import isapsw.tim43.ISCC.dto.ReportDTO;
import isapsw.tim43.ISCC.dto.UserDTO;
import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.ProcedureType;
import isapsw.tim43.ISCC.model.Report;
import isapsw.tim43.ISCC.repository.ClinicRepository;
import isapsw.tim43.ISCC.repository.ProcedureTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.List;

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
	
	public DoctorDTO save(DoctorDTO doctorDTO) {
		if(doctorDTO.getEmail() == null || doctorDTO.getEmail().isEmpty() || doctorDTO.getFirstName() == null
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

		if(!checkWorkingTime(doctorDTO.getWorkingtimeStart(), doctorDTO.getWorkingtimeEnd())){
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
		return doctorRepository.findById(id).orElseGet(null);
	}
	
	public DoctorDTO findOne_(Long id){
		return new DoctorDTO(doctorRepository.findById(id).get());
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

	public boolean deleteDoctor(Long id){
		Doctor doctor = findOne(id);

		if (doctor == null || !doctor.getMedicalProcedures().isEmpty()) {
			return false;
		}

		remove(id);

		return true;
	}

}
