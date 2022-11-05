package dao;

import config.ConnectionDB;
import models.Vehicle;

import java.sql.*;
import java.util.ArrayList;

public class VehicleDAO {
    public static ArrayList<Vehicle> viewVehicles(Integer CustomerId) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "Select * from VEHICLE where CUSTOMERID=" + CustomerId;
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            while (rs.next()) {
                Vehicle v = new Vehicle(rs.getString("VIN"), rs.getString("CARMANUFACTURER"), Long.valueOf(rs.getLong("CURRENTMILEAGE")), rs.getString("LASTSCHEDULEDMAINTENANCESERVICE"), Integer.valueOf(rs.getInt("YEAR")), Integer.valueOf(rs.getInt("CUSTOMERID")));
                vehicles.add(v);
            }
            return vehicles;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static Boolean insertVehicle(String Vin, String CarManufacturer, Long CurrentMileage, String LastScheduledMaintenanceService, Integer Year, Integer CustomerId) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "INSERT INTO VEHICLE VALUES (?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, Vin);
            st.setString(2, CarManufacturer);
            st.setLong(3, CurrentMileage);
            st.setString(4, LastScheduledMaintenanceService);
            st.setInt(5, Year);
            st.setInt(6, CustomerId);
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public static Boolean deleteVehicle(String Vin) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "DELETE FROM VEHICLE WHERE VIN=?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, Vin);
            st.executeUpdate();
            ConnectionDB.closeConnection(connection);
            return Boolean.valueOf(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Boolean.valueOf(false);
        }
    }
}
