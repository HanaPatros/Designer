package Model;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "userId",
        "firstName",
        "name",
        "login",
        "email",
        "password",
        "phone",
        "startDate",
        "endDate",
        "administrator",
        "departments"
})


public class User {
	
	

	public User(Long userId, String firstName, String name, String login, String email, String password, String phone,
			Date startDate, Date endDate, BigInteger administrator, List<Absence> absence,
			List<Reservation> reservation, Department department) {
		super();
		UserId = userId;
		FirstName = firstName;
		Name = name;
		Login = login;
		Email = email;
		Password = password;
		Phone = phone;
		StartDate = startDate;
		EndDate = endDate;
		Administrator = administrator;
		this.absence = absence;
		this.reservation = reservation;
		this.department = this.department;
	}

	public User() {
		
	}
	
	@JsonProperty("userId")
	
	private Long UserId;
	
	@JsonProperty("firstName")
	
	private String FirstName;
	
	@JsonProperty("name")
	
	private String Name;
	
	@JsonProperty("login")
	
	private String Login;
	
	@JsonProperty("email")
	
	private String Email;
	
	@JsonProperty("password")
	
	private String Password;
	
	@JsonProperty("phone")
	
	private String Phone;
	
	@JsonProperty("startDate")
	@JsonFormat(pattern = "YYYY-MM-dd")
	
	private Date StartDate;
	
	@JsonProperty("endDate")
	@JsonFormat(pattern = "YYYY-MM-dd")
	
	private Date EndDate;
	
	@JsonProperty("administratie")
	
	private BigInteger Administrator;
	
	@JsonManagedReference(value="absence-user")
	
	private List<Absence> absence;
	
	@JsonManagedReference(value="reservation-user")
	
	private List<Reservation> reservation;
	
	@JsonProperty("department")
	@JsonBackReference(value="user-department")
	
	private Department department;

	@JsonGetter("userId")
	public Long getUserId() {
		return UserId;
	}
	
	@JsonSetter("userId")
	public void setUserId(Long userId) {
		UserId = userId;
	}
	
	@JsonGetter("firstName")
	public String getFirstName() {
		return FirstName;
	}
	
	@JsonSetter("firstName")
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	@JsonGetter("name")
	public String getName() {
		return Name;
	}
	
	@JsonSetter("name")
	public void setName(String name) {
		Name = name;
	}
	
	@JsonGetter("login")
	public String getLogin() {
		return Login;
	}
	
	@JsonSetter("login")
	public void setLogin(String login) {
		Login = login;
	}
	
	@JsonGetter("email")
	public String getEmail() {
		return Email;
	}
	
	@JsonSetter("email")
	public void setEmail(String email) {
		Email = email;
	}
	
	@JsonGetter("password")
	public String getPassword() {
		return Password;
	}
	
	@JsonSetter("password")
	public void setPassword(String password) {
		Password = password;
	}
	
	@JsonGetter("phone")
	public String getPhone() {
		return Phone;
	}
	
	@JsonSetter("phone")
	public void setPhone(String phone) {
		Phone = phone;
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
	
	@JsonGetter("administrator")
	public BigInteger getAdministrator() {
		return Administrator;
	}

	@JsonSetter("administrator")
	public void setAdministrator(BigInteger administrator) {
		Administrator = administrator;
	}

	@JsonGetter("absence")
	public List<Absence> getAbsence() {
		return absence;
	}

	@JsonSetter("absence")
	public void setAbsence(List<Absence> absence) {
		this.absence = absence;
	}
	
	@JsonGetter
	public List<Reservation> getReservation() {
		return reservation;
	}
	
	@JsonSetter
	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}
	
	@JsonGetter("department")
	public Department getDepartment() {
		return department;
	}
	
	@JsonSetter("department")
	public void setDepartment(Department department) {
		this.department = department;
	}

	
}
