package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.ReportDTO;
import isapsw.tim43.ISCC.model.Report;
import isapsw.tim43.ISCC.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import isapsw.tim43.ISCC.dto.DoctorDTO;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private ReportService reportService;
	
	@PostMapping(value = "/add", consumes = "application/json")
	public ResponseEntity<DoctorDTO> addDoctor(@RequestBody DoctorDTO doctorDTO){
		doctorDTO = doctorService.save(doctorDTO);

		if(doctorDTO != null){
			return new ResponseEntity<>(doctorDTO, HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DoctorDTO>> getAllDoctors(){
		List<DoctorDTO> doctorDTOList = doctorService.findAll();
		return new ResponseEntity<>(doctorDTOList, HttpStatus.OK);
	}

	@PostMapping(value = "/report", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReportDTO> writeReport(@RequestBody ReportDTO reportDTO) {
		Report report = reportService.mapDTO(reportDTO);

		reportService.save(report);
		return new ResponseEntity<ReportDTO>(reportDTO, HttpStatus.CREATED);
	}

}
