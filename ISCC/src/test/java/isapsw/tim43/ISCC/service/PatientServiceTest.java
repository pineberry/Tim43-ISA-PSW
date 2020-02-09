package isapsw.tim43.ISCC.service;

import static isapsw.tim43.ISCC.constants.DoctorConstants.*;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.*;
import static isapsw.tim43.ISCC.constants.PatientConstants.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailException;
import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PatientServiceTest {

	@InjectMocks
	private PatientService patientService;
	
	@Mock
	MedicalProcedureService medicalProcedureService;
	
	@Mock
	ProcedureTypeService procedureTypeService;
	
	@Mock
	DoctorService doctorService;
	
	@Mock
	EmailService emailService;
	
	MedicalProcedureDTO procedure = new MedicalProcedureDTO();

	@BeforeEach
	public void setup() {
		procedure = new MedicalProcedureDTO(new MedicalProcedure(MEDICAL_PROCEDURE_ID,
				MEDICAL_PROCEDURE_TYPE, MEDICAL_PROCEDURE_DATE, MEDICAL_PROCEDURE_ROOM, MEDICAL_PROCEDURE_DOCTOR,
				MEDICAL_PROCEDURE_PATIENT, MEDICAL_PROCEDURE_DISCOUNT, MEDICAL_PROCEDURE_BOOKED,
				MEDICAL_PROCEDURE_DOCTOR_RATED, MEDICAL_PROCEDURE_CLINIC_RATED));
	}
	
	@Test
	void testScheduleAppointment() throws ParseException, MailException, InterruptedException {
				

		when(medicalProcedureService.save(new MedicalProcedureDTO(new MedicalProcedure(MEDICAL_PROCEDURE_ID,
				MEDICAL_PROCEDURE_TYPE, MEDICAL_PROCEDURE_DATE, MEDICAL_PROCEDURE_ROOM, MEDICAL_PROCEDURE_DOCTOR,
				MEDICAL_PROCEDURE_PATIENT, MEDICAL_PROCEDURE_DISCOUNT, MEDICAL_PROCEDURE_START_TIME,
				MEDICAL_PROCEDURE_END_TIME, new ArrayList<Doctor>(), MEDICAL_PROCEDURE_BOOKED,
				MEDICAL_PROCEDURE_DOCTOR_RATED, MEDICAL_PROCEDURE_CLINIC_RATED)))).thenReturn(procedure);
		String day = String.valueOf(MEDICAL_PROCEDURE_DATE.getDate());
		String month = String.valueOf(MEDICAL_PROCEDURE_DATE.getMonth()+1);
		String year = String.valueOf(MEDICAL_PROCEDURE_DATE.getYear()+1900);
		String emailContent = PATIENT_FIRST_NAME + " " + PATIENT_LAST_NAME + " has requested an appointment with dr. " + 
				DOCTOR_FIRST_NAME + " " + DOCTOR_LAST_NAME + " for date: " + 
				day + "/" +
				month + "/" +
				year + " at " + PATIENT_HOUR_SCHEDULE +":00 o'clock.\n\n" +
				"To accept click on the link below:\n"+
				"http://localhost:8081/searchRooms?proc=" + MEDICAL_PROCEDURE_ID + "&type=exam";
		doNothing().when(emailService).sendNotificationAsync("isa.pws43@gmail.com", emailContent);
		
		MedicalProcedureDTO result = patientService.scheduleAppointment(new Doctor(DOCTOR_ID, DOCTOR_EMAIL, DOCTOR_PASSWORD, 
				DOCTOR_FIRST_NAME, DOCTOR_LAST_NAME, DOCTOR_ADDRESS, DOCTOR_CITY, DOCTOR_STATE, DOCTOR_PHONE_NUMBER, 
				DOCTOR_AVERAGE_RATING, DOCTOR_WORKING_TIME_START, DOCTOR_WORKING_TIME_END, DOCTOR_ON_VACATION, DOCTOR_SPECIALIZED, 
				DOCTOR_PROCEDURES, DOCTOR_CLINIC), 
				new MedicalProcedure(MEDICAL_PROCEDURE_ID, MEDICAL_PROCEDURE_TYPE, MEDICAL_PROCEDURE_DATE, MEDICAL_PROCEDURE_ROOM,
						MEDICAL_PROCEDURE_DOCTOR, MEDICAL_PROCEDURE_PATIENT, MEDICAL_PROCEDURE_DISCOUNT, MEDICAL_PROCEDURE_BOOKED,
						MEDICAL_PROCEDURE_DOCTOR_RATED, MEDICAL_PROCEDURE_CLINIC_RATED), PATIENT_HOUR_SCHEDULE);
		
    	assertNotNull(result);
    	assertFalse(result.isBooked());
    	assertEquals(new Date(120, 2, 15, 10, 0), result.getDateOfProcedure());
    	assertEquals(result.getDoctor().getId(),MEDICAL_PROCEDURE_DOCTOR.getId());
    	assertEquals(result.getId(),MEDICAL_PROCEDURE_ID);
	}

}
