package isapsw.tim43.ISCC.model;

import lombok.Data;

import java.util.Date;

@Data
public class VacationRequest {
	
	private long id;
	private String senderFirstName;
	private String senderLastName;
	private String senderRole;
	private Date startingDate;
	private Date endingDate;
	private boolean isApproved;
	
	
	
	public VacationRequest(long id, String senderFirstName, String senderLastName, String senderRole, Date startingDate,
			Date endingDate) {
		super();
		this.id = id;
		this.senderFirstName = senderFirstName;
		this.senderLastName = senderLastName;
		this.senderRole = senderRole;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
	}
	
	
}
