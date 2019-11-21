package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.model.Patient;
import isapsw.tim43.ISCC.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

	@Autowired
	PatientRepository patientRepository;

	public Patient findById(Long id) {
		Patient p = patientRepository.findById(id).orElse(null);
		return p;
	}

	public Patient findByHealthCareNumber(String healthCareNumber) {
		Patient patient = null;

		for (Patient p : patientRepository.findAll()) {
			if(p.getHealthCareNumber().equals(healthCareNumber))
			{
				patient = p;
				break;
			}
		}

		return patient;
	}

	public Patient save(Patient patient) {
		return patientRepository.save(patient);
	}

	public void remove(Long id) {
		patientRepository.deleteById(id);
	}

	public Patient findUserByEmailAddress(String emailAddress) {
		Patient patient = null;

		for (Patient p : patientRepository.findAll()) {
			if(p.getEmail().equals(emailAddress))
			{
				patient = p;
				break;
			}
		}
		return patient;
	}

}
