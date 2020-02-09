package isapsw.tim43.ISCC.controller;

import static isapsw.tim43.ISCC.constants.PatientConstants.*;
import static isapsw.tim43.ISCC.constants.MedicalProcedureConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class PatientControllerIntegrationTest {
	
	MockMvc mockMvc;
		
	
	@Autowired
	PatientController patientController;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
	}

//	@Transactional
	@Test
	void testScheduleAppointment() throws Exception {
		mockMvc.perform(get("/patient/schedule-appointment")
				.contentType(MediaType.APPLICATION_JSON)
				.param("date", PATIENT_DATE_SCHEDULE)
				.param("hour", PATIENT_HOUR_SCHEDULE)
				.param("patient", PATIENT_ID_SCHEDULE)
				.param("doctor", PATIENT_DOCTOR_SCHEDULE)
				)
		.andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.id", is(MEDICAL_PROCEDURE_ID.intValue())));
	}

}
