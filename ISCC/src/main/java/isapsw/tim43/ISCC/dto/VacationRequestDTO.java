package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.VacationRequest;

import java.util.Date;

public class VacationRequestDTO {

    private long id;
    private String senderFirstName;
    private String senderLastName;
    private String senderRole;
    private String senderEmail;
    private Date startingDate;
    private Date endingDate;
    private int status;

    public VacationRequestDTO(long id, String senderFirstName, String senderLastName, String senderRole,
                                    String senderEmail, Date startingDate, Date endingDate, int status) {
        this.id = id;
        this.senderFirstName = senderFirstName;
        this.senderLastName = senderLastName;
        this.senderRole = senderRole;
        this.senderEmail = senderEmail;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.status = status;
    }

    public VacationRequestDTO(VacationRequest vacationRequest) {
        this.id = vacationRequest.getId();
        this.senderFirstName = vacationRequest.getSenderFirstName();
        this.senderLastName = vacationRequest.getSenderLastName();
        this.senderRole = vacationRequest.getSenderRole();
        this.senderEmail = vacationRequest.getSenderEmail();
        this.startingDate = vacationRequest.getStartingDate();
        this.endingDate = vacationRequest.getEndingDate();
        this.status = vacationRequest.getStatus();
    }
}
