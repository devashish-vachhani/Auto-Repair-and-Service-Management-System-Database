package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import config.ConnectionDB;

public class VehicleDAO {
    public static Boolean insertVehicle(Integer Vin, String CarManufacturer, Long CurrentMileage, Character LastScheduledMaintenanceService, Integer Year) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "INSERT INTO VEHICLE VALUES (?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, Vin);
            st.setString(2, CarManufacturer);
            st.setLong(3, CurrentMileage);
            st.setCharacter(4, LastScheduledMaintenanceService);
            st.setInt(5, Year);
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
