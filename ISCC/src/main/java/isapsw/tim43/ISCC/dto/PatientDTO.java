package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.Patient;
import lombok.Data;

@Data
public class PatientDTO {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String healthCareNumber;
    private String address;
    private String city;
    private String state;
    private String phoneNumber;

    public PatientDTO() {
    }

    public PatientDTO(Long id, String email, String password, String firstName, String lastName, String healthCareNumber,
                      String address, String city, String state, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.healthCareNumber = healthCareNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
    }

    public PatientDTO(Patient patient) {
        this(patient.getId(), patient.getEmail(), null, patient.getFirstName(), patient.getLastName(),
                patient.getHealthCareNumber(), patient.getAddress(), patient.getCity(), patient.getState(),
                patient.getPhoneNumber());
    }

}
