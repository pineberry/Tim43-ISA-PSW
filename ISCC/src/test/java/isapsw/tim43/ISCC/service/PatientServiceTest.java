package isapsw.tim43.ISCC.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class PatientServiceTest {

	@InjectMocks
	@Autowired
	private DoctorService doctorService;
	
	@InjectMocks
	@Autowired
	private PatientService patientService;
	
	@InjectMocks
	@Autowired
	private MedicalRoomService medicalRoomService;

	
	@Test
	void testScheduleAppointment() throws ParseException, MailException, InterruptedException {
		Doctor doctor = doctorService.findOne(9);
		
		MedicalProcedure medicalProcedure = new MedicalProcedure(doctor.getSpecialized(), new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-15"), 
        		medicalRoomService.findOne(1), doctor, patientService.findById(Long.valueOf(2)), 0, false);
				
		String hour = "10";
		
		MedicalProcedureDTO procedure = patientService.scheduleAppointment(doctor, medicalProcedure, hour);
		
    	assertNotNull(procedure);
    	assertFalse(procedure.isBooked());
    	assertEquals(new Date(120, 2, 15, 10, 0), procedure.getDateOfProcedure());
        assertEquals("10:00", procedure.getStartTime());
		
		
	}

}
