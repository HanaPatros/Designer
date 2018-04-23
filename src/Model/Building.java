package Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
       "buildingId",
       "name",
       "street",
       "number",
       "zipCode",
       "city",
       "buildingCode",
       "svg"
})

@JsonIgnoreProperties(ignoreUnknown = true)
public class Building {

   @JsonProperty("buildingId")
   private Long BuildingId;

   @JsonProperty("name")
   private String Name;

   @JsonProperty("street")
   private String Street;

   @JsonProperty("number")
   private int Number;

   @JsonProperty("city")
   private String City;

   @JsonProperty("zipCode")
   private int ZipCode;

   @JsonProperty("buildingCode")
   private String BuildingCode;

   @JsonProperty("svg")
   private String SVG;

   @JsonManagedReference("building-floor")
   private List<Floor> floors;

   @JsonGetter("buildingId")
   public Long getBuildingId() {
       return BuildingId;
   }

   @JsonSetter("buildingId")
   public void setBuildingId(Long buildingId) {
       BuildingId = buildingId;
   }

   @JsonGetter("name")
   public String getName() {
       return Name;
   }

   @JsonSetter("name")
   public void setName(String name) {
       Name = name;
   }

   @JsonGetter("street")
   public String getStreet() {
       return Street;
   }

   @JsonSetter("street")
   public void setStreet(String street) {
       Street = street;
   }

   @JsonGetter("number")
   public int getNumber() {
       return Number;
   }

   @JsonSetter("number")
   public void setNumber(int number) {
       Number = number;
   }

   @JsonGetter("city")
   public String getCity() {
       return City;
   }

   @JsonSetter("city")
   public void setCity(String city) {
       City = city;
   }

   @JsonGetter("zipCode")
   public int getZipCode() {
       return ZipCode;
   }

   @JsonSetter("zipcode")
   public void setZipCode(int zipCode) {
       ZipCode = zipCode;
   }

   @JsonGetter("buildingCode")
   public String getBuildingCode() {
       return BuildingCode;
   }

   @JsonSetter("buildingCode")
   public void setBuildingCode(String buildingCode) {
       BuildingCode = buildingCode;
   }

   @JsonSetter("svg")
   public String getSVG() {
       return SVG;
   }

   @JsonGetter("svg")
   public void setSVG(String sVG) {
       SVG = sVG;
   }

   @JsonGetter
   public List<Floor> getFloors() {
       return floors;
   }

   @JsonSetter
   public void setFloors(List<Floor> floors) {
       this.floors = floors;
   }
}