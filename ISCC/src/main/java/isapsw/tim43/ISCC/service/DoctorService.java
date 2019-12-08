package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.DoctorDTO;
import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.ProcedureType;
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
	
	public void remove(Long id) {
		doctorRepository.deleteById(id);
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

}
