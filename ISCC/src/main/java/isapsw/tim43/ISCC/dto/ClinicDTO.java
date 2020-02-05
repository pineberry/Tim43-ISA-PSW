package isapsw.tim43.ISCC.dto;

import java.util.List;

import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.Doctor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ClinicDTO {

    private Long id;
    private String name;
    private String address;
    private String description;
    private double averageRating;
    Map<String, Integer> numberOfProcedures = new HashMap<String, Integer>();
    List<Doctor> doctors = new ArrayList<Doctor>();

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
}
