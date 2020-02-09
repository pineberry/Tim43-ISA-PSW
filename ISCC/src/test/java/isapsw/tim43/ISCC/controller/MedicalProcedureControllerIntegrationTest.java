package isapsw.tim43.ISCC.controller;

import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.*;
import static isapsw.tim43.ISCC.constants.PatientConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.model.MedicalProcedure;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class MedicalProcedureControllerIntegrationTest {

	MockMvc mockMvc;
	
	@Autowired
	MedicalProcedureController medicalProcedureController;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(medicalProcedureController).build();
	}

	
	@Test
	void testConfirmPredefinedAppointment_successful() throws Exception {
		
		MedicalProcedureDTO procedureDTO = new MedicalProcedureDTO(new MedicalProcedure(MEDICAL_PROCEDURE_ID,
				MEDICAL_PROCEDURE_TYPE, MEDICAL_PROCEDURE_DATE, MEDICAL_PROCEDURE_ROOM,
				MEDICAL_PROCEDURE_DOCTOR, MEDICAL_PROCEDURE_DISCOUNT, MEDICAL_PROCEDURE_BOOKED, 
				MEDICAL_PROCEDURE_DOCTOR_RATED, MEDICAL_PROCEDURE_CLINIC_RATED));
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		
	    String requestJson = ow.writeValueAsString(procedureDTO);
		
		
		mockMvc.perform(put("/medical/procedure/predefined_appointment/{patientID}", PATIENT_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].id", is(MEDICAL_PROCEDURE_ID.intValue())))
			.andExpect(jsonPath("$[0].patient.id", is(PATIENT_ID.intValue())));
	}
}
