package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.dto.PatientDTO;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.service.DoctorService;
import isapsw.tim43.ISCC.service.MedicalRoomService;
import isapsw.tim43.ISCC.service.PatientService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    PatientService patientService;
    
    @Autowired
    DoctorService doctorService;
    
    @Autowired
    MedicalRoomService medicalRoomService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {

        return new ResponseEntity<>(new PatientDTO(patientService.findById(id)), HttpStatus.OK);
    }
    
    @PutMapping(value = "/update")
    public ResponseEntity<PatientDTO> updatePatientsData(@RequestBody PatientDTO user) {

        return new ResponseEntity<PatientDTO>(patientService.updatePatientsData(user), HttpStatus.OK);
    }
    
    @GetMapping(value = "/schedule-appointment")
    public ResponseEntity<MedicalProcedureDTO> scheduleAppointment(@RequestParam(name="date") String date, @RequestParam(name="hour") String hour, 
    		@RequestParam(name="patient") String patient_id, @RequestParam(name="doctor") String doctor_id) throws ParseException, MailException, InterruptedException {

        Doctor doctor = doctorService.findOne(Long.parseLong(doctor_id));
        
        if(doctor!=null) 
        {
	        
	        MedicalProcedureDTO medProcDTO = patientService.scheduleAppointment(
	        		doctor, new MedicalProcedure(doctor.getSpecialized(), new SimpleDateFormat("yyyy-MM-dd").parse(date), 
	        		medicalRoomService.findOne(1), doctor, patientService.findById(Long.parseLong(patient_id)), 0, false), hour);
	        
			return new ResponseEntity<MedicalProcedureDTO>(medProcDTO , HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {

        List<PatientDTO> patientDTOS = patientService.getAllPatients();

        return new ResponseEntity<>(patientDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/number/{healthCareNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientDTO>> searchByHealthCareNumber(@PathVariable String healthCareNumber){
        List<PatientDTO> patientDTOList = patientService.searchByHealthCareNumber(healthCareNumber);

        if (patientDTOList != null) {
            return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/name/{name}/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientDTO>> searchByNameAndLastName(@PathVariable String name,
                                                                                        @PathVariable String lastName){
        List<PatientDTO> patientDTOList = patientService.searchByNameAndLastName(name, lastName);

        if (patientDTOList != null) {
            return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}
