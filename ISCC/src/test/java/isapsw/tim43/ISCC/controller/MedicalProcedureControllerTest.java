package isapsw.tim43.ISCC.controller;

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
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
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
class MedicalProcedureControllerTest {

	@InjectMocks
	MedicalProcedureController medicalProcedureController;
	
//	@Mock
//	PatientService patientService;
//	
//	@Mock
//	PatientRepository patientRepository;
//
//	@Mock
//	DoctorService doctorService;
//	
//	@Mock
//	DoctorRepository doctorRepository;
//
//	@Mock
//	MedicalRoomService medicalRoomService;
//	
//	@Mock
//	MedicalRoomRepository medicalRoomRepository;
//	
//	@Mock
//	MedicalProcedureRepository medicalProcedureRepository;
//
	@Mock
    MedicalProcedureService medicalProcedureService;
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
	void testConfirmPredefinedAppointment_successful() throws MailException, InterruptedException {
		
		MedicalProcedure medicalProcedure = new MedicalProcedure(MEDICAL_PROCEDURE_ID, MEDICAL_PROCEDURE_TYPE, MEDICAL_PROCEDURE_DATE, MEDICAL_PROCEDURE_ROOM,
				MEDICAL_PROCEDURE_DOCTOR, MEDICAL_PROCEDURE_PATIENT, MEDICAL_PROCEDURE_DISCOUNT, MEDICAL_PROCEDURE_BOOKED, MEDICAL_PROCEDURE_DOCTOR_RATED, MEDICAL_PROCEDURE_CLINIC_RATED);
		
		MedicalProcedureDTO procedure = new MedicalProcedureDTO(medicalProcedure);
		
		List<MedicalProcedureDTO> procedures = new ArrayList<MedicalProcedureDTO>();
		procedures.add(procedure);
		
		when(medicalProcedureService.confirmPredefinedAppointment(procedure, Long.valueOf(1))).thenReturn(procedures);
		
		ResponseEntity<List<MedicalProcedureDTO>> responseEntity = medicalProcedureController.confirmPredefinedAppointment(procedure, Long.valueOf(1));
		
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	void testConfirmPredefinedAppointment_no_content() throws MailException, InterruptedException {
		
		MedicalProcedure medicalProcedure = new MedicalProcedure(MEDICAL_PROCEDURE_ID, MEDICAL_PROCEDURE_TYPE, MEDICAL_PROCEDURE_DATE, MEDICAL_PROCEDURE_ROOM,
				MEDICAL_PROCEDURE_DOCTOR, MEDICAL_PROCEDURE_PATIENT, MEDICAL_PROCEDURE_DISCOUNT, MEDICAL_PROCEDURE_BOOKED, MEDICAL_PROCEDURE_DOCTOR_RATED, MEDICAL_PROCEDURE_CLINIC_RATED);
		
		MedicalProcedureDTO procedure = new MedicalProcedureDTO(medicalProcedure);
		
		List<MedicalProcedureDTO> procedures = new ArrayList<MedicalProcedureDTO>();
		
		when(medicalProcedureService.confirmPredefinedAppointment(procedure, Long.valueOf(1))).thenReturn(procedures);
		
		ResponseEntity<List<MedicalProcedureDTO>> responseEntity = medicalProcedureController.confirmPredefinedAppointment(procedure, Long.valueOf(1));
		
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}

/*
 *
 *  
    @PutMapping(value = "/predefined_appointment/{patientID}")
    public ResponseEntity<List<MedicalProcedureDTO>> confirmPredefinedAppointment(@RequestBody MedicalProcedureDTO procedure, @PathVariable Long patientID) throws MailException, InterruptedException {
    	
    	
    	List<MedicalProcedureDTO> procedures = medicalProcedureService.confirmPredefinedAppointment(procedure, patientID);
    	
    	if (procedures.isEmpty()) 
    	{
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} else {
    		return new ResponseEntity<>(procedures, HttpStatus.OK);
    	}
    }
 * 
 * */
 