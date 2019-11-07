package isapsw.tim43.ISCC.model;

import java.util.List;

public class ClinicCenter {
	
	private long id;
	private List<Clinic> clinics;
	private List<Diagnosis> diagnosis;
	private List<Medicine> medicines;
	private List<ClinicCenterAdministrator> administrators;
	
	public ClinicCenter(long id, List<Clinic> clinics, List<Diagnosis> diagnosis, List<Medicine> medicines,
			List<ClinicCenterAdministrator> administrators) {
		super();
		this.id = id;
		this.clinics = clinics;
		this.diagnosis = diagnosis;
		this.medicines = medicines;
		this.administrators = administrators;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Clinic> getClinics() {
		return clinics;
	}

	public void setClinics(List<Clinic> clinics) {
		this.clinics = clinics;
	}

	public List<Diagnosis> getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(List<Diagnosis> diagnosis) {
		this.diagnosis = diagnosis;
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public List<ClinicCenterAdministrator> getAdministrators() {
		return administrators;
	}

	public void setAdministrators(List<ClinicCenterAdministrator> administrators) {
		this.administrators = administrators;
	}
	
	
}
