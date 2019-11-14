package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.ClinicAdministrator;

public class ClinicAdministratorDTO {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String clinicName;

    public ClinicAdministratorDTO() {
    }

    public ClinicAdministratorDTO(Long id, String email, String password, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public ClinicAdministratorDTO(ClinicAdministrator clinicAdministrator) {
        this(clinicAdministrator.getId(), clinicAdministrator.getEmail(), clinicAdministrator.getPassword(), clinicAdministrator.getFirstName(), clinicAdministrator.getLastName());
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

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
}
