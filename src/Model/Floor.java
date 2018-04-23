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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
       "floorId",
       "name",
       "number",
       "floorCode",
       "svg",
       "building"
})


@JsonIgnoreProperties(ignoreUnknown = true)
public class Floor {

   @JsonProperty("floorId")
   private Long FloorId;

   @JsonProperty("name")
   private String Name;

   @JsonProperty("number")
   private int Number;

   @JsonProperty("floorCode")
   private String FloorCode;

   @JsonProperty("buildingId")
   private int buildingId;
           
   @JsonProperty("svg")
   private String SVG;

   @JsonManagedReference("floor-department")
   private List<Department> departments;

   @JsonBackReference("building-floor")
   @JsonProperty("building")
   private Building buildings;

   @JsonGetter("floorId")
   public Long getFloorId() {
       return FloorId;
   }

   @JsonSetter("floorId")
   public void setFloorId(Long floorId) {
       FloorId = floorId;
   }

   @JsonGetter("name")
   public String getName() {
       return Name;
   }

   @JsonSetter("name")
   public void setName(String name) {
       Name = name;
   }

   @JsonGetter("number")
   public int getNumber() {
       return Number;
   }

   @JsonSetter("number")
   public void setNumber(int number) {
       Number = number;
   }

   @JsonGetter("floorCode")
   public String getFloorCode() {
       return FloorCode;
   }

   @JsonSetter("floorCode")
   public void setFloorCode(String floorCode) {
       FloorCode = floorCode;
   }

   @JsonGetter("svg")
   public String getSVG() {
       return SVG;
   }

   @JsonSetter("svg")
   public void setSVG(String sVG) {
       SVG = sVG;
   }

   @JsonGetter
   public List<Department> getDepartments() {
       return departments;
   }

   @JsonSetter
   public void setDepartments(List<Department> departments) {
       this.departments = departments;
   }

   @JsonGetter("building")
   public Building getBuildings() {
       return buildings;
   }

   @JsonSetter("building")
   public void setBuildings(Building buildings) {
       this.buildings = buildings;
   }

   @JsonGetter("buildingId")
    public int getBuildingId() {
        return buildingId;
    }

    @JsonSetter("buildingId")
    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }
   

}
