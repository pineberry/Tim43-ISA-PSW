package isapsw.tim43.ISCC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	public Doctor save(Doctor doctor) {
		return doctorRepository.save(doctor);
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
}
