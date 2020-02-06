package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.VacationRequest;
import lombok.Data;

import java.util.Date;

@Data
public class VacationRequestDTO {

    private long id;
    private DoctorDTO doctorDTO;
    private NurseDTO nurseDTO;
    private Date startingDate;
    private Date endingDate;
    private int status;

    public VacationRequestDTO(){}

    public VacationRequestDTO(long id,DoctorDTO doctorDTO, NurseDTO nurseDTO, Date startingDate, Date endingDate, int status) {
        this.id = id;
        this.doctorDTO = doctorDTO;
        this.nurseDTO = nurseDTO;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.status = status;
    }

    public VacationRequestDTO(VacationRequest vacationRequest) {
        this.id = vacationRequest.getId();
        this.startingDate = vacationRequest.getStartingDate();
        this.endingDate = vacationRequest.getEndingDate();
        this.status = vacationRequest.getStatus();
    }
}
