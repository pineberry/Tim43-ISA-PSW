package isapsw.tim43.ISCC.dto;

import lombok.Data;

@Data
public class SearchClinicParametersDTO {

	private String date;
	private String typeOfProcedure;
	private String location;
	private int rating;
	public SearchClinicParametersDTO(String date, String typeOfProcedure, String location, int rating) {
		super();
		this.date = date;
		this.typeOfProcedure = typeOfProcedure;
		this.location = location;
		this.rating = rating;
	}	
	
	
}
