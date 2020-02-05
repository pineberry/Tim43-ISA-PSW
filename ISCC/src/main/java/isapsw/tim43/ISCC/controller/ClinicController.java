package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.ClinicDTO;
import isapsw.tim43.ISCC.dto.DoctorDTO;
import isapsw.tim43.ISCC.dto.MedicalProcedureDTO;
import isapsw.tim43.ISCC.dto.MedicalRoomDTO;
import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.service.ClinicService;
import isapsw.tim43.ISCC.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/clinic")
public class ClinicController {

    @Autowired
    ClinicService clinicService;

    @Autowired
    DoctorService doctorService;

    @GetMapping(value = "/clinics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        List<Clinic> clinics = clinicService.findAll();
        List<ClinicDTO> clinicsDTO = new ArrayList<ClinicDTO>();
        for (Clinic clinic : clinics) {
            ClinicDTO clinicDTO = new ClinicDTO(clinic);
            clinicsDTO.add(clinicDTO);
        }

        return new ResponseEntity<>(clinicsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClinicDTO> getClinicById(@PathVariable("id") Long clinicID) {

    	if(clinicService.findOne(clinicID) == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	} else {
			return new ResponseEntity<ClinicDTO>(new ClinicDTO(clinicService.findOne(clinicID)),HttpStatus.OK);
		}
    }

    @GetMapping(value = "/single/{id}")
    public ResponseEntity<ClinicDTO> test(@PathVariable("id") Long clinicID) {

    	if(clinicService.findOne(clinicID) == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	} else {
			return new ResponseEntity<ClinicDTO>(new ClinicDTO(clinicService.findOne(clinicID)),HttpStatus.OK);
		}
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicDTO> getClinic(@PathVariable String name){
        ClinicDTO clinicDTO = clinicService.getClinic(name);

        if (clinicDTO != null) {
            return new ResponseEntity<>(clinicDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{name}/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicalRoomDTO>> getClinicRooms(@PathVariable String name){
        List<MedicalRoomDTO> medicalRoomDTOList = clinicService.getRooms(name);

        if (medicalRoomDTOList != null) {
            return new ResponseEntity<>(medicalRoomDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "doctors/{id}")
    public ResponseEntity<List<DoctorDTO>> getDoctorsByClinic(@PathVariable Long id){
        List<DoctorDTO> doctorDTOS = doctorService.getDoctorsByClinic(id);
        if(doctorDTOS == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(doctorDTOS, HttpStatus.OK);
        }
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<Void> editClinic(@RequestBody ClinicDTO clinicDTO){
        clinicDTO = clinicService.edit(clinicDTO);

        if (clinicDTO != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/report/{id}")
    public ResponseEntity<ClinicDTO> getClinicReport(@PathVariable Long id){
       ClinicDTO clinicDTO = clinicService.getClinicReport(id);
       return new ResponseEntity<>(clinicDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/income/{startDate}/{endDate}/{id}")
    public ResponseEntity<Double> getClinicIncome(@PathVariable Long id,
                                                  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        double income = clinicService.getClinicIncome(id, startDate, endDate);
        return new ResponseEntity<>(income, HttpStatus.OK);
    }

    @GetMapping(value = "/predefined/{name}")
    public ResponseEntity<List<MedicalProcedureDTO>> getPredefinedProcedures(@PathVariable String name){
        List<MedicalProcedureDTO> medicalProcedureDTOList = clinicService.getPredefinedProcedures(name);

        if (medicalProcedureDTOList != null) {
            return new ResponseEntity<>(medicalProcedureDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
