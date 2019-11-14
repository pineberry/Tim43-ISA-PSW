package isapsw.tim43.ISCC.model;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	/*
	private List<Date> appointmentDates;
	private List<Doctor> doctors;
	private List<MedicalRoom> medicalRooms;
	private Map<Double, MedicalProcedure> pricelist;
	*/

	public Clinic() {
	}

	public Clinic(Long id, String name, String address, String description, double averageRating, List<Date> appointmentDates,
				  List<Doctor> doctors, List<MedicalRoom> medicalRooms, Map<Double, MedicalProcedure> pricelist, List<ClinicAdministrator> administrators) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.averageRating = averageRating;
		/*this.appointmentDates = appointmentDates;
		this.doctors = doctors;
		this.medicalRooms = medicalRooms;
		this.pricelist = pricelist;
		this.administrators = administrators;*/
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
	/*public List<Date> getAppointmentDates() {
		return appointmentDates;
	}
	public void setAppointmentDates(List<Date> appointmentDates) {
		this.appointmentDates = appointmentDates;
	}
	public List<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	public List<MedicalRoom> getMedicalRooms() {
		return medicalRooms;
	}
	public void setMedicalRooms(List<MedicalRoom> medicalRooms) {
		this.medicalRooms = medicalRooms;
	}
	public Map<Double, MedicalProcedure> getPricelist() {
		return pricelist;
	}
	public void setPricelist(Map<Double, MedicalProcedure> pricelist) {
		this.pricelist = pricelist;
	}
	public List<ClinicAdministrator> getAdministrators() {
		return administrators;
	}
	public void setAdministrators(List<ClinicAdministrator> administrators) {
		this.administrators = administrators;
	}*/
	
	
	
	
	
}
