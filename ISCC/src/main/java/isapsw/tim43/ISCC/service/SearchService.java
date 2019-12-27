package isapsw.tim43.ISCC.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.dto.SearchClinicParametersDTO;
import isapsw.tim43.ISCC.dto.SearchResultClinicsDoctorsDTO;
import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;

@Service
public class SearchService {
	
	@Autowired 
	private ClinicService clinicService;
	
	@Autowired
	private DoctorService doctorService;

	public SearchResultClinicsDoctorsDTO searchClinics(SearchClinicParametersDTO parameters) throws ParseException {
		
		SimpleDateFormat formatter_ = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter_.parse(parameters.getDate());
		
		ArrayList<Clinic> clinics = new ArrayList<Clinic>();
		ArrayList<Doctor> doctors = new ArrayList<Doctor>();
		
		
		for (Clinic clinic : clinicService.findAll()) 
		{
			for (Doctor doctor : doctorService.findAll_()) {
				if (doctor.getClinic().getId() == clinic.getId() && !doctor.getOnVacation() 
						&& doctor.getSpecialized().getTypeName().equals(parameters.getTypeOfProcedure())) // lekar iz klinike i ako nije na odmoru 
				{
					if (!doctor.getMedicalProcedures().isEmpty()) 
					{ //ako ima zakazane preglede ide dalja provera
						for (MedicalProcedure procedure : doctor.getMedicalProcedures()) 
						{
							SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
							if (formatter.format(procedure.getDateOfProcedure()) != 
									formatter.format(date) && // ako nisu u isto vreme
									date.after(new Date())) //ako je neko vreme pocev od sutra
							{
								if (!clinics.contains(clinic)) {
									clinics.add(clinic);
								}
								doctors.add(doctor);
							}
						} 
					}
					else {
						if (!clinics.contains(clinic)) {
							clinics.add(clinic);
						}
						doctors.add(doctor);
					}
				}
			}
		}
		return new SearchResultClinicsDoctorsDTO(clinics, doctors);
	}

	
	
}
