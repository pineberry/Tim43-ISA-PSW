package isapsw.tim43.ISCC.controller;

import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_ADDRESS;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_ID;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_NAME;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_EMAIL;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_ID;
import static isapsw.tim43.ISCC.constants.SearchConstants.SEARCH_DATE;
import static isapsw.tim43.ISCC.constants.SearchConstants.SEARCH_LOCATION;
import static isapsw.tim43.ISCC.constants.SearchConstants.SEARCH_PROCEDURE;
import static isapsw.tim43.ISCC.constants.SearchConstants.SEARCH_RATING;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
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
public class SearchControllerIntegrationTest {
	
	MockMvc mockMvc;
	
	@Autowired
	SearchController searchController;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
	}
	
	@Test
	public void searchClincs() throws Exception {
		
		mockMvc.perform(get("/search/clinic")
				.contentType(MediaType.APPLICATION_JSON)
				.param("date", SEARCH_DATE)
				.param("typeOfProcedure", SEARCH_PROCEDURE)
				.param("location", SEARCH_LOCATION)
				.param("rating", SEARCH_RATING)
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.clinics[*]", hasSize(1)))
			.andExpect(jsonPath("$.clinics[0].id", is(CLINIC_ID.intValue())))
			.andExpect(jsonPath("$.clinics[0].name", is(CLINIC_NAME)))
			.andExpect(jsonPath("$.clinics[0].address", is(CLINIC_ADDRESS)))
			.andExpect(jsonPath("$.doctors[*]", hasSize(1)))
			.andExpect(jsonPath("$.doctors[0].id", is(DOCTOR_ID.intValue())))
			.andExpect(jsonPath("$.doctors[0].email", is(DOCTOR_EMAIL)));
	}
	
	@Test
	public void searchClincs_empty() throws Exception {
		
		mockMvc.perform(get("/search/clinic")
				.contentType(MediaType.APPLICATION_JSON)
				.param("date", SEARCH_DATE)
				.param("typeOfProcedure", "procedure")
				.param("location", SEARCH_LOCATION)
				.param("rating", SEARCH_RATING)
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.clinics[*]", hasSize(0)))
			.andExpect(jsonPath("$.doctors[*]", hasSize(0)));
	}
}
