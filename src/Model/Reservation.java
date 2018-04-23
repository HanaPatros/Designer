package Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "reservationId",
        "startDate",
        "endDate",
        "statusDate",
        "requestor",
        "creator",
        "creatorDate",
        "description",
        "flexDesk",
        "user"
})


public class Reservation {
	
	public Reservation(Long reservationId, Date startDate, Date endDate, String status, Date statusDate, int requestor,
			int creator, Date creatorDate, String description, FlexDesk flexDesk, User user) {
		super();
		ReservationId = reservationId;
		StartDate = startDate;
		EndDate = endDate;
		Status = status;
		StatusDate = statusDate;
		Requestor = requestor;
		Creator = creator;
		CreatorDate = creatorDate;
		Description = description;
		this.flexDesk = flexDesk;
		this.user = user;
	}

	public Reservation() {
		
	}
	
	@JsonProperty("reservationId")
	
	private Long ReservationId;
	
	@JsonProperty("startDate")
	@JsonFormat(pattern = "YYYY-MM-dd")
	
	private Date StartDate;
	
	@JsonProperty("endDate")
	@JsonFormat(pattern = "YYYY-MM-dd")
	
	private Date EndDate;
	
	@JsonProperty("status")
	
	private String Status;
	
	@JsonProperty("statusDate")
	@JsonFormat(pattern = "YYYY-MM-dd")
	
	private Date StatusDate;
	
	@JsonProperty("requestor")
	
	private int Requestor;
	
	@JsonProperty("creator")
	
	private int Creator;
	
	@JsonProperty("creatorDate")
	@JsonFormat(pattern = "YYYY-MM-dd")
	
	private Date CreatorDate;
	
	@JsonProperty("description")
	
	private String Description;

	@JsonProperty("flexDesk")
	@JsonBackReference(value="reservation-flexdesk")
	
	private FlexDesk flexDesk;
	
	@JsonProperty("user")
	@JsonBackReference(value="reservation-user")
	
	private User user;


	public Long getReservationId() {
		return ReservationId;
	}

	public void setReservationId(Long reservationId) {
		ReservationId = reservationId;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Date getStatusDate() {
		return StatusDate;
	}

	public void setStatusDate(Date statusDate) {
		StatusDate = statusDate;
	}

	public int getRequestor() {
		return Requestor;
	}

	public void setRequestor(int requestor) {
		Requestor = requestor;
	}

	public int getCreator() {
		return Creator;
	}

	public void setCreator(int creator) {
		Creator = creator;
	}

	public Date getCreatorDate() {
		return CreatorDate;
	}

	public void setCreatorDate(Date creatorDate) {
		CreatorDate = creatorDate;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public FlexDesk getFlexDesk() {
		return flexDesk;
	}

	public void setFlexDesk(FlexDesk flexDesk) {
		this.flexDesk = flexDesk;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
