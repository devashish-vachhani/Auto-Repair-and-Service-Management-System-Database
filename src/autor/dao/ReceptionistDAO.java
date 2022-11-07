package dao;

import config.ConnectionDB;
import models.PendingCustomers;
import models.User;

import java.sql.*;
import java.util.ArrayList;

public class ReceptionistDAO {
    public static String addCustomer(User user, Long custId, String custUsername, String custFirstName, String custLastName, String custAddress, String role, String custEmail, Long custPhoneNumber, String vin, String brand, String mileage, String year, Integer status, Integer standing) {
        try {
            Connection connection = ConnectionDB.getConnection();

            Integer sc_id = user.getSc_id();

            PreparedStatement pst1 = connection.prepareStatement("insert into USERS(USER_ID, SC_ID, USERNAME, PASSWORD, ROLE) values(?,?,?,?,?)");
            pst1.setLong(1, custId);
            pst1.setInt(2, sc_id);
            pst1.setString(3, custUsername);
            pst1.setString(4, custLastName);
            pst1.setString(5, "CUSTOMER");
            Integer res1 = pst1.executeUpdate();

            PreparedStatement pst2 = connection.prepareStatement("insert into CUSTOMER(SC_ID, USER_ID, F_NAME, L_NAME, ADDRESS, EMAIL, PHONE_NO, STANDING, STATUS) values(?,?,?,?,?,?,?,?,?)");
            pst2.setInt(1, sc_id);
            pst2.setLong(2, custId);
            pst2.setString(3, custFirstName);
            pst2.setString(4, custLastName);
            pst2.setString(5, custAddress);
            pst2.setString(6, custEmail);
            pst2.setLong(7, custPhoneNumber);
            pst2.setInt(8, standing);
            pst2.setInt(9, status);
            Integer res2 = pst2.executeUpdate();

            PreparedStatement pst3 = connection.prepareStatement("insert into VEHICLE(VIN, USER_ID, SC_ID, BRAND, MILEAGE, YEAR, LAST_SERVICE) values(?,?,?,?,?,?,?)");
            pst3.setString(1, vin);
            pst3.setLong(2, custId);
            pst3.setInt(3, sc_id);
            pst3.setString(4, brand);
            pst3.setString(5, mileage);
            pst3.setString(6, year);
            pst3.setString(7, "O");
            Integer res3 = pst3.executeUpdate();

            ConnectionDB.closeConnection(connection);
            return "Sucess";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<PendingCustomers> viewPendingCustomers(User user) {
        try {
            Connection connection = ConnectionDB.getConnection();

            Integer sc_id = user.getSc_id();

            Statement st = connection.createStatement();
            String query = "SELECT S.CUST_ID, C.F_NAME, C.L_NAME, S.SE_ID, S.SERVICE_DATE, S.AMOUNT_CHARGED FROM SERVICEEVENT S, CUSTOMER C WHERE S.CUST_ID = C.USER_ID AND S.STATUS = 'UNPAID' AND S.SC_ID =" + sc_id;
            ResultSet rs = st.executeQuery(query);
            ArrayList<PendingCustomers> pendingCustomers= new ArrayList<>();
            while (rs.next()){
                pendingCustomers.add(new PendingCustomers(rs.getLong("CUST_ID"), rs.getString("F_NAME"), rs.getString("L_NAME"), rs.getInt("SE_ID"), rs.getString("SERVICE_DATE"), rs.getInt("AMOUNT_CHARGED")));
            }
            ConnectionDB.closeConnection(connection);
            return pendingCustomers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
