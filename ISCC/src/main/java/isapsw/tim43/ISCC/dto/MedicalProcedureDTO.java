package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.MedicalProcedure;
import lombok.Data;

import java.util.Date;

@Data
public class MedicalProcedureDTO {

    private long id;
    private ProcedureTypeDTO procedureType;
    private Date dateOfProcedure;
    private MedicalRoomDTO medicalRoom;
    private DoctorDTO doctor;
    private float price;
    private float discount;
    private boolean booked;
    private String startTime;
    private String endTime;

    public MedicalProcedureDTO(){}

    public MedicalProcedureDTO(long id, ProcedureTypeDTO procedureType, Date dateOfProcedure,
                               MedicalRoomDTO medicalRoom, DoctorDTO doctor, float price, float discount, boolean booked) {
        this.id = id;
        this.procedureType = procedureType;
        this.dateOfProcedure = dateOfProcedure;
        this.medicalRoom = medicalRoom;
        this.doctor = doctor;
        this.price = price;
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
        price = medicalProcedure.getPrice();
        discount = medicalProcedure.getDiscount();
        booked = medicalProcedure.isBooked();
        startTime = medicalProcedure.getStartTime();
        endTime = medicalProcedure.getEndTime();
    }

}
