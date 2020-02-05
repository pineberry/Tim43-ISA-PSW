package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.ClinicAdministrator;
import lombok.Data;

@Data
public class ClinicAdministratorDTO {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private ClinicDTO clinic;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;

    public ClinicAdministratorDTO() {
    }

    public ClinicAdministratorDTO(Long id, String email, String password, String firstName, String lastName,
                                  String phoneNumber, String address, String city, String state, ClinicDTO clinicName) {
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



    public ClinicAdministratorDTO(ClinicAdministrator clinicAdministrator) {
        this(clinicAdministrator.getId(), clinicAdministrator.getEmail(), clinicAdministrator.getPassword(),
                clinicAdministrator.getFirstName(), clinicAdministrator.getLastName(),
                clinicAdministrator.getPhoneNumber(), clinicAdministrator.getAddress(), clinicAdministrator.getCity(),
                clinicAdministrator.getState(),new ClinicDTO(clinicAdministrator.getClinic()));
    }
}
