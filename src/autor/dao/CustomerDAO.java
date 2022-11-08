package dao;

import config.ConnectionDB;
import models.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO {
    public static void viewCustomer(Long UserId, Integer ScId) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "Select USER_ID,F_NAME,L_NAME,ADDRESS,EMAIL,PHONE_NO from CUSTOMER where USER_ID=" + UserId + "AND SC_ID=" + ScId;
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            ArrayList<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                Customer c = new Customer(Long.valueOf(rs.getLong("USER_ID")), rs.getString("F_NAME"), rs.getString("L_NAME"), rs.getString("ADDRESS"), rs.getString("EMAIL"), Long.valueOf(rs.getLong("PHONE_NO")));
                customers.add(c);
            }
            if(customers.size() == 1) {
                Customer customer = customers.get(0);
                System.out.println("USER ID: " + customer.getUserId() + " FIRST NAME: " + customer.getFName() + " LAST NAME: " + customer.getLName() + " ADDRESS: " + customer.getAddress() + " EMAIL: " + customer.getEmail() + " PHONE NO: " + customer.getPhoneNo());
            }
            ConnectionDB.closeConnection(connection);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static Boolean insertCustomer(Integer CustomerId, String FullName, Integer ServiceCenter, Boolean Status, Boolean Standing) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "INSERT INTO CUSTOMER VALUES (?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, CustomerId);
            st.setString(2, FullName);
            st.setInt(3, ServiceCenter);
            st.setBoolean(4, Status);
            st.setBoolean(5, Standing);
            st.executeUpdate();
            ConnectionDB.closeConnection(connection);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public static void updateCustomerStatus(Long UserId, Integer ScId, Integer Status) {
        try {
        Connection connection = ConnectionDB.getConnection();
        String query = "UPDATE CUSTOMER set STATUS=? WHERE USER_ID =? AND SC_ID=?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1, Status);
        pst.setLong(2, UserId);
        pst.setInt(3, ScId);
        pst.executeUpdate();
        ConnectionDB.closeConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void updateCustomerStanding(Long UserId, Integer ScId, Integer Standing) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "UPDATE CUSTOMER set STANDING=? WHERE USER_ID =? AND SC_ID=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, Standing);
            pst.setLong(2, UserId);
            pst.setInt(3, ScId);
            pst.executeUpdate();
            ConnectionDB.closeConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}