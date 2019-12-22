package isapsw.tim43.ISCC.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import isapsw.tim43.ISCC.dto.ClinicDTO;
import isapsw.tim43.ISCC.dto.DoctorDTO;
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

	public SearchResultClinicsDoctorsDTO searchClinics(SearchClinicParametersDTO parameters) {
		HashMap<Long , ClinicDTO> clinics = new HashMap<Long, ClinicDTO>();
		HashMap<Long, DoctorDTO> doctors = new HashMap<Long, DoctorDTO>();
		for (Clinic clinic : clinicService.findAll()) 
		{
			for (Doctor doctor : doctorService.findAll_()) {
				if (doctor.getClinic().getId() == clinic.getId() && !doctor.getOnVacation()) // lekar iz klinike i ako nije na odmoru
				{
					if (!doctor.getMedicalProcedures().isEmpty()) 
					{ //ako ima zakazane preglede ide dalja provera
						for (MedicalProcedure procedure : doctor.getMedicalProcedures()) 
						{
							SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
							if (formatter.format(procedure.getDateOfProcedure()) != 
									formatter.format(parameters.getDate()) && // ako nisu u isto vreme
									parameters.getDate().after(new Date())) //ako je neko vreme pocev od sutra
							{
								if(!clinics.containsKey(clinic.getId()))
									clinics.put(clinic.getId(), new ClinicDTO(clinic.getId(), clinic.getName(),
											clinic.getAddress(), clinic.getDescription(), clinic.getAverageRating()));

								if(!doctors.containsKey(doctor.getId()))
									doctors.put(doctor.getId(), new DoctorDTO(doctor));
							}
						} 
					}
					else {
						if(!clinics.containsKey(clinic.getId()))
						clinics.put(clinic.getId(), new ClinicDTO(clinic.getId(), clinic.getName(),
								clinic.getAddress(), clinic.getDescription(), clinic.getAverageRating()));

						if(!doctors.containsKey(doctor.getId()))
							doctors.put(doctor.getId(), new DoctorDTO(doctor));
					}
				}
			}
		}
		return new SearchResultClinicsDoctorsDTO(new ArrayList<ClinicDTO>(clinics.values()), new ArrayList<DoctorDTO>(doctors.values()));
	}

	
	
}
