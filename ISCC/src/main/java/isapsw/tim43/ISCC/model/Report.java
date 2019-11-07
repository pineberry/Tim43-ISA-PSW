package isapsw.tim43.ISCC.model;

public class Report {
	
	private long id;
	private Diagnosis diagnosis;
	private Prescription prescription;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public Report(long id, Diagnosis diagnosis, Prescription prescription) {
		super();
		this.id = id;
		this.diagnosis = diagnosis;
		this.prescription = prescription;
	}

	public Report() {
		super();
	}	
}
