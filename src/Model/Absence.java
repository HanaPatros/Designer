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
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "abcenseId",
        "startDate",
        "endDate",
        "creator",
        "creationDate",
        "status",
        "statusDate",
        "description",
        "users"
})


public class Absence {
	
	public Absence() {
		
	}
	
	public Absence(int absenceId, Date startDate, Date endDate, int creator, Date creationDate, int status,
			Date statusDate, String description, User user) {
		super();
		AbsenceId = absenceId;
		StartDate = startDate;
		EndDate = endDate;
		Creator = creator;
		CreationDate = creationDate;
		Status = status;
		StatusDate = statusDate;
		Description = description;
		this.user = user;
	}
	
	@JsonProperty("absenceId")
	
	@Column(name = "AbsenceId")
	private int AbsenceId;
	
	@JsonProperty("startDate")
	
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date StartDate;
	
	@JsonProperty("endDate")
	
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date EndDate;
	
	@JsonProperty("creator")
	
	private int Creator;
	
	@JsonProperty("creationDate")
	
	private Date CreationDate;
	
	
	@JsonProperty("status")
	
	private int Status;
	
	@JsonProperty("statusDate")
	@JsonFormat(pattern = "YYYY-MM-dd")
	
	private Date StatusDate;
	
	@JsonProperty("description")
	
	private String Description;
	
	@JsonProperty("user")
	@JsonBackReference(value="absence-user")
	
	private User user;
	
	@JsonGetter("absenceId")
	public int getAbsenceId() {
		return AbsenceId;
	}
	
	@JsonSetter("absenceId")
	public void setAbsenceId(int absenceId) {
		AbsenceId = absenceId;
	}
	
	@JsonGetter("startDate")
	public Date getStartDate() {
		return StartDate;
	}
	
	@JsonSetter("startDate")
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	
	@JsonGetter("endDate")
	public Date getEndDate() {
		return EndDate;
	}
	
	@JsonSetter("endDate")
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	
	@JsonGetter("creator")
	public int getCreator() {
		return Creator;
	}
	
	@JsonSetter("creator")
	public void setCreator(int creator) {
		Creator = creator;
	}
	
	@JsonGetter("creationDate")
	public Date getCreationDate() {
		return CreationDate;
	}
	
	@JsonSetter("creationDate")
	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}
	
	@JsonGetter("status")
	public int getStatus() {
		return Status;
	}
	
	@JsonSetter("status")
	public void setStatus(int status) {
		Status = status;
	}
	
	@JsonGetter("statusDate")
	public Date getStatusDate() {
		return StatusDate;
	}
	
	@JsonSetter("statusDate")
	public void setStatusDate(Date statusDate) {
		StatusDate = statusDate;
	}
	
	@JsonGetter("description")
	public String getDescription() {
		return Description;
	}
	
	@JsonSetter("description")
	public void setDescription(String description) {
		Description = description;
	}
	
	@JsonGetter("user")
	public User getUser() {
		return user;
	}
	
	@JsonSetter("user")
	public void setUser(User user) {
		this.user = user;
	}
}
