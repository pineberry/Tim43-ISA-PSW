package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.DoctorDTO;
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
	
	public DoctorDTO findOne_(long id){
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

}
