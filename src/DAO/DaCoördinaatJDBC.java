/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Coördinaat;
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
public class DaCoördinaatJDBC {

    Coördinaat coördinaat;
    List<Coördinaat> coördinaten;
    DaBase db = new DaBase();
    Connection conn = db.getConnection();
    Statement stmt;

    public void DaCoordinaat() {

    }

    public ArrayList<Coördinaat> getAllCoordinates() {
        try {
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
            coördinaten = new ArrayList();
            coördinaat = new Coördinaat();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COÖRDINAAT ");
            while (rs.next()) {
                coördinaat.setId(rs.getLong("Id"));
                coördinaat.setDeskId(rs.getString("DeskId"));
                coördinaat.setX1(rs.getInt("X"));
                coördinaat.setY1(rs.getInt("Y"));
                coördinaat.setWidth1(rs.getInt("Width"));
                coördinaat.setHeight1(rs.getInt("Height"));
                coördinaten.add(coördinaat);

            }

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(DaCoördinaatJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<Coördinaat>) coördinaten;
    }

}
