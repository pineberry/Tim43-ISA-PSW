package isapsw.tim43.ISCC.model;

import java.util.Date;

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
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSenderFirstName() {
		return senderFirstName;
	}
	public void setSenderFirstName(String senderFirstName) {
		this.senderFirstName = senderFirstName;
	}
	public String getSenderLastName() {
		return senderLastName;
	}
	public void setSenderLastName(String senderLastName) {
		this.senderLastName = senderLastName;
	}
	public String getSenderRole() {
		return senderRole;
	}
	public void setSenderRole(String senderRole) {
		this.senderRole = senderRole;
	}
	public Date getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	public Date getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	
}
