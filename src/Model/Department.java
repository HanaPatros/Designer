package Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "departmentId",
        "name",
        "departmentCode",
        "svg",
        "floor"
})

@JsonIgnoreProperties(ignoreUnknown = true)
public class Department {



	public Department(Long departmentId, String name, String departmentCode, String svg, List<FlexDesk> flexdesk,
			List<User> user, Floor floor) {
		super();
		DepartmentId = departmentId;
		Name = name;
		DepartmentCode = departmentCode;
		Svg = svg;
		this.flexdesk = flexdesk;
		this.user = user;
		this.floor = floor;
	}

	public Department(){
		
	}

	@JsonProperty("departmentId")
	
	private Long DepartmentId;
	
	@JsonProperty("name")
	
	private String Name;
	
	@JsonProperty("departmentCode")
	
	private String DepartmentCode;
	
	@JsonProperty("svg")
	
	private String Svg;
        
        @JsonProperty("floorId")
        private Long floorId;
	
	@JsonManagedReference(value="flexdesk-department")
	
	private List<FlexDesk> flexdesk;
	
	@JsonManagedReference(value="user-department")
	
	private List<User> user;
	
	@JsonProperty("floor")
	@JsonBackReference(value="floor-department")
	
	@JoinColumn(name = "FloorId")
	private Floor floor;
	
	@JsonGetter("departmentId")
	public Long getDepartmentId() {
		return DepartmentId;
	}
	
	@JsonSetter("departmentId")
	public void setDepartmentId(Long departmentId) {
		DepartmentId = departmentId;
	}
	
	@JsonGetter("name")
	public String getName() {
		return Name;
	}
	
	@JsonSetter("name")
	public void setName(String name) {
		Name = name;
	}
	
	@JsonGetter("departmentCode")
	public String getDepartmentCode() {
		return DepartmentCode;
	}
	
	@JsonSetter("departmentCode")
	public void setDepartmentCode(String departmentCode) {
		DepartmentCode = departmentCode;
	}
	
	@JsonGetter("svg")
	public String getSvg() {
		return Svg;
	}
	
	@JsonSetter("svg")
	public void setSvg(String svg) {
		Svg = svg;
	}
	
	@JsonGetter
	public List<FlexDesk> getFlexdesk() {
		return flexdesk;
	}
	
	@JsonSetter
	public void setFlexdesks(List<FlexDesk> flexdesk) {
		this.flexdesk = flexdesk;
	}

	@JsonGetter
	public List<User> getUsers() {
		return user;
	}
	
	@JsonSetter
	public void setUsers(List<User> user) {
		this.user = user;
	}

	@JsonGetter("floor")
	public Floor getFloor() {
		return floor;
	}
	
	@JsonSetter("floor")
	public void setFloor(Floor floor) {
		this.floor = floor;
	}

        @JsonGetter("floorId")    
        public Long getFloorId() {
            return floorId;
        }

        @JsonSetter("floorId")
        public void setFloorId(Long floorId) {
            this.floorId = floorId;
        }

	
}
