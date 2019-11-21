package isapsw.tim43.ISCC.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.model.ClinicAdministrator;
import isapsw.tim43.ISCC.model.ClinicCenterAdministrator;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.Patient;

@Service
public class IsccUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
		Object user = userService.findUserByEmailAddress(emailAddress);
		if (user.getClass() == Patient.class)
		{
			Patient p = (Patient)userService.findUserByEmailAddress(emailAddress);
			return new User(p.getEmail(), p.getPassword(), new ArrayList<>());
		}
		else if (user.getClass() == Doctor.class)
		{
			Doctor d = (Doctor)userService.findUserByEmailAddress(emailAddress);
			return new User(d.getEmail(), d.getPassword(), new ArrayList<>());
		}
		else if (user.getClass() == ClinicAdministrator.class)
		{
			ClinicAdministrator ca = (ClinicAdministrator)userService.findUserByEmailAddress(emailAddress);
			return new User(ca.getEmail(), ca.getPassword(), new ArrayList<>());
		}
		else 
		{
			ClinicCenterAdministrator cca = (ClinicCenterAdministrator)userService.findUserByEmailAddress(emailAddress);
			return new User(cca.getEmail(), cca.getPassword(), new ArrayList<>());
		}

	}

	
	
}
