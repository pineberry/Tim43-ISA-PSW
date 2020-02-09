package isapsw.tim43.ISCC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.service.ClinicService;
import isapsw.tim43.ISCC.service.DoctorService;
import isapsw.tim43.ISCC.service.MedicalProcedureService;

@RestController
@RequestMapping(value = "/rate")
public class RatingController {

	@Autowired
    private MedicalProcedureService medicalProcedureService;
	
	@Autowired
	private ClinicService clinicService;
	
	@Autowired
	private DoctorService doctorService;
	
	
	
	@PutMapping(value = "/clinic/{rate}/{id}/{appointmentId}/{patientID}")
    public ResponseEntity<List<MedicalProcedureDTO>> getPatientsProcedures(@PathVariable int rate, @PathVariable Long id, @PathVariable Long appointmentId, @PathVariable Long patientID)
    {
		/*
		 * update ocenu klinike
		 * update appointmenta na konto kog je pacijent ocenio
		 * */
		
		medicalProcedureService.updateMedicalProcedure(appointmentId, "clinic");
		clinicService.updateClinicRate(id, rate);
		
    	if (medicalProcedureService.getPatientsProcedures(patientID).isEmpty()) 
    	{
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} else {
    		return new ResponseEntity<>(medicalProcedureService.getPatientsPastProcedures(patientID), HttpStatus.OK);
    	}
    }
	
	@PutMapping(value = "/doctor/{rate}/{id}/{appointmentId}/{patientID}")
    public ResponseEntity<List<MedicalProcedureDTO>> getPatentsProcedures(@PathVariable int rate, @PathVariable Long id, @PathVariable Long appointmentId, @PathVariable Long patientID)
    {
		medicalProcedureService.updateMedicalProcedure(appointmentId, "doctor");
		doctorService.updateDoctorRate(id, rate);
		
    	if (medicalProcedureService.getPatientsProcedures(patientID).isEmpty()) 
    	{
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} else {
    		return new ResponseEntity<>(medicalProcedureService.getPatientsPastProcedures(patientID), HttpStatus.OK);
    	}
    }
	
}
