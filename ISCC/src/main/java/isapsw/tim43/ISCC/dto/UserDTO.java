package isapsw.tim43.ISCC.dto;

import lombok.Data;

@Data
public class UserDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String passwordF;
	private String address;
	private String city;
	private String state;
	private String phoneNumber;
	private String healthCareNumber;
}
