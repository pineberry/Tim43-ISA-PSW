package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.model.Patient;
import isapsw.tim43.ISCC.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
    MedicalProcedureService medicalProcedureService;
	
	@Autowired
	private EmailService emailService;

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

	public MedicalProcedureDTO scheduleAppointment(Patient patient, Doctor doctor, MedicalProcedure medicalProcedure, String hour) throws MailException, InterruptedException {
		int year = medicalProcedure.getDateOfProcedure().getYear() + 1900;
		int month = medicalProcedure.getDateOfProcedure().getMonth() + 1;	
		String emailContent = patient.getFirstName() + " " + patient.getLastName() + " has requested an appointment with dr. " + 
							doctor.getFirstName() + " " + doctor.getLastName() + " for date: " + medicalProcedure.getDateOfProcedure().getDate() + "/" +
							month + "/" +
							year + " at " + hour +":00 o'clock.\n\n" +
				"To accept click on the link below:\n"+
				"http://localhost:8081/searchRooms/" + medicalProcedure.getId();
		emailService.sendNotificationAsync("isa.pws43@gmail.com", emailContent);
		return medicalProcedureService.save(new MedicalProcedureDTO(medicalProcedure));
		
	}

}
