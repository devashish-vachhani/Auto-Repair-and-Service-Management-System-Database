package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import config.ConnectionDB;

public class CustomerDAO {
    public static Boolean insertCustomer(Integer CustomerID, String FullName, Integer ServiceCenter, Boolean Status, Boolean Standing) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "INSERT INTO CUSTOMER VALUES (?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, CustomerID);
            st.setString(2, FullName);
            st.setInt(3, ServiceCenter);
            st.setBoolean(4, Status);
            st.setBoolean(5, Standing);
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}