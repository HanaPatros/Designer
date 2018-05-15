/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sa59053
 */
public class DaBase {
    public Connection getConnection(){
       
        Connection conn = null;
        try {
            
             String connString = "jdbc:derby://localhost:1527/Coördinaten";
            conn = DriverManager.getConnection(connString, "root", "usbw");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
        
    }
}
