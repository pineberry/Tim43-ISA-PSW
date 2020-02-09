package isapsw.tim43.ISCC.constants;

import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_ADDRESS;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_ADMINISTRATORS;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_AVERAGE_RATING;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_DESCRIPTION;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_DOCTORS;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_ID;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_MEDICAL_ROOMS;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_NAME;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_NURSES;
import static isapsw.tim43.ISCC.constants.ClinicConstants.CLINIC_PERSCRIPTIONS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.MedicalProcedure;

public class MedicalRoomConstants {
	
	public static final Long ROOM_ID = 2L;
	public static final String ROOM_NAME = "Room";
	public static final int ROOM_NUMBER = 1;
	public static final List<MedicalProcedure> ROOM_PROCEDURES = new ArrayList<MedicalProcedure>();
	public static final Clinic ROOM_CLINIC = new Clinic
			(CLINIC_ID, CLINIC_NAME, CLINIC_ADDRESS, CLINIC_DESCRIPTION,
					CLINIC_AVERAGE_RATING, new ArrayList<Date>(), CLINIC_DOCTORS,
					CLINIC_MEDICAL_ROOMS, new HashMap<Double, MedicalProcedure>(), 
					CLINIC_ADMINISTRATORS, CLINIC_NURSES, CLINIC_PERSCRIPTIONS);
}
