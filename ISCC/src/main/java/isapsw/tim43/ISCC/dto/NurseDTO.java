package isapsw.tim43.ISCC.dto;

import lombok.Data;

@Data
public class NurseDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String clinicName;

    public NurseDTO() {
    }

    public NurseDTO(String email, String password, String firstName, String lastName, String phoneNumber, String address,
                    String city, String state, String clinicName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.clinicName = clinicName;
    }
}
