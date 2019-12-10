package isapsw.tim43.ISCC.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Clinic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "description")
	private String description;

	@Column(name = "average_rating")
	private double averageRating;

	@OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ClinicAdministrator> administrators;
	
	@OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonBackReference(value="doctor-clinic")
	private List<Doctor> doctors;

	@OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Nurse> nurses;

	@OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Prescription> prescriptions;
	
	/*
	private List<Date> appointmentDates;
	private List<MedicalRoom> medicalRooms;
	private Map<Double, MedicalProcedure> pricelist;
	*/

	public Clinic() {
	}

	public Clinic(Long id, String name, String address, String description, double averageRating, List<Date> appointmentDates,
				  List<Doctor> doctors, List<MedicalRoom> medicalRooms, Map<Double, MedicalProcedure> pricelist,
				  List<ClinicAdministrator> administrators, List<Nurse> nurses, List<Prescription> prescriptions) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.averageRating = averageRating;
		this.doctors = doctors;
		this.administrators = administrators;
		this.nurses = nurses;
		this.prescriptions = prescriptions;
		/*this.appointmentDates = appointmentDates;
		this.medicalRooms = medicalRooms;
		this.pricelist = pricelist;
		*/
	}
}
