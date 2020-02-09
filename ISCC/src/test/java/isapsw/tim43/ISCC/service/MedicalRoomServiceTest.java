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

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.SpyBean;
import isapsw.tim43.ISCC.model.MedicalProcedure;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class MedicalRoomServiceTest {
	
	@InjectMocks
	@SpyBean
	MedicalRoomService medicalRoomService;
	
	@Test
	public void testGetTimesForCosenRoomSuccess() {
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
	public void testOverlapingTimesSuccess() {
		List<String> roomTimes = new ArrayList<String>();
		String procedureTime = "12:00:13:00";
		roomTimes.add("11:00:11:50");
		roomTimes.add("13:01:14:00");
		boolean retVal = medicalRoomService.overlapingTimes(roomTimes, procedureTime);
		
		assertThat(retVal).isFalse();
	}
	
	@Test
	public void testOverlapingTimesFail() {
		List<String> roomTimes = new ArrayList<String>();
		String procedureTime = "12:00:13:00";
		roomTimes.add("11:00:12:01");
		roomTimes.add("13:01:14:00");
		boolean retVal = medicalRoomService.overlapingTimes(roomTimes, procedureTime);
		
		assertThat(retVal).isTrue();
	}
	
	

}
