package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaBase {

    public Connection getConnection() {
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
