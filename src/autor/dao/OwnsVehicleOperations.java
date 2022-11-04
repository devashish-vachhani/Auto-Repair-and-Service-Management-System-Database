package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import config.ConnectionDB;

public class OwnsVehiceOperations {

    public static Boolean insertOwnsVehicle(Integer CustomerID, Integer ServiceCenter, Integer Vin) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "INSERT INTO OWNS_VEHICLE(CustomerID, ServiceCenter, Vin ) VALUES (?,?,?)";
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, CustomerID);
            st.setInt(2, ServiceCenter);
            st.setInt(3, Vin);
            st.executeUpdate();
            ConnectionDB.closeConnection(connection);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static Boolean deleteOwnsVehicle(Integer CustomerID, Integer ServiceCenter, Integer Vin) {
        try {
            Connection connection = ConnectionDB.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM OwnsVehicle WHERE CustomerID= " + CustomerID + "ServiceCenter=" + ServiceCenter + "Vin=" + Vin);
            ConnectionDB.closeConnection(connection);
            return Boolean.valueOf(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Boolean.valueOf(false);
        }
    }
}