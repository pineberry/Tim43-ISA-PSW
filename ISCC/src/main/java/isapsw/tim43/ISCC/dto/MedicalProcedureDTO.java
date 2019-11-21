package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.Doctor;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.model.MedicalRoom;
import isapsw.tim43.ISCC.model.ProcedureType;

import java.util.Date;

public class MedicalProcedureDTO {

    private long id;
    private ProcedureType procedureType;
    private Date dateOfProcedure;
    private MedicalRoom medicalRoom;
    private Doctor doctor;
    private float price;
    private float discount;
    private boolean booked;

    public MedicalProcedureDTO(){}

    public MedicalProcedureDTO(long id, ProcedureType procedureType, Date dateOfProcedure,
                               MedicalRoom medicalRoom, Doctor doctor, float price, float discount, boolean booked) {
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
        this(medicalProcedure.getId(), medicalProcedure.getProcedureType(), medicalProcedure.getDateOfProcedure(),
                medicalProcedure.getMedicalRoom(), medicalProcedure.getDoctor(), medicalProcedure.getPrice(),
                medicalProcedure.getDiscount(), medicalProcedure.isBooked());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProcedureType getProcedureType() {
        return procedureType;
    }

    public void setProcedureType(ProcedureType procedureType) {
        this.procedureType = procedureType;
    }

    public Date getDateOfProcedure() {
        return dateOfProcedure;
    }

    public void setDateOfProcedure(Date dateOfProcedure) {
        this.dateOfProcedure = dateOfProcedure;
    }

    public MedicalRoom getMedicalRoom() {
        return medicalRoom;
    }

    public void setMedicalRoom(MedicalRoom medicalRoom) {
        this.medicalRoom = medicalRoom;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDicount() {
        return discount;
    }

    public void setDicount(float dicount) {
        this.discount = dicount;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
