/**
 * Test class for SearchController which gets all clinics with doctors at specified time
 */
package isapsw.tim43.ISCC.controller;

import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_ADDRESS;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_ADMINISTRATORS;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_AVERAGE_RATING;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_DESCRIPTION;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_DOCTORS;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_ID;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_MEDICAL_ROOMS;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_NAME;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_NURSES;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_PERSCRIPTIONS;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_ADDRESS;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_AVERAGE_RATING;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_CITY;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_CLINIC;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_EMAIL;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_FIRST_NAME;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_ID;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_LAST_NAME;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_ON_VACATION;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_PASSWORD;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_PHONE_NUMBER;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_PROCEDURES;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_SPECIALIZED;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_STATE;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_WORKING_TIME_END;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_WORKING_TIME_START;
import static isapsw.tim43.ISCC.constants.SearchConstants.SEARCH_DATE;
import static isapsw.tim43.ISCC.constants.SearchConstants.SEARCH_LOCATION;
import static isapsw.tim43.ISCC.constants.SearchConstants.SEARCH_PROCEDURE;
import static isapsw.tim43.ISCC.constants.SearchConstants.SEARCH_RATING;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import isapsw.tim43.ISCC.dto.SearchClinicParametersDTO;
import isapsw.tim43.ISCC.dto.SearchResultClinicsDoctorsDTO;
import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.service.SearchService;



@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchControllerTest {

	MockMvc mockMvc;
	
	protected WebApplicationContext wac;
	
	@Autowired
	SearchController searchController;
	
	@MockBean
	SearchService searchService;
	
	SearchResultClinicsDoctorsDTO results = new SearchResultClinicsDoctorsDTO(new ArrayList<Clinic>(), new ArrayList<Doctor>());
	
	@Before
	public void setup() throws Exception {
		//this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		this.mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
		results.getClinics().add(new Clinic(CLINIC_ID, CLINIC_NAME, CLINIC_ADDRESS, CLINIC_DESCRIPTION,
				CLINIC_AVERAGE_RATING, new ArrayList<Date>(), CLINIC_DOCTORS,
				CLINIC_MEDICAL_ROOMS, new HashMap<Double, MedicalProcedure>(), 
				CLINIC_ADMINISTRATORS, CLINIC_NURSES, CLINIC_PERSCRIPTIONS));
		results.getDoctors().add(new Doctor(DOCTOR_ID, DOCTOR_EMAIL, DOCTOR_PASSWORD, DOCTOR_FIRST_NAME, DOCTOR_LAST_NAME, 
				DOCTOR_ADDRESS, DOCTOR_CITY, DOCTOR_STATE, DOCTOR_PHONE_NUMBER, DOCTOR_AVERAGE_RATING, DOCTOR_WORKING_TIME_START,
				DOCTOR_WORKING_TIME_END, DOCTOR_ON_VACATION, DOCTOR_SPECIALIZED, DOCTOR_PROCEDURES, DOCTOR_CLINIC));
	}
	
	@Test
	public void searchClinics_success() throws Exception {
		
		when(searchService.searchClinics(
				new SearchClinicParametersDTO(SEARCH_DATE, SEARCH_PROCEDURE, SEARCH_LOCATION, Integer.parseInt(SEARCH_RATING))
				)).thenReturn(results);
		
		mockMvc.perform(get("/search/clinic")
				.contentType(MediaType.APPLICATION_JSON)
				.param("date", SEARCH_DATE)
				.param("typeOfProcedure", SEARCH_PROCEDURE)
				.param("location", SEARCH_LOCATION)
				.param("rating", SEARCH_RATING)
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.clinics[0].name", is(CLINIC_NAME)))
			.andExpect(jsonPath("$.doctors[0].email", is(DOCTOR_EMAIL)));
	}
	
	
}
