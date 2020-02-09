package isapsw.tim43.ISCC.constants;

import static isapsw.tim43.ISCC.constants.ClinicConstants.*;
import static isapsw.tim43.ISCC.constants.DoctorConstants.*;
import static isapsw.tim43.ISCC.constants.PatientConstants.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.model.MedicalRoom;
import isapsw.tim43.ISCC.model.Patient;
import isapsw.tim43.ISCC.model.ProcedureType;

public class MedicalProcedureConstants { 
	public static final Long MEDICAL_PROCEDURE_ID = 1L;
	public static final ProcedureType MEDICAL_PROCEDURE_TYPE = DOCTOR_SPECIALIZED;
	@SuppressWarnings("deprecation")
	public static final Date MEDICAL_PROCEDURE_DATE = new Date(120, 2, 15, 10, 0);
	public static final MedicalRoom MEDICAL_PROCEDURE_ROOM =  new MedicalRoom(1, "sobicak 1", 1, 
			new Clinic(CLINIC_ID, CLINIC_NAME, CLINIC_ADDRESS, CLINIC_DESCRIPTION,
					CLINIC_AVERAGE_RATING, new ArrayList<Date>(), CLINIC_DOCTORS,
					CLINIC_MEDICAL_ROOMS, new HashMap<Double, MedicalProcedure>(), 
					CLINIC_ADMINISTRATORS, CLINIC_NURSES, CLINIC_PERSCRIPTIONS));
	public static final Doctor MEDICAL_PROCEDURE_DOCTOR = new Doctor(DOCTOR_ID, DOCTOR_EMAIL, DOCTOR_PASSWORD, DOCTOR_FIRST_NAME, DOCTOR_LAST_NAME,
			DOCTOR_ADDRESS, DOCTOR_CITY, DOCTOR_STATE, DOCTOR_PHONE_NUMBER, DOCTOR_AVERAGE_RATING,
			DOCTOR_WORKING_TIME_START, DOCTOR_WORKING_TIME_END, DOCTOR_ON_VACATION, DOCTOR_SPECIALIZED,
			DOCTOR_PROCEDURES, DOCTOR_CLINIC);
	public static final Patient MEDICAL_PROCEDURE_PATIENT = new Patient(PATIENT_ID, PATIENT_EMAIL, PATIENT_PASSWORD, PATIENT_FIRST_NAME, PATIENT_LAST_NAME, 
			PATIENT_ADDRESS, PATIENT_CITY, PATIENT_STATE, PATIENT_PHONE_NUMBER, 
			PATIENT_HEALTH_CARE_NUMBER, PATIENT_STATUS, PATIENT_MEDICAL_RECORD);
	public static final float MEDICAL_PROCEDURE_DISCOUNT = 0;
	public static final String MEDICAL_PROCEDURE_START_TIME = "10:00";
	public static final String MEDICAL_PROCEDURE_END_TIME = "11:00";
	public static final boolean MEDICAL_PROCEDURE_BOOKED = false;
	public static final boolean MEDICAL_PROCEDURE_CLINIC_RATED = false;
	public static final boolean MEDICAL_PROCEDURE_DOCTOR_RATED = false;
}
