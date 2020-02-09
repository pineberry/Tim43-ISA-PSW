package isapsw.tim43.ISCC.service;

import static isapsw.tim43.ISCC.constants.MedicalRoomConstants.ROOM_NAME;
import static isapsw.tim43.ISCC.constants.MedicalRoomConstants.ROOM_NUMBER;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_BOOKED;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_CLINIC_RATED;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_DATE;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_DISCOUNT;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_DOCTOR;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_DOCTOR_RATED;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_ID;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_PATIENT;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_ROOM;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.MEDICAL_PROCEDURE_TYPE;
import static isapsw.tim43.ISCC.constants.MedicalRoomConstants.ROOM_CLINIC;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.model.MedicalRoom;
import isapsw.tim43.ISCC.repository.MedicalProcedureRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicalProcedureServiceTest {
	
	@SpyBean
	private MedicalProcedureService medicalProcedureService;
	
	@MockBean
	private MedicalRoomService medicalRoomService;
	
	@MockBean
	private MedicalProcedureRepository medicalProcedureRepository;
	
	
	@Test
	public void bookRoomSuccess() throws InterruptedException {
	//	MedicalProcedureService medicalProcedureService = new MedicalProcedureService();
	//	MedicalProcedureService mpsSpy = Mockito.spy(medicalProcedureService);
		
		MedicalRoom medicalRoom = new MedicalRoom(Long.valueOf(2), ROOM_NAME, ROOM_NUMBER, ROOM_CLINIC);
		MedicalProcedure medicalProcedure = new MedicalProcedure(MEDICAL_PROCEDURE_ID, MEDICAL_PROCEDURE_TYPE, MEDICAL_PROCEDURE_DATE, null,
				MEDICAL_PROCEDURE_DOCTOR, MEDICAL_PROCEDURE_PATIENT, MEDICAL_PROCEDURE_DISCOUNT, MEDICAL_PROCEDURE_BOOKED, MEDICAL_PROCEDURE_DOCTOR_RATED, MEDICAL_PROCEDURE_CLINIC_RATED);
		
		when(medicalRoomService.findOne(Long.valueOf(2))).thenReturn(medicalRoom);
//		when(mpsSpy.findOne(Long.valueOf(1))).thenReturn(medicalProcedure);
//		Mockito.doReturn(medicalRoom).when(medicalRoomService.findOne(Long.valueOf(2)));
//		when(medicalProcedureService.findOne(Long.valueOf(1))).thenReturn(medicalProcedure);
		Mockito.doReturn(medicalProcedure).when(medicalProcedureService).findOne(Long.valueOf(1));
		List<String> times = new ArrayList<String>();
		when(medicalRoomService.getTimesForChosenDate(medicalProcedure.getDateOfProcedure(),
                medicalRoom.getMedicalProcedures())).thenReturn(times);
		String timeOfNewProcedure = medicalProcedure.getStartTime() + ":" + medicalProcedure.getEndTime();
		when(medicalRoomService.overlapingTimes(times, timeOfNewProcedure)).thenReturn(false);
		when(medicalProcedureRepository.save(medicalProcedure)).thenReturn(medicalProcedure);
	    MedicalProcedureDTO medicalProcedureDTO = medicalProcedureService.bookRoom(Long.valueOf(1), Long.valueOf(2));
		
		assertThat(medicalProcedureDTO).isNotNull();
		assertThat(medicalProcedureDTO.getMedicalRoom())
					.isNotNull()
					.matches(p -> p.getRoomName().equals(ROOM_NAME));
	}
	
	@Test
	public void bookRoomFail() throws InterruptedException {
		MedicalRoom medicalRoom = new MedicalRoom(Long.valueOf(2), ROOM_NAME, ROOM_NUMBER, ROOM_CLINIC);
		MedicalProcedure medicalProcedure = new MedicalProcedure(MEDICAL_PROCEDURE_ID, MEDICAL_PROCEDURE_TYPE, MEDICAL_PROCEDURE_DATE, null,
				MEDICAL_PROCEDURE_DOCTOR, MEDICAL_PROCEDURE_PATIENT, MEDICAL_PROCEDURE_DISCOUNT, MEDICAL_PROCEDURE_BOOKED, MEDICAL_PROCEDURE_DOCTOR_RATED, MEDICAL_PROCEDURE_CLINIC_RATED);
		
		when(medicalRoomService.findOne(Long.valueOf(2))).thenReturn(medicalRoom);
		Mockito.doReturn(medicalProcedure).when(medicalProcedureService).findOne(Long.valueOf(1));
		List<String> times = new ArrayList<String>();
		when(medicalRoomService.getTimesForChosenDate(medicalProcedure.getDateOfProcedure(),
                medicalRoom.getMedicalProcedures())).thenReturn(times);
		String timeOfNewProcedure = medicalProcedure.getStartTime() + ":" + medicalProcedure.getEndTime();
		when(medicalRoomService.overlapingTimes(times, timeOfNewProcedure)).thenReturn(true); // Vraca null kontroleru
		when(medicalProcedureRepository.save(medicalProcedure)).thenReturn(medicalProcedure);
	    MedicalProcedureDTO medicalProcedureDTO = medicalProcedureService.bookRoom(Long.valueOf(1), Long.valueOf(2));
		
		assertThat(medicalProcedureDTO).isNull();
	}
	
	@Test
	public void autoBookRoomSuccess() {
		MedicalProcedure medicalProcedure = new MedicalProcedure(MEDICAL_PROCEDURE_ID, MEDICAL_PROCEDURE_TYPE, MEDICAL_PROCEDURE_DATE, null,
				MEDICAL_PROCEDURE_DOCTOR, MEDICAL_PROCEDURE_PATIENT, MEDICAL_PROCEDURE_DISCOUNT, MEDICAL_PROCEDURE_BOOKED, MEDICAL_PROCEDURE_DOCTOR_RATED, MEDICAL_PROCEDURE_CLINIC_RATED);
		Mockito.doReturn(medicalProcedure).when(medicalProcedureService).findOne(MEDICAL_PROCEDURE_ID);
		when(medicalRoomService.findAvailableAppoinment(medicalProcedure)).thenReturn(medicalProcedure);
		when(medicalProcedureRepository.save(medicalProcedure)).thenReturn(medicalProcedure);
		
		MedicalProcedureDTO medicalProcedureDTO = medicalProcedureService.autoBookRoom(MEDICAL_PROCEDURE_ID);
		
		assertThat(medicalProcedureDTO).isNotNull();
	}
	

}
