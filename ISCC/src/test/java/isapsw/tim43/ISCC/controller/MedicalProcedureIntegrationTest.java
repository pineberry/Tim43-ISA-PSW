package isapsw.tim43.ISCC.controller;

import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_ID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItem;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class MedicalProcedureIntegrationTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	MedicalProcedureController medicalProcedureController;
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(medicalProcedureController).build();
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void bookRoomSuccess() throws Exception {
		mockMvc.perform(put("/medical/procedure/1/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void bookRoomFail() throws Exception {
		mockMvc.perform(put("/medical/procedure/2/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void automaticallyBookRoomSuccess() throws Exception {
		mockMvc.perform(put("/medical/procedure/auto/book/2")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.startTime").value("7:00"));
	}

}
