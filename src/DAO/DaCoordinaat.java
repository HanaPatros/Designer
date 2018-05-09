/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Coordinaat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sa59053
 */
public class DaCoordinaat {
    Coordinaat coordinaat;
    List<Coordinaat> coordinaten;
    DaBase db = new DaBase();
        Connection conn = db.getConnection();
        Statement stmt;
    public void DaCoordinaat(){
        
        
    }    
    public ArrayList<Coordinaat> getAllCoordinates() {
        try {
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
coordinaten = new ArrayList();
coordinaat = new Coordinaat();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COORDINAAT ");
            while (rs.next()) {
                coordinaat.setId(rs.getLong("Id"));
                coordinaat.setDeskId(rs.getString("DeskId"));
                coordinaat.setX1(rs.getInt("X"));
                coordinaat.setY1(rs.getInt("Y"));
                coordinaat.setWidth1(rs.getInt("Width"));
                coordinaat.setHeight1(rs.getInt("Height"));
                coordinaten.add(coordinaat);
                
            }
        

            conn.commit();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(DaCoordinaat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<Coordinaat>) coordinaten;    
    }    
}
