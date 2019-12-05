package isapsw.tim43.ISCC.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;

@Service
public class AvailableAppointmentsService {

	@Autowired
	private DoctorService doctorService;
	
	public List<String> checkUnavailableAppointments(long id, Date date)
	{
		Doctor doctor = doctorService.findOne(id);
		List<String> unavailable = new ArrayList<String>();
		if (!doctor.getMedicalProcedures().isEmpty()) { //ako ima neke preglede zakazane proveriti da li su ti pregledi onog datuma koji je trazen
			for (MedicalProcedure mp : doctor.getMedicalProcedures()) {
				if (mp.getDateOfProcedure().getMonth() == date.getMonth()
						&& mp.getDateOfProcedure().getDate() == date.getDate()
						&& mp.getDateOfProcedure().getYear() == date.getYear()) {
					unavailable.add(mp.getStartTime()+"-"+mp.getEndTime());
				}
			} 
		}
		return unavailable;
	}
	
}
