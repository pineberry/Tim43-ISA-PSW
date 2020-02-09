package isapsw.tim43.ISCC.constants;

import static isapsw.tim43.ISCC.constants.ClinicConstants.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.model.ProcedureType;

public class DoctorConstants {

	public static final Long DOCTOR_ID = 1L;
	public static final String DOCTOR_EMAIL = "doctor@mail.com";
	public static final String DOCTOR_PASSWORD = "password";
	public static final String DOCTOR_FIRST_NAME = "Marko";
	public static final String DOCTOR_LAST_NAME = "Markovic";
	public static final String DOCTOR_ADDRESS = "Ulica Uliceva 1";
	public static final String DOCTOR_CITY = "Gradograd";
	public static final String DOCTOR_STATE = "Drznana";
	public static final String DOCTOR_PHONE_NUMBER = "123456789";
	public static final double DOCTOR_AVERAGE_RATING = 3.0;
	public static final String DOCTOR_WORKING_TIME_START = "09:00";
	public static final String DOCTOR_WORKING_TIME_END = "16:00";
	public static final boolean DOCTOR_ON_VACATION = false;
	public static final Clinic DOCTOR_CLINIC = new Clinic
			(CLINIC_ID, CLINIC_NAME, CLINIC_ADDRESS, CLINIC_DESCRIPTION,
					CLINIC_AVERAGE_RATING, new ArrayList<Date>(), CLINIC_DOCTORS,
					CLINIC_MEDICAL_ROOMS, new HashMap<Double, MedicalProcedure>(), 
					CLINIC_ADMINISTRATORS, CLINIC_NURSES, CLINIC_PERSCRIPTIONS);
	public static final ProcedureType DOCTOR_SPECIALIZED = new ProcedureType(1, "Tip procedure", "Opis procedure", 100);
	public static final ArrayList<MedicalProcedure> DOCTOR_PROCEDURES = new ArrayList<MedicalProcedure>();
}
