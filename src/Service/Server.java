/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import UI.JFrameGebouwen;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.derby.drda.NetworkServerControl;

/**
 *
 * @author sa59053
 */
public class Server {

    public static void main(String[] args) {
        NetworkServerControl server = null;
        Connection conn = null;
        PreparedStatement prestat = null;
        Statement stmt = null;
        ResultSet result = null;

        try {
            server = new NetworkServerControl(InetAddress.getByName("localhost"), 1527);
            server.start(null);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Coördinaten;create=true");

            stmt = conn.createStatement();

            JFrameGebouwen gebouwen = new JFrameGebouwen();
            gebouwen.JInputWH.setVisible(true);
            //gebouwen.setVisible(true);
            prestat = conn.prepareStatement("SELECT * FROM Coördinaat");
            result = prestat.executeQuery();

            StringBuilder builder = new StringBuilder();
            while (result.next()) {
                builder.append(result.getInt(1) + ", " + result.getString(2));
                builder.append('\n');
            }

            JOptionPane.showMessageDialog(null, builder.toString());

            result.close();
            result = null;
            prestat.close();
            prestat = null;
            conn.close();
            conn = null;

            server.shutdown();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {;
                }
                result = null;
            }
            if (prestat != null) {
                try {
                    prestat.close();
                } catch (SQLException e) {;
                }
                prestat = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {;
                }
                conn = null;
            }
        }

    }

}
