package isapsw.tim43.ISCC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isapsw.tim43.ISCC.auth.AuthenticationRequest;
import isapsw.tim43.ISCC.auth.AuthenticationResponse;
import isapsw.tim43.ISCC.service.IsccUserDetailsService;
import isapsw.tim43.ISCC.service.JWTUtil;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IsccUserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping
	public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
	{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authenticationRequest.getEmailAddress(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Nepostojeca email adresa ili netacan password");
		}
		final UserDetails userDetails = 
				userDetailsService.loadUserByUsername(authenticationRequest.getEmailAddress());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
}
