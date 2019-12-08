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
import isapsw.tim43.ISCC.service.AvailableAppointmentsService;
import isapsw.tim43.ISCC.service.DoctorService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private AvailableAppointmentsService appointmentsService;
	
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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable String id)
	{
		return new ResponseEntity<DoctorDTO>(doctorService.findOne_(Long.parseLong(id)), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/available-appointments")
	public ResponseEntity<List<String>> getAvailableAppointments(@RequestParam(name = "date") String date, @PathVariable String id) throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss");  
		Date date_ = formatter.parse(date.substring(0, 24));
		System.out.println(date_);
		List<String> unavailableAppointments = appointmentsService.checkUnavailableAppointments(Long.parseLong(id), date_);
		return new ResponseEntity<List<String>>(unavailableAppointments, HttpStatus.OK);
	}

}
