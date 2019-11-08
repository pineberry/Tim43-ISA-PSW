package isapsw.tim43.ISCC.model;

import java.util.Date;

public class BookingRequest {
	
	private long id;
	private ProcedureType procedureType;
	private Date date;

	public BookingRequest(long id, ProcedureType procedureType, Date date) {
		super();
		this.id = id;
		this.procedureType = procedureType;
		this.date = date;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
