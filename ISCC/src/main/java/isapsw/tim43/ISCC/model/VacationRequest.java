package isapsw.tim43.ISCC.model;

import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class VacationRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Doctor doctor;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Nurse nurse;

	@Column(name = "starting_date")
	private Date startingDate;

	@Column(name = "ending_date")
	private Date endingDate;

	@Column(name = "status")
	private int status; //0 - pending; 1 - approved; 2 - denied
	
	public VacationRequest(){}
	
	public VacationRequest(Long id, Doctor doctor, Nurse nurse, Date startingDate, Date endingDate) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.nurse = nurse;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.status = 0;
	}
	
	
}
