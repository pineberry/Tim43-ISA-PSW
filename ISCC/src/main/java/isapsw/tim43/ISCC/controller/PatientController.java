package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.dto.PatientDTO;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.model.Patient;
import isapsw.tim43.ISCC.service.DoctorService;
import isapsw.tim43.ISCC.service.MedicalRoomService;
import isapsw.tim43.ISCC.service.PatientService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        Patient patient = patientService.findById(id);

        return new ResponseEntity<>(new PatientDTO(patient), HttpStatus.OK);
    }
    
    @GetMapping(value = "/schedule-appointment")
    public ResponseEntity<MedicalProcedureDTO> scheduleAppointment(@RequestParam(name="date") String date, @RequestParam(name="hour") String hour, 
    		@RequestParam(name="patient") String patient_id, @RequestParam(name="doctor") String doctor_id) throws ParseException, MailException, InterruptedException {

        Doctor doctor = doctorService.findOne(Long.parseLong(doctor_id));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Patient patient = patientService.findById(Long.parseLong(patient_id));
        
        return new ResponseEntity<MedicalProcedureDTO>(
        		patientService.scheduleAppointment(patient, doctor, 
        				new MedicalProcedure(doctor.getSpecialized(), formatter.parse(date), medicalRoomService.findOne(1), doctor, 0, 0, false), hour),
        		HttpStatus.OK);
    }
    
    
}
