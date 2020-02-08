package isapsw.tim43.ISCC.constants;

import java.util.ArrayList;
import java.util.List;

import isapsw.tim43.ISCC.model.ClinicAdministrator;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalRoom;
import isapsw.tim43.ISCC.model.Nurse;
import isapsw.tim43.ISCC.model.Prescription;
import isapsw.tim43.ISCC.model.ProcedureType;

public class ClinicConstants {
	public static final Long CLINIC_ID = 1L;
	public static final String CLINIC_NAME = "Test klinika";
	public static final String CLINIC_ADDRESS = "Ulica Uliceva 1";
	public static final String CLINIC_DESCRIPTION = "Opis klinike";
	public static final double CLINIC_AVERAGE_RATING = 3.0;
	public static final List<ClinicAdministrator> CLINIC_ADMINISTRATORS = new ArrayList<ClinicAdministrator>();
	public static final List<Doctor> CLINIC_DOCTORS = new ArrayList<Doctor>();
	public static final List<Nurse> CLINIC_NURSES = new ArrayList<Nurse>();
	public static final List<Prescription> CLINIC_PERSCRIPTIONS = new ArrayList<Prescription>();
	public static final List<MedicalRoom> CLINIC_MEDICAL_ROOMS = new ArrayList<MedicalRoom>();
	public static final List<ProcedureType> CLINIC_PROCEDURE_TYPES = new ArrayList<ProcedureType>();
	
}
