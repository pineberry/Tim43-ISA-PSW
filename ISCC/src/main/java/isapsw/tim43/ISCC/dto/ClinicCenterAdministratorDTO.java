package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.ClinicCenterAdministrator;
import lombok.Data;

@Data
public class ClinicCenterAdministratorDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;

    public ClinicCenterAdministratorDTO(String email, String password, String firstName, String lastName,
                                        String phoneNumber, String address, String city, String state) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public ClinicCenterAdministratorDTO() {
    }

    public ClinicCenterAdministratorDTO(ClinicCenterAdministrator clinicCenterAdministrator) {
        this(clinicCenterAdministrator.getEmail(), clinicCenterAdministrator.getPassword(),
                clinicCenterAdministrator.getFirstName(), clinicCenterAdministrator.getLastName(),
                clinicCenterAdministrator.getPhoneNumber(), clinicCenterAdministrator.getAddress(),
                clinicCenterAdministrator.getCity(), clinicCenterAdministrator.getState());
    }
}
