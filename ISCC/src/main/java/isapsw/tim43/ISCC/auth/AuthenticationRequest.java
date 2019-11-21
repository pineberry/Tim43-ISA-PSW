package isapsw.tim43.ISCC.auth;

import lombok.Data;

@Data
public class AuthenticationRequest {

	private String emailAddress;
	private String password;
	public AuthenticationRequest(String emailAddress, String password) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
	}
	
	public AuthenticationRequest() {
		
	}
	
}
