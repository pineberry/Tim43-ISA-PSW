package isapsw.tim43.ISCC.dto;

import lombok.Data;

@Data
public class UsersDetailsDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String typeOfUser;
	private String jwt;
	private boolean firstLogin;
	
	public UsersDetailsDTO() {
	
	}

	public UsersDetailsDTO(String firstName, String lastName, String email, String typeOfUser, String jwt, boolean firstLogin) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.typeOfUser = typeOfUser;
		this.jwt = jwt;
		this.firstLogin = firstLogin;
	}
	
}
