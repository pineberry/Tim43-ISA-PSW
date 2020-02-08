package isapsw.tim43.ISCC.controller;

import static org.junit.Assert.assertEquals;

import static isapsw.tim43.ISCC.constants.DoctorConstants.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import isapsw.tim43.ISCC.auth.AuthenticationRequest;
import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.dto.UsersDetailsDTO;
import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.ClinicAdministrator;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.model.MedicalRoom;
import isapsw.tim43.ISCC.model.Nurse;
import isapsw.tim43.ISCC.model.Prescription;
import isapsw.tim43.ISCC.model.ProcedureType;
import isapsw.tim43.ISCC.service.DoctorService;
import isapsw.tim43.ISCC.service.IsccUserDetailsService;
import isapsw.tim43.ISCC.service.JWTUtil;
import isapsw.tim43.ISCC.service.MedicalRoomService;
import isapsw.tim43.ISCC.service.PatientService;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
//@TestPropertySource("classpath:application-test.properties")
class PatientControllerTest {

	private static final String URL_PREFIX = "/patient";

	@Autowired
    private TestRestTemplate restTemplate;
	
	private String jwt;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@MockBean
    private PatientService patientServiceMock;
	
	@MockBean
	private JWTUtil jwtUtilMock;
	
	@MockBean
	private IsccUserDetailsService isccUserDetailsServiceMock;
	
	@MockBean
	private DoctorService doctorService;
	
	@MockBean
	private MedicalRoomService medicalRoomService;
	 
//	@PostConstruct
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders
//                .webAppContextSetup(webApplicationContext)
//                .apply(springSecurity())
//                .build();
//    } 

//	@BeforeEach
//	public void login() throws JsonMappingException, JsonProcessingException {
//		ResponseEntity<String> responseEntity = 
//				restTemplate.postForEntity("/authenticate", 
//						new AuthenticationRequest("patient@mail.com", "password"), 
//						String.class);
//
//		ObjectMapper mapper = new ObjectMapper();
//		UsersDetailsDTO usersDetailsDTO = mapper.readValue(responseEntity.getBody(), UsersDetailsDTO.class);
//		jwt = "Bearer " + usersDetailsDTO.getJwt();
//		System.out.println("\n\n\n\n" + jwt + "\n\n\n\n");
//	}

	
	/* (3.10) Postupak zakazivanja pregleda (pacijent) */
	@Test
	void testScheduleAppointment() throws Exception {
		
//		AuthenticationRequest data = new AuthenticationRequest("teodora.95mandic@gmail.com", "pass");
//		
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//		
//		String auth = ow.writeValueAsString(data);
//		
		String jwt = "";
//		
//		this.mockMvc.perform(post("/authenticate").contentType(contentType)
//				.content(auth))
//				.andExpect(status().isOk());
		
		UserDetails userDetails = isccUserDetailsServiceMock.loadUserByUsername("teodora.95mandic@gmail.com");
		
		Mockito.when(jwtUtilMock.generateToken(userDetails)).thenReturn(jwt);
		
		List<MedicalProcedure> medicalProcedures = new ArrayList<MedicalProcedure>();
		
		ProcedureType specialized = new ProcedureType("Ime procedure", "Opis procedure", 120);
		
		List<Date> appointmentDates = new ArrayList<Date>();
		List<Doctor> doctors = new ArrayList<Doctor>();
		List<MedicalRoom> medicalRooms = new ArrayList<MedicalRoom>();
		Map<Double, MedicalProcedure> pricelist = new HashMap<Double, MedicalProcedure>();
		List<ClinicAdministrator> administrators = new ArrayList<ClinicAdministrator>();
		List<Nurse> nurses = new ArrayList<Nurse>();
		List<Prescription> prescriptions = new ArrayList<Prescription>();
		
		Clinic clinic = new Clinic(1L, "name", "address", "description", 3.0, appointmentDates, doctors,
				medicalRooms, pricelist, administrators, nurses, prescriptions); 
		
		Doctor doctor = new Doctor(DOCTOR_ID, DOCTOR_EMAIL, DOCTOR_PASSWORD, DOCTOR_FIRST_NAME, DOCTOR_LAST_NAME,
				DOCTOR_ADDRESS, DOCTOR_CITY, DOCTOR_STATE, DOCTOR_PHONE_NUMBER, DOCTOR_AVERAGE_RATING,
				DOCTOR_WORKING_TIME_START, DOCTOR_WORKING_TIME_END, DOCTOR_ON_VACATION, specialized, medicalProcedures, clinic);
		
		MedicalProcedure medicalProcedure = new MedicalProcedure(doctor.getSpecialized(), 
				new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-15"), medicalRoomService.findOne(1), 
				doctor, patientServiceMock.findById(Long.valueOf(2)), 0, false);
		
		MedicalProcedureDTO medicalProcedureDTO = new MedicalProcedureDTO(medicalProcedure);
		
		Mockito.when(patientServiceMock.scheduleAppointment(doctor, medicalProcedure, "10")).thenReturn(medicalProcedureDTO);
		
		mockMvc.perform(get(URL_PREFIX + "/schedule-appointment")
				.header("Authorization", jwt)
				.param("date", "2020-03-15")
				.param("hour", "10")
				.param("patient", "2")
				.param("doctor", "9"))
				.andExpect(status().isOk());
		
//		HttpHeaders headers = new HttpHeaders();
//
//    	headers.add("Authorization", jwt);
//    	
//    	HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
//    	
//    	ResponseEntity<MedicalProcedureDTO> responseEntity =
//    			restTemplate.exchange(URL_PREFIX + "/schedule-appointment?date=2020-03-15&hour=10&patient=2&doctor=9", HttpMethod.GET, httpEntity, MedicalProcedureDTO.class);
//    	
//    	MedicalProcedureDTO procedure = responseEntity.getBody();
//    	
//    	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    	assertNotNull(procedure);
//    	assertFalse(procedure.isBooked());
//        assertEquals("10:00", procedure.getStartTime());
	}

}
