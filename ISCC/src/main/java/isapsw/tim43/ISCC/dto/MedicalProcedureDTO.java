package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.MedicalProcedure;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MedicalProcedureDTO {

    private long id;
    private ProcedureTypeDTO procedureType;
    private Date dateOfProcedure;
    private MedicalRoomDTO medicalRoom;
    private DoctorDTO doctor;
    private List<DoctorDTO> doctors = new ArrayList<>();
    private PatientDTO patient;
    private float price;
    private float discount;
    private boolean booked;
    private String startTime;
    private String endTime;

    public MedicalProcedureDTO(){}

    public MedicalProcedureDTO(long id, ProcedureTypeDTO procedureType, Date dateOfProcedure,
                               MedicalRoomDTO medicalRoom, DoctorDTO doctor, float discount, boolean booked) {
        this.id = id;
        this.procedureType = procedureType;
        this.dateOfProcedure = dateOfProcedure;
        this.medicalRoom = medicalRoom;
        this.doctor = doctor;
        this.discount = discount;
        this.booked = booked;
    }

    public MedicalProcedureDTO(MedicalProcedure medicalProcedure){
        id = medicalProcedure.getId();
        procedureType = new ProcedureTypeDTO(medicalProcedure.getProcedureType());
        dateOfProcedure = medicalProcedure.getDateOfProcedure();
        if (medicalProcedure.getMedicalRoom() != null)
            medicalRoom = new MedicalRoomDTO(medicalProcedure.getMedicalRoom());
        doctor = new DoctorDTO(medicalProcedure.getDoctor());
        if (medicalProcedure.getPatient() != null)
            patient = new PatientDTO(medicalProcedure.getPatient());
        discount = medicalProcedure.getDiscount();
        booked = medicalProcedure.isBooked();
        startTime = medicalProcedure.getStartTime();
        endTime = medicalProcedure.getEndTime();
    }

}
