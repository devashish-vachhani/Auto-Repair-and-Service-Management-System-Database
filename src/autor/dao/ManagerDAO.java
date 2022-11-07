package dao;

import config.ConnectionDB;
import models.CarService;
import models.TypeEnum;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ManagerDAO {
    public static String addEmployees(User user, Long userId, String firstName, String lastName, String address, String emailAddress, Long phoneNumber, String role, String startDate, Integer compensation, String username) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String empFullName = firstName + " " + lastName;
            String password = lastName;
            if(role.equals("R")){
                role = "RECEPTIONIST";
            } else {
                role = "MECHANIC";
            }

            PreparedStatement pst = connection.prepareStatement("insert into USERS(USER_ID, SC_ID, USERNAME, PASSWORD, ROLE) values(?,?,?,?,?)");
            pst.setLong(1, userId);
            pst.setInt(2, user.getSc_id());
            pst.setString(3, username);
            pst.setString(4, password);
            pst.setString(5, role);
            Integer res = pst.executeUpdate();

            PreparedStatement pst1 = connection.prepareStatement("insert into EMPLOYEE(USER_ID, NAME, ADDRESS, EMAIL, PHONE_NO, ROLE, SC_ID) values(?,?,?,?,?,?,?)");
            pst1.setLong(1, userId);
            pst1.setString(2, empFullName);
            pst1.setString(3, address);
            pst1.setString(4, emailAddress);
            pst1.setLong(5, phoneNumber);
            pst1.setString(6, role);
            pst1.setInt(7, user.getSc_id());
            Integer res1 = pst1.executeUpdate();

            if (role.equals("RECEPTIONIST")){
                PreparedStatement pst2 = connection.prepareStatement("insert into RECEPTIONIST(USER_ID, SC_ID, ANNUAL_SALARY) values(?,?,?)");
                pst2.setLong(1, userId);
                pst2.setInt(2, user.getSc_id());
                pst2.setInt(3, compensation);
                Integer res2= pst2.executeUpdate();
            } else {
                PreparedStatement pst3 = connection.prepareStatement("insert into MECHANIC(USER_ID, SC_ID, HOURS_WORKED) values(?,?,?)");
                pst3.setLong(1, userId);
                pst3.setInt(2, user.getSc_id());
                pst3.setInt(3, 50);
                Integer res3= pst3.executeUpdate();
            }
            ConnectionDB.closeConnection(connection);
            return "Success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String setOperationalHours(User user, String operationalHours) {
        try {
            Connection connection = ConnectionDB.getConnection();
            Integer isOpen = 0;
            if(operationalHours.equals("Y")){
                isOpen = 1;
            }

            Statement st = connection.createStatement();
            String query = "UPDATE SERVICECENTER SET OPEN_SAT=" + isOpen + " WHERE SC_ID=" + user.getSc_id();
            st.executeUpdate(query);
            ConnectionDB.closeConnection(connection);
            return "Success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String setupMaintenancePrice(User user, String bundle, Integer hondaPrice, Integer infinitiPrice, Integer lexusPrice, Integer nissanPrice, Integer toyotaPrice) {
        try {
            Connection connection = ConnectionDB.getConnection();
            Integer sc_id = user.getSc_id();

            Statement st = connection.createStatement();
            String query = "SELECT S_ID FROM CARSERVICE WHERE NAME= '" + bundle +"'";
            //single row
            ResultSet rs = st.executeQuery(query);
            Integer s_id = null;
//            CarService carService = null;
            while(rs.next()){
//                carService = new CarService(rs.getInt("S_ID"), rs.getString("NAME"), TypeEnum.valueOf(rs.getString("TYPE")));
                s_id = rs.getInt("S_ID");
            }

            Statement st1 = connection.createStatement();
            String query1 = "UPDATE OFFEREDPRICE SET PRICE=" + hondaPrice + " WHERE BRAND= 'HONDA' AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st1.executeUpdate(query1);

            Statement st2 = connection.createStatement();
            String query2 = "UPDATE OFFEREDPRICE SET PRICE=" + lexusPrice + " WHERE BRAND= 'LEXUS'AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st2.executeUpdate(query2);

            Statement st3 = connection.createStatement();
            String query3 = "UPDATE OFFEREDPRICE SET PRICE=" + infinitiPrice + " WHERE BRAND= 'INFINITI'AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st3.executeUpdate(query3);

            Statement st4 = connection.createStatement();
            String query4 = "UPDATE OFFEREDPRICE SET PRICE=" + nissanPrice + " WHERE BRAND= 'NISSAN'AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st4.executeUpdate(query4);

            Statement st5 = connection.createStatement();
            String query5 = "UPDATE OFFEREDPRICE SET PRICE=" + toyotaPrice + " WHERE BRAND= 'TOYOTA'AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st5.executeUpdate(query5);

            ConnectionDB.closeConnection(connection);
            return "Success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<CarService> displayRepairCarService(User user) {
        try {
            Connection connection = ConnectionDB.getConnection();

            Statement st = connection.createStatement();
            String query = "SELECT * FROM CARSERVICE WHERE TYPE= 'R' OR TYPE='MR'";
            ResultSet rs = st.executeQuery(query);
            ArrayList<CarService> carServices = new ArrayList<>();
            while(rs.next()){
                carServices.add(new CarService(rs.getInt("S_ID"), rs.getString("NAME"), TypeEnum.valueOf(rs.getString("TYPE"))));
            }
            ConnectionDB.closeConnection(connection);
            return carServices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String setupRepairPrice(User user, Integer s_id, Integer hondaPrice, Integer infinitiPrice, Integer lexusPrice, Integer nissanPrice, Integer toyotaPrice) {
        try {
            Connection connection = ConnectionDB.getConnection();
            Integer sc_id = user.getSc_id();
            Statement st1 = connection.createStatement();
            String query1 = "UPDATE OFFEREDPRICE SET PRICE=" + hondaPrice + " WHERE BRAND= 'HONDA' AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st1.executeUpdate(query1);

            Statement st2 = connection.createStatement();
            String query2 = "UPDATE OFFEREDPRICE SET PRICE=" + lexusPrice + " WHERE BRAND= 'LEXUS' AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st2.executeUpdate(query2);

            Statement st3 = connection.createStatement();
            String query3 = "UPDATE OFFEREDPRICE SET PRICE=" + infinitiPrice + " WHERE BRAND= 'INFINITI' AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st3.executeUpdate(query3);

            Statement st4 = connection.createStatement();
            String query4 = "UPDATE OFFEREDPRICE SET PRICE=" + nissanPrice + " WHERE BRAND= 'NISSAN' AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st4.executeUpdate(query4);

            Statement st5 = connection.createStatement();
            String query5 = "UPDATE OFFEREDPRICE SET PRICE=" + toyotaPrice + " WHERE BRAND= 'TOYOTA' AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st5.executeUpdate(query5);

            ConnectionDB.closeConnection(connection);
            return "Success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
