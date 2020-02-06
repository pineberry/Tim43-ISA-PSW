package isapsw.tim43.ISCC.dto;

import lombok.Data;

@Data
public class NurseDTO {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private ClinicDTO clinic;

    public NurseDTO() {
    }

    public NurseDTO(Long id, String email, String password, String firstName, String lastName, String phoneNumber, String address,
                    String city, String state, ClinicDTO clinicName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.clinic = clinicName;
    }
}
