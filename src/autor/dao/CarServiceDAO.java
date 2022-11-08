package dao;

import config.ConnectionDB;
import models.CarService;
import models.TypeEnum;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CarServiceDAO {
    public static void viewCarServices(Integer SeId) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "Select * FROM CARSERVICE where S_ID IN (SELECT S_ID FROM SERVICEEVENTDETAILS WHERE SE_ID=" + SeId + ")";
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            ArrayList<CarService> carServices = new ArrayList<>();
            while (rs.next()) {
                CarService carService = new CarService(Integer.valueOf(rs.getInt("S_ID")), rs.getString("NAME"), TypeEnum.valueOf(rs.getString("TYPE")));
                carServices.add(carService);
            }
            ConnectionDB.closeConnection(connection);
            System.out.println(carServices);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static CarService viewCarService(String Name) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "Select * FROM CARSERVICE WHERE NAME='" + Name + "'";
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            CarService carService = new CarService();
            while (rs.next()) {
                carService = new CarService(Integer.valueOf(rs.getInt("S_ID")), rs.getString("NAME"), TypeEnum.valueOf(rs.getString("TYPE")));
            }
            ConnectionDB.closeConnection(connection);
            return carService;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static Set<Integer> getSIds(String names) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "Select S_ID FROM CARSERVICE WHERE NAME IN (" + names + ")";;
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            Set<Integer> sIds = new HashSet<>();
            while (rs.next()) {
                sIds.add(Integer.valueOf(rs.getInt("S_ID")));
            }
            ConnectionDB.closeConnection(connection);
            return sIds;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
