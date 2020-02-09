package isapsw.tim43.ISCC.controller;

import static isapsw.tim43.ISCC.constants.DoctorConstants.*;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.repository.ClinicAdministratorRepository;
import isapsw.tim43.ISCC.repository.DoctorRepository;
import isapsw.tim43.ISCC.repository.MedicalProcedureRepository;
import isapsw.tim43.ISCC.repository.MedicalRoomRepository;
import isapsw.tim43.ISCC.repository.PatientRepository;
import isapsw.tim43.ISCC.repository.ProcedureTypeRepository;
import isapsw.tim43.ISCC.service.ClinicAdministratorService;
import isapsw.tim43.ISCC.service.DoctorService;
import isapsw.tim43.ISCC.service.EmailService;
import isapsw.tim43.ISCC.service.MedicalProcedureService;
import isapsw.tim43.ISCC.service.MedicalRoomService;
import isapsw.tim43.ISCC.service.PatientService;
import isapsw.tim43.ISCC.service.ProcedureTypeService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PatientControllerTest {

	@InjectMocks
	PatientController patientControllerMock;

	@Mock
	PatientService patientService;
//	
//	@Mock
//	PatientRepository patientRepository;
//
	@Mock
	DoctorService doctorService;
//	
//	@Mock
//	DoctorRepository doctorRepository;
//
	@Mock
	MedicalRoomService medicalRoomService;
//	
//	@Mock
//	MedicalRoomRepository medicalRoomRepository;
//	
//	@Mock
//	MedicalProcedureRepository medicalProcedureRepository;
//
//	@Mock
//    MedicalProcedureService medicalProcedureService;
//	
//	@Mock
//	EmailService emailService;
//	
//	@Mock
//	ProcedureTypeService procedureTypeService;
//	
//	@Mock
//	ProcedureTypeRepository procedureTypeRepository;
//	
//	@Mock
//    ClinicAdministratorService clinicAdministratorService;
//	
//	@Mock
//    ClinicAdministratorRepository clinicAdministratorRepository;

	

	@Test
	void testScheduleAppointment_successful() throws MailException, ParseException, InterruptedException{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Doctor doctor = new Doctor(DOCTOR_ID, DOCTOR_EMAIL, DOCTOR_PASSWORD, DOCTOR_FIRST_NAME, DOCTOR_LAST_NAME,
				DOCTOR_ADDRESS, DOCTOR_CITY, DOCTOR_STATE, DOCTOR_PHONE_NUMBER, DOCTOR_AVERAGE_RATING,
				DOCTOR_WORKING_TIME_START, DOCTOR_WORKING_TIME_END, DOCTOR_ON_VACATION, DOCTOR_SPECIALIZED,
				DOCTOR_PROCEDURES, DOCTOR_CLINIC);

		when(doctorService.findOne(1L)).thenReturn(doctor);
		
		MedicalProcedure medicalProcedure = new MedicalProcedure(MEDICAL_PROCEDURE_ID, MEDICAL_PROCEDURE_TYPE, MEDICAL_PROCEDURE_DATE, MEDICAL_PROCEDURE_ROOM,
				MEDICAL_PROCEDURE_DOCTOR, MEDICAL_PROCEDURE_PATIENT, MEDICAL_PROCEDURE_DISCOUNT, MEDICAL_PROCEDURE_BOOKED, MEDICAL_PROCEDURE_DOCTOR_RATED, MEDICAL_PROCEDURE_CLINIC_RATED);
		
		MedicalProcedureDTO medProcDTO = new MedicalProcedureDTO(medicalProcedure);
		
		when(patientService.scheduleAppointment(doctor, new MedicalProcedure(DOCTOR_SPECIALIZED, 
				new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-15"), medicalRoomService.findOne(1), doctor, 
				patientService.findById(Long.parseLong("1")), 0, false), "10")).thenReturn(medProcDTO);

		ResponseEntity<MedicalProcedureDTO> responseEntity = 
				patientControllerMock.scheduleAppointment("2020-03-15", "10", "1", "1");

		
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertEquals(responseEntity.getBody().getDateOfProcedure(), new Date(120, 2, 15, 10, 0));
		assertEquals(responseEntity.getBody().getDoctor().getFirstName(), "Marko");
		assertEquals(responseEntity.getBody().getPatient().getId(), Long.valueOf(1));
		

	}
	
	@Test
	void testScheduleAppointment_not_found() throws MailException, ParseException, InterruptedException{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		when(doctorService.findOne(1L)).thenReturn(null);

		ResponseEntity<MedicalProcedureDTO> responseEntity = 
				patientControllerMock.scheduleAppointment("2020-03-15", "10", "1", "1");

		
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

}

/*
 * 
 * @GetMapping(value = "/schedule-appointment")
    public ResponseEntity<MedicalProcedureDTO> scheduleAppointment(@RequestParam(name="date") String date, @RequestParam(name="hour") String hour, 
    		@RequestParam(name="patient") String patient_id, @RequestParam(name="doctor") String doctor_id) throws ParseException, MailException, InterruptedException {

        Doctor doctor = doctorService.findOne(Long.parseLong(doctor_id));

        MedicalProcedureDTO medProcDTO = patientService.scheduleAppointment(
        		doctor, new MedicalProcedure(doctor.getSpecialized(), new SimpleDateFormat("yyyy-MM-dd").parse(date), 
        		medicalRoomService.findOne(1), doctor, patientService.findById(Long.parseLong(patient_id)), 0, false), hour);

		return new ResponseEntity<MedicalProcedureDTO>(medProcDTO , HttpStatus.OK);
    }
 * 
 * */
