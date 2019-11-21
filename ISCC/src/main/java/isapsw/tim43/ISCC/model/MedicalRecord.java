package isapsw.tim43.ISCC.model;

import java.util.List;


public class MedicalRecord {

	private long id;
	private List<MedicalProcedure> medicalExamination;
	private List<MedicalProcedure> surgeries;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public List<MedicalProcedure> getMedicalExamination() {
		return medicalExamination;
	}
	
	public void setMedicalExamination(List<MedicalProcedure> medicalExamination) {
		this.medicalExamination = medicalExamination;
	}
	
	public List<MedicalProcedure> getSurgeries() {
		return surgeries;
	}
	
	public void setSurgeries(List<MedicalProcedure> surgeries) {
		this.surgeries = surgeries;
	}

	public MedicalRecord(long id, List<MedicalProcedure> medicalExamination,
			List<MedicalProcedure> surgeries) {
		super();
		this.id = id;
		this.medicalExamination = medicalExamination;
		this.surgeries = surgeries;
	}

	public MedicalRecord() {
		super();
	}
}
