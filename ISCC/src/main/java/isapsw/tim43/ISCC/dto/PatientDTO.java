package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.Patient;

public class PatientDTO {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String healthCareNumber;

    public PatientDTO() {
    }

    public PatientDTO(Long id, String email, String password, String firstName, String lastName, String healthCareNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.healthCareNumber = healthCareNumber;
    }

    public PatientDTO(Patient patient) {
        this(patient.getId(), patient.getEmail(), patient.getPassword(), patient.getFirstName(), patient.getLastName(),
                patient.getHealthCareNumber());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHealthCareNumber() {
        return healthCareNumber;
    }

    public void setHealthCareNumber(String healthCareNumber) {
        this.healthCareNumber = healthCareNumber;
    }
}
