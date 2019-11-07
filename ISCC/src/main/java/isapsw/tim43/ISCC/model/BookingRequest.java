package isapsw.tim43.ISCC.model;

import java.util.Date;

public class BookingRequest {
	
	private long id;
	private String type;
	private Date date;
	
	public BookingRequest(long id, String type, Date date) {
		super();
		this.id = id;
		this.type = type;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
