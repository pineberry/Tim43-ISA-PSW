package isapsw.tim43.ISCC.service;

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
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import isapsw.tim43.ISCC.dto.SearchClinicParametersDTO;
import isapsw.tim43.ISCC.dto.SearchResultClinicsDoctorsDTO;
import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {

	@Autowired
	private SearchService searchService;
	
	@MockBean
	private ClinicService clinicService;
	
	@MockBean
	private DoctorService doctorService;
	
	@Test
	public void searchClinics() throws NumberFormatException, ParseException {
	
		List<Clinic> clinics = new ArrayList<>();
		List<Doctor> doctors = new ArrayList<>();
		
		clinics.add(new Clinic(CLINIC_ID, CLINIC_NAME, CLINIC_ADDRESS, CLINIC_DESCRIPTION,
				CLINIC_AVERAGE_RATING, new ArrayList<Date>(), CLINIC_DOCTORS,
				CLINIC_MEDICAL_ROOMS, new HashMap<Double, MedicalProcedure>(), 
				CLINIC_ADMINISTRATORS, CLINIC_NURSES, CLINIC_PERSCRIPTIONS));
		
		doctors.add(new Doctor(DOCTOR_ID, DOCTOR_EMAIL, DOCTOR_PASSWORD, DOCTOR_FIRST_NAME, DOCTOR_LAST_NAME, 
				DOCTOR_ADDRESS, DOCTOR_CITY, DOCTOR_STATE, DOCTOR_PHONE_NUMBER, DOCTOR_AVERAGE_RATING, DOCTOR_WORKING_TIME_START,
				DOCTOR_WORKING_TIME_END, DOCTOR_ON_VACATION, DOCTOR_SPECIALIZED, DOCTOR_PROCEDURES, DOCTOR_CLINIC));
		
		when(clinicService.findAll()).thenReturn(clinics);
		when(doctorService.findAll_()).thenReturn(doctors);
		
		SearchResultClinicsDoctorsDTO results = searchService.searchClinics(
				new SearchClinicParametersDTO(SEARCH_DATE, SEARCH_PROCEDURE, SEARCH_LOCATION, Integer.parseInt(SEARCH_RATING)));
		
		assertThat(results).isNotNull();
		assertThat(results.getClinics())
			.isNotNull()
			.isNotEmpty()
			.allMatch(p -> p.getName().equals(CLINIC_NAME));
		assertThat(results.getDoctors())
			.isNotNull()
			.isNotEmpty()
			.allMatch(p -> p.getEmail().equals(DOCTOR_EMAIL));
	
	}
}
