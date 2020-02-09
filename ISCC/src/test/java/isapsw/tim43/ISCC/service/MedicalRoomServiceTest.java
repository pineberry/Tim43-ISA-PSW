package isapsw.tim43.ISCC.service;

import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_BOOKED;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_CLINIC_RATED;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_DATE;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_DISCOUNT;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_DOCTOR;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_DOCTOR_RATED;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_ID;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_PATIENT;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_TYPE;

import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_CLINIC;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.model.MedicalRoom;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicalRoomServiceTest {
	
	
	@SpyBean
	private MedicalRoomService medicalRoomService;
	
	@MockBean
	private DoctorService doctorService;
	
	@Test
	public void getTimesForCosenRoomSuccess() {
		MedicalProcedure medicalProcedure = new MedicalProcedure(MEDICAL_PROCEDURE_ID, MEDICAL_PROCEDURE_TYPE, MEDICAL_PROCEDURE_DATE, null,
				MEDICAL_PROCEDURE_DOCTOR, MEDICAL_PROCEDURE_PATIENT, MEDICAL_PROCEDURE_DISCOUNT, MEDICAL_PROCEDURE_BOOKED, MEDICAL_PROCEDURE_DOCTOR_RATED, MEDICAL_PROCEDURE_CLINIC_RATED);
		MedicalProcedure medicalProcedureAnother = new MedicalProcedure(MEDICAL_PROCEDURE_ID, MEDICAL_PROCEDURE_TYPE, MEDICAL_PROCEDURE_DATE, null,
				MEDICAL_PROCEDURE_DOCTOR, MEDICAL_PROCEDURE_PATIENT, MEDICAL_PROCEDURE_DISCOUNT, MEDICAL_PROCEDURE_BOOKED, MEDICAL_PROCEDURE_DOCTOR_RATED, MEDICAL_PROCEDURE_CLINIC_RATED);
		
		medicalProcedure.setStartTime("12:00");
		medicalProcedure.setEndTime("13:30");
		medicalProcedureAnother.setStartTime("14:00");
		medicalProcedureAnother.setEndTime("15:30");
		
		List<MedicalProcedure> procs = new ArrayList<MedicalProcedure>();
		procs.add(medicalProcedure);
		procs.add(medicalProcedureAnother);
		
		
		List<String> result = medicalRoomService.getTimesForChosenDate(MEDICAL_PROCEDURE_DATE, procs);
		
		assertThat(result).isNotEmpty();
		assertThat(result).hasSize(2);					
	}
	
	@Test
	public void overlapingTimesSuccess() {
		List<String> roomTimes = new ArrayList<String>();
		String procedureTime = "12:00:13:00";
		roomTimes.add("11:00:11:50");
		roomTimes.add("13:01:14:00");
		boolean retVal = medicalRoomService.overlapingTimes(roomTimes, procedureTime);
		
		assertThat(retVal).isFalse();
	}
	
	@Test
	public void overlapingTimesFail() {
		List<String> roomTimes = new ArrayList<String>();
		String procedureTime = "12:00:13:00";
		roomTimes.add("11:00:12:01");
		roomTimes.add("13:01:14:00");
		boolean retVal = medicalRoomService.overlapingTimes(roomTimes, procedureTime);
		
		assertThat(retVal).isTrue();
	}
	
	
	@Test
	public void bookAnyRoomAtRequestedTime() {
		MedicalProcedure medicalProcedure = new MedicalProcedure(MEDICAL_PROCEDURE_ID, MEDICAL_PROCEDURE_TYPE, MEDICAL_PROCEDURE_DATE, null,
				MEDICAL_PROCEDURE_DOCTOR, MEDICAL_PROCEDURE_PATIENT, MEDICAL_PROCEDURE_DISCOUNT, MEDICAL_PROCEDURE_BOOKED, MEDICAL_PROCEDURE_DOCTOR_RATED, MEDICAL_PROCEDURE_CLINIC_RATED);
		
		medicalProcedure.setStartTime("12:00");
		medicalProcedure.setEndTime("13:00");
		String reqeustedTime = medicalProcedure.getStartTime() + ":" + medicalProcedure.getEndTime();
		MedicalRoom medicalRoomOne = new MedicalRoom(Long.valueOf(1), "Room 1", 1, DOCTOR_CLINIC);
		MedicalRoom medicalRoomTwo = new MedicalRoom(Long.valueOf(2), "Room 2", 2, DOCTOR_CLINIC);
		List<MedicalRoom> rooms = new ArrayList<MedicalRoom>();
		rooms.add(medicalRoomOne);
		rooms.add(medicalRoomTwo);
		List<String> times = new ArrayList<String>();
		
		Mockito.doReturn(times).when(medicalRoomService).getTimesForChosenDate(medicalProcedure.getDateOfProcedure(),
																				medicalRoomOne.getMedicalProcedures());
		Mockito.doReturn(false).when(medicalRoomService).overlapingTimes(times, reqeustedTime);
		
		boolean retVal = medicalRoomService.bookAnyRoomAtRequestedTime(medicalProcedure, rooms);
		
		assertThat(retVal).isTrue();
	}
	
	@Test
	public void getAvailableTimeSuccess() {
		MedicalProcedure medicalProcedure = new MedicalProcedure(MEDICAL_PROCEDURE_ID, MEDICAL_PROCEDURE_TYPE, MEDICAL_PROCEDURE_DATE, null,
				MEDICAL_PROCEDURE_DOCTOR, MEDICAL_PROCEDURE_PATIENT, MEDICAL_PROCEDURE_DISCOUNT, MEDICAL_PROCEDURE_BOOKED, MEDICAL_PROCEDURE_DOCTOR_RATED, MEDICAL_PROCEDURE_CLINIC_RATED);
		
		medicalProcedure.setStartTime("13:00");
		medicalProcedure.setEndTime("14:00");
		String requestedTime = medicalProcedure.getStartTime() + ":" + medicalProcedure.getEndTime();
		MedicalRoom medicalRoomOne = new MedicalRoom(Long.valueOf(1), "Room 1", 1, DOCTOR_CLINIC);
		medicalRoomOne.setMedicalProcedures(new ArrayList<MedicalProcedure>());
		List<String> times = new ArrayList<String>();
		times.add("11:00:12:00"); 
		times.add("12:00:13:00");
		Mockito.doReturn(times).when(medicalRoomService).getTimesForChosenDate(medicalProcedure.getDateOfProcedure(),
																		medicalRoomOne.getMedicalProcedures());
		Mockito.doReturn(false).when(medicalRoomService).overlapingTimes(times, "7:0:8:0");
		when(doctorService.getAvailableDoctor(medicalProcedure, "7:0:8:0")).thenReturn(MEDICAL_PROCEDURE_DOCTOR);
		
		boolean retVal = medicalRoomService.getAvailableTime(medicalProcedure, medicalRoomOne);
		
		assertThat(retVal).isTrue();
	}

}
