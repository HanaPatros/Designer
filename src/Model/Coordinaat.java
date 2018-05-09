package Model;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sa59053
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
       "Id",
       "x",
       "y",
       "width",
       "height"
})
@Entity
public class Coordinaat  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("Id")
    Long id;
    @JsonProperty("deskId")
    public String deskId;
    @JsonProperty("x")
    int x;
    @JsonProperty("y")
    int y;
    @JsonProperty("width")
    int width;
    @JsonProperty("height")
    int height;
   
    
    
    @JsonProperty("x")
    public int getX1() {
        return x;
    }

    @JsonProperty("x")
    public void setX1(int x) {
        this.x = x;
    }

    @JsonProperty("y")
    public int getY1() {
        return y;
    }

    @JsonProperty("y")
    public void setY1(int y) {
        this.y = y;
    }

    @JsonProperty("Width")
    public int getWidth1() {
        return width;
    }

    @JsonProperty("Width")
    public void setWidth1(int width) {
        this.width = width;
    }

    @JsonProperty("Height")
    public int getHeight1() {
        return height;
    }

    @JsonProperty("Height")
    public void setHeight1(int height) {
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public Coordinaat() {
    }
    

    public Coordinaat(int x, int y, int width, int height, String deskId) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.deskId = deskId;
    }

    
    
    public String toString(){
     return   "<svg width='1500'"+" height='1500' >"+ 
                            "< rect width='"+getWidth1()+"'"+" height='"+ getHeight1() + " x='"+getX1()+"'" + "y='"+ getY1()+"'" + "/>"+
                             "</svg>";
    }

    public String getDeskId() {
        return deskId;
    }

    public void setDeskId(String deskId) {
        this.deskId = deskId;
    }

   
    
    
}
