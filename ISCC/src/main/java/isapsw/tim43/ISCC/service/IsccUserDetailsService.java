package isapsw.tim43.ISCC.service;

import java.util.ArrayList;

import isapsw.tim43.ISCC.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.dto.UsersDetailsDTO;

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
		else if (user.getClass() == Nurse.class)
		{
			Nurse nurse = (Nurse)userService.findUserByEmailAddress(emailAddress);
			return new User(nurse.getEmail(), nurse.getPassword(), new ArrayList<>());
		}
		else 
		{
			ClinicCenterAdministrator cca = (ClinicCenterAdministrator)userService.findUserByEmailAddress(emailAddress);
			return new User(cca.getEmail(), cca.getPassword(), new ArrayList<>());
		}

	}

	public UsersDetailsDTO getUsersDetails(String emailAddress) {
		UsersDetailsDTO usersDetailsDTO = null;
		Object user = userService.findUserByEmailAddress(emailAddress);
		if (user.getClass() == Patient.class)
		{
			Patient p = (Patient)userService.findUserByEmailAddress(emailAddress);
			usersDetailsDTO = new UsersDetailsDTO(p.getFirstName(), p.getLastName(), p.getEmail(), "patient", null, false, p.getId());
		}
		else if (user.getClass() == Doctor.class)
		{
			Doctor d = (Doctor)userService.findUserByEmailAddress(emailAddress);
			usersDetailsDTO = new UsersDetailsDTO(d.getFirstName(), d.getLastName(), d.getEmail(), "doctor", null, false, d.getId());
		}
		else if (user.getClass() == ClinicAdministrator.class)
		{
			ClinicAdministrator ca = (ClinicAdministrator)userService.findUserByEmailAddress(emailAddress);
			usersDetailsDTO = new UsersDetailsDTO(ca.getFirstName(), ca.getLastName(), ca.getEmail(), "clinicAdministrator", null, false, ca.getId());
		}
		else if (user.getClass() == Nurse.class)
		{
			Nurse nurse = (Nurse)userService.findUserByEmailAddress(emailAddress);
			usersDetailsDTO = new UsersDetailsDTO(nurse.getFirstName(), nurse.getLastName(), nurse.getEmail(), "nurse", null, false, nurse.getId());
		}
		else 
		{
			ClinicCenterAdministrator cca = (ClinicCenterAdministrator)userService.findUserByEmailAddress(emailAddress);
			usersDetailsDTO = new UsersDetailsDTO(cca.getFirstName(), cca.getLastName(), cca.getEmail(), "clinicCenterAdministrator", null, cca.isFirstLogin(), cca.getId());
		}
		return usersDetailsDTO;
	}

	
	
}
