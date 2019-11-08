package isapsw.tim43.ISCC.model;


import java.util.Date;
import java.util.List;
import java.util.Map;

public class Clinic {
	
	private String name;
	private String address;
	private String description;
	private double averageRating;
	
	private List<Date> appointmentDates;
	private List<Doctor> doctors;
	private List<MedicalRoom> medicalRooms;
	private Map<Double, MedicalProcedure> pricelist;
	private List<ClinicAdministrator> administrators;
	
	public Clinic(String name, String address, String description, double averageRating, List<Date> appointmentDates,
			List<Doctor> doctors, List<MedicalRoom> medicalRooms, Map<Double, MedicalProcedure> pricelist, List<ClinicAdministrator> administrators) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.averageRating = averageRating;
		this.appointmentDates = appointmentDates;
		this.doctors = doctors;
		this.medicalRooms = medicalRooms;
		this.pricelist = pricelist;
		this.administrators = administrators;
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
	public List<Date> getAppointmentDates() {
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
	}
	
	
	
	
	
}
