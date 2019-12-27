package isapsw.tim43.ISCC.dto;

import java.util.List;

import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.Doctor;
import lombok.Data;

@Data
public class ClinicDTO {

    private Long id;
    private String name;
    private String address;
    private String description;
    private double averageRating;
    private List<Doctor> doctors;

    public ClinicDTO() {
    }

    public ClinicDTO(Long id, String name, String address, String description, double averageRating, List<Doctor> doctors) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.averageRating = averageRating;
        this.doctors = doctors;
    }

    public ClinicDTO(Clinic clinic){
        this(clinic.getId(), clinic.getName(), clinic.getAddress(), clinic.getDescription(), clinic.getAverageRating(), clinic.getDoctors());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
