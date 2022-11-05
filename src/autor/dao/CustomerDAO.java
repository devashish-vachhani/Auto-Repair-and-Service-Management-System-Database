package dao;

import config.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {
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
}