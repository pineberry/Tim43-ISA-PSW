package isapsw.tim43.ISCC.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.model.Patient;

@Service
public class IsccUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
		Patient patient = userService.findUserByEmailAddress(emailAddress);
		return new User(patient.getEmail(), patient.getPassword(), new ArrayList<>());
	}

	
	
}
