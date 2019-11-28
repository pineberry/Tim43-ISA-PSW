package isapsw.tim43.ISCC.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SearchClinicParametersDTO {

	private Date date;
	private String typeOfProcedure;
	private String location;
	private int rating;
	public SearchClinicParametersDTO(Date date, String typeOfProcedure, String location, int rating) {
		super();
		this.date = date;
		this.typeOfProcedure = typeOfProcedure;
		this.location = location;
		this.rating = rating;
	}	
	
	
}
