package isapsw.tim43.ISCC.service;

import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_NAME;
import static isapsw.tim43.ISCC.constants.DoctorConstants.DOCTOR_EMAIL;
import static isapsw.tim43.ISCC.constants.SearchConstants.SEARCH_DATE;
import static isapsw.tim43.ISCC.constants.SearchConstants.SEARCH_LOCATION;
import static isapsw.tim43.ISCC.constants.SearchConstants.SEARCH_PROCEDURE;
import static isapsw.tim43.ISCC.constants.SearchConstants.SEARCH_RATING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import isapsw.tim43.ISCC.dto.SearchClinicParametersDTO;
import isapsw.tim43.ISCC.dto.SearchResultClinicsDoctorsDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class SearchServiceIntegrationTest {
	
	@Autowired
	SearchService searchService;
	
	
	@Test
	public void searchClinic_success() throws NumberFormatException, ParseException {
		
		SearchResultClinicsDoctorsDTO results = searchService.searchClinics(
				new SearchClinicParametersDTO(SEARCH_DATE, SEARCH_PROCEDURE, SEARCH_LOCATION, Integer.parseInt(SEARCH_RATING)));
		
		assertThat(results)
			.isNotNull();
		assertThat(results.getClinics())
			.isNotNull()
			.isNotEmpty()
			.allMatch(p -> p.getName().equals(CLINIC_NAME));
		assertThat(results.getDoctors())
			.isNotNull()
			.isNotEmpty()
			.allMatch(p -> p.getEmail().equals(DOCTOR_EMAIL));
	}
	
	@Test
	public void searchClinics_no_procedure() throws NumberFormatException, ParseException {
		
		SearchResultClinicsDoctorsDTO results = searchService.searchClinics(
				new SearchClinicParametersDTO(SEARCH_DATE, "procedura", SEARCH_LOCATION, Integer.parseInt(SEARCH_RATING)));
	
		assertThat(results).isNotNull();
		assertThat(results.getClinics())
			.isNotNull()
			.isEmpty();
		assertThat(results.getDoctors())
			.isNotNull()
			.isEmpty();
	}
	
	@Test
	public void searchClinics_date_in_past() throws NumberFormatException, ParseException {
		SearchResultClinicsDoctorsDTO results = searchService.searchClinics(
				new SearchClinicParametersDTO("2019-04-07", SEARCH_PROCEDURE, SEARCH_LOCATION, Integer.parseInt(SEARCH_RATING)));
	
		assertThat(results).isNotNull();
		assertThat(results.getClinics())
			.isNotNull()
			.isEmpty();
		assertThat(results.getDoctors())
			.isNotNull()
			.isEmpty();
	}
}
