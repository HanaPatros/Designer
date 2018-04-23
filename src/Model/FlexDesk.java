package Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "flexDeskId",
        "name",
        "flexDeskCode",
        "x",
        "y",
        "department"
})


public class FlexDesk {

	public FlexDesk(Long flexDeskId, String name, String flexDeskCode, int x, int y, List<Reservation> reservation,
			Department departments) {
		super();
		FlexDeskId = flexDeskId;
		Name = name;
		FlexDeskCode = flexDeskCode;
		X = x;
		Y = y;
		this.reservation = reservation;
		this.department = departments;
	}

	public FlexDesk() {
		
	}
	
	@JsonProperty("flexDeskId")
	
	private Long FlexDeskId;
	
	@JsonProperty("name")
	
	private String Name;
	
	@JsonProperty("flexDeskCode")
	
	private String FlexDeskCode;
	
	@JsonProperty("x")
	
	private int X;
	
	@JsonProperty("y")
	
	private int Y;
	
	@JsonManagedReference(value="reservation-flexdesk")
	
	private List<Reservation> reservation;
	
	@JsonProperty("department")
	@JsonBackReference(value="flexdesk-department")
	
	@JoinColumn(name = "DepartmentId")
	private Department department;
	
	@JsonGetter("flexDeskId")
	public Long getFlexDeskId() {
		return FlexDeskId;
	}
	
	@JsonSetter("flexDeskId")
	public void setFlexDeskId(Long flexDeskId) {
		FlexDeskId = flexDeskId;
	}
	
	@JsonGetter("name")
	public String getName() {
		return Name;
	}
	
	@JsonSetter("name")
	public void setName(String name) {
		Name = name;
	}
	
	@JsonGetter("flexDeskCode")
	public String getFlexDeskCode() {
		return FlexDeskCode;
	}
	
	@JsonSetter("flexDeskCode")
	public void setFlexDeskCode(String flexDeskCode) {
		FlexDeskCode = flexDeskCode;
	}
	
	@JsonGetter("x")
	public int getX() {
		return X;
	}
	
	@JsonSetter("x")
	public void setX(int x) {
		X = x;
	}
	
	@JsonGetter("y")
	public int getY() {
		return Y;
	}
	
	@JsonSetter("y")
	public void setY(int y) {
		Y = y;
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
