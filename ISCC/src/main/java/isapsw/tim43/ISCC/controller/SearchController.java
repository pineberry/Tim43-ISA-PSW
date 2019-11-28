package isapsw.tim43.ISCC.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isapsw.tim43.ISCC.dto.ProcedureTypeDTO;
import isapsw.tim43.ISCC.dto.SearchClinicParametersDTO;
import isapsw.tim43.ISCC.dto.SearchResultClinicsDoctorsDTO;
import isapsw.tim43.ISCC.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	private SearchService searchService;

	@GetMapping(value = "/clinic")
	public ResponseEntity<SearchResultClinicsDoctorsDTO> searchClinics(@RequestParam(name="date") String date, @RequestParam(name="typeOfProcedure") String procedure, 
			@RequestParam(name="location") String location, @RequestParam(name="rating") String rating) throws NumberFormatException, ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		SimpleDateFormat formatter_ = new SimpleDateFormat("yyyy-MM-dd");
		Date date_ = formatter_.parse(date);
		
		SearchClinicParametersDTO parameters = new SearchClinicParametersDTO(date_, procedure, location, Integer.parseInt(rating));
		return new ResponseEntity<SearchResultClinicsDoctorsDTO>(searchService.searchClinics(parameters), HttpStatus.OK);
	}
	
}
