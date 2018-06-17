/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Building;
import Model.Department;
import Model.Floor;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author sa59053
 */
public class JsonService {

    private ObjectMapper mapper = new ObjectMapper();
    private List<Building> gebouwen;
    private List<Floor> verdiepingen;
    private List<Department> departments;
    private Building gebouw;
    private Department department;

    public Floor getFloorById(Long floorId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectMapper mapper1 = new ObjectMapper();
        Floor verdiep = new Floor();
        try {
            verdiep = objectMapper.readValue(new URL("http://flexdeskapplication.azurewebsites.net/api/Floor/"
                    + floorId.toString()), Floor.class);

        } catch (IOException e) {
            // TODO Auto-generated catch block

        }
        return verdiep;
    }

    public List<Building> getAllBuildings() throws IOException {
        gebouwen = new ArrayList();

        try {
            gebouwen = mapper.readValue(new URL("http://flexdeskapplication.azurewebsites.net/api/Building"),
                    mapper.getTypeFactory().constructCollectionType(List.class, Building.class));

        } catch (IOException e) {
            // TODO Auto-generated catch block

        }
        return (List<Building>) gebouwen;

    }

    public List<Floor> getAllFloors() throws IOException {
        verdiepingen = new ArrayList();
        try {

            verdiepingen = mapper.readValue(new URL("http://flexdeskapplication.azurewebsites.net/api/Floor/"),
                    mapper.getTypeFactory().constructCollectionType(List.class, Floor.class));

        } catch (MalformedURLException ex) {
        }
        return (List<Floor>) verdiepingen;
    }

    public List<Department> getAllDepartments() throws IOException {
        departments = new ArrayList();

        try {
            departments = mapper.readValue(new URL("http://flexdeskapplication.azurewebsites.net/api/Department"), mapper.getTypeFactory().constructCollectionType(List.class, Department.class));
        } catch (MalformedURLException ex) {
        }
        return (List<Department>) departments;
    }

    public void addFloorSVG(Floor verdieping) {

        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringEmp = new StringWriter();

        try {

            objectMapper.writeValue(stringEmp, verdieping);

            String link = "http://flexdeskapplication.azurewebsites.net/api/Floor/" + verdieping.getFloorId().toString();
            URL url = new URL(link);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestMethod("PUT");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            os.write(stringEmp.toString().getBytes("UTF-8"));
            int test = con.getResponseCode();
            System.out.println("" + test);

        } catch (IOException e) {
            e.fillInStackTrace();
        }

    }

}
