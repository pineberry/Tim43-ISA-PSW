package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.dto.PatientDTO;
import isapsw.tim43.ISCC.dto.UserDTO;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.model.Patient;
import isapsw.tim43.ISCC.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	public MedicalProcedureDTO scheduleAppointment(Doctor doctor, MedicalProcedure medicalProcedure, String hour) throws MailException, InterruptedException {
		int year = medicalProcedure.getDateOfProcedure().getYear() + 1900;
		int month = medicalProcedure.getDateOfProcedure().getMonth() + 1;
		int date = medicalProcedure.getDateOfProcedure().getDate();
		@SuppressWarnings("deprecation")
		Date dateOfProcedure = medicalProcedure.getDateOfProcedure();
		dateOfProcedure.setHours(Integer.parseInt(hour));
		medicalProcedure.setDateOfProcedure(dateOfProcedure);
		MedicalProcedureDTO retVal = medicalProcedureService.save(new MedicalProcedureDTO(medicalProcedure));
		Patient patient = medicalProcedure.getPatient();
		String emailContent = patient.getFirstName() + " " + patient.getLastName() + " has requested an appointment with dr. " + 
							doctor.getFirstName() + " " + doctor.getLastName() + " for date: " + 
							date + "/" +
							month + "/" +
							year + " at " + hour +":00 o'clock.\n\n" +
				"To accept click on the link below:\n"+
				"http://localhost:8081/searchRooms/" + retVal.getId();
		emailService.sendNotificationAsync("isa.pws43@gmail.com", emailContent);
		return retVal;
		
	}

	public PatientDTO updatePatientsData(PatientDTO patientData) {
		patientRepository.save(new Patient(patientData.getId(), patientData.getEmail(), patientData.getPassword(), patientData.getFirstName(),
				patientData.getLastName(), patientData.getAddress(), patientData.getCity(), patientData.getState(), patientData.getPhoneNumber(),
				patientData.getHealthCareNumber(), patientRepository.findById(patientData.getId()).get().getStatus(),
				patientRepository.findById(patientData.getId()).get().getRecord()));
		return null;
	}

	public List<PatientDTO> getAllPatients() {
		List<Patient> patients = patientRepository.findAll();

		List<PatientDTO> patientDTOS = new ArrayList<>();

		for (Patient patient:
			 patients) {
			PatientDTO patientDTO = modelToDto(patient);
			patientDTOS.add(patientDTO);
		}

		return patientDTOS;
	}

	public PatientDTO modelToDto(Patient patient) {
		PatientDTO patientDTO = new PatientDTO();

		patientDTO.setId(patient.getId());
		patientDTO.setEmail(patient.getEmail());
		patientDTO.setFirstName(patient.getFirstName());
		patientDTO.setLastName(patient.getLastName());
		patientDTO.setHealthCareNumber(patient.getHealthCareNumber());
		patientDTO.setAddress(patient.getAddress());
		patientDTO.setCity(patient.getCity());
		patientDTO.setState(patient.getState());
		patientDTO.setPhoneNumber(patient.getPhoneNumber());

		return patientDTO;
	}

}
