package isapsw.tim43.ISCC.model;

import lombok.Data;

import java.util.Date;

@Data
public class VacationRequest {
	
	private long id;
	private String senderFirstName;
	private String senderLastName;
	private String senderRole;
	private String senderEmail;
	private Date startingDate;
	private Date endingDate;
	private int status; //0 - pending; 1 - approved; 2 - denied
	
	public VacationRequest(){}
	
	public VacationRequest(long id, String senderFirstName, String senderLastName, String senderRole, String senderEmail, Date startingDate,
			Date endingDate) {
		super();
		this.id = id;
		this.senderFirstName = senderFirstName;
		this.senderLastName = senderLastName;
		this.senderRole = senderRole;
		this.senderEmail = senderEmail;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.status = 0;
	}
	
	
}
