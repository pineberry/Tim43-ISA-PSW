package isapsw.tim43.ISCC.dto;

import java.util.ArrayList;

import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.Doctor;
import lombok.Data;

@Data
public class SearchResultClinicsDoctorsDTO {
	
	private ArrayList<Clinic> clinics;
	private ArrayList<Doctor> doctors;
	public SearchResultClinicsDoctorsDTO(ArrayList<Clinic> clinics, ArrayList<Doctor> doctors) {
		super();
		this.clinics = clinics;
		this.doctors = doctors;
	}
	public SearchResultClinicsDoctorsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}