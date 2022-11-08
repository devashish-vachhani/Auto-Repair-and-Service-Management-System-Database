package dao;

import config.ConnectionDB;
import models.Vehicle;

import java.sql.*;
import java.util.ArrayList;

public class VehicleDAO {
    public static void viewVehicles(Long UserId, Integer ScId) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "Select * from VEHICLE where USER_ID=" + UserId + "AND SC_ID=" + ScId;
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            while (rs.next()) {
                Vehicle v = new Vehicle(rs.getString("VIN"), Long.valueOf(rs.getLong("USER_ID")), Integer.valueOf(rs.getInt("SC_ID")), rs.getString("BRAND"), Long.valueOf(rs.getLong("MILEAGE")), Integer.valueOf(rs.getInt("YEAR")), rs.getString("LAST_SERVICE"));
                vehicles.add(v);
            }
            System.out.println("List of vehicles:");
            for(int i=0; i<vehicles.size(); i++) {
                Vehicle v = vehicles.get(i);
                System.out.println(i+1 + ".VIN: " + v.getVin() + " BRAND: " + v.getBrand() + " MILEAGE: " + v.getMileage() + " YEAR: " + v.getYear());
            }
            ConnectionDB.closeConnection(connection);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static Vehicle viewVehicle(String Vin) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "Select * from VEHICLE where VIN='" + Vin + "'";
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            Vehicle vehicle = new Vehicle();
            while (rs.next()) {
                vehicle = new Vehicle(rs.getString("VIN"), Long.valueOf(rs.getLong("USER_ID")), Integer.valueOf(rs.getInt("SC_ID")), rs.getString("BRAND"), Long.valueOf(rs.getLong("MILEAGE")), Integer.valueOf(rs.getInt("YEAR")), rs.getString("LAST_SERVICE"));
            }
            ConnectionDB.closeConnection(connection);
            return vehicle;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static Boolean insertVehicle(String Vin, Long UserId, Integer ScId, String Brand, Long Mileage, Integer Year) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "INSERT INTO VEHICLE VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, Vin);
            st.setLong(2, UserId);
            st.setInt(3, ScId);
            st.setString(4, Brand);
            st.setLong(5, Mileage);
            st.setInt(6, Year);
            st.setString(7, "O");
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
    public static Integer countVehicles(Long UserId, Integer ScId) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "Select COUNT(*) as COUNT_VEHICLES from VEHICLE where USER_ID=" + UserId + "AND SC_ID=" + ScId;
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            Integer count = null;
            while (rs.next()) {
                count = Integer.valueOf(rs.getInt("COUNT_VEHICLES"));
            }
            ConnectionDB.closeConnection(connection);
            return count;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
