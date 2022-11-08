package dao;

import config.ConnectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarServiceRepair {
    public static ArrayList<String> viewNamesByCategory(String Category) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "Select NAME FROM CARSERVICE C, REPAIR R WHERE C.S_ID = R.S_ID AND CATEGORY='" + Category + "'";
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            ArrayList<String> names = new ArrayList<>();
            while (rs.next()) {
                names.add(rs.getString("NAME"));
            }
            ConnectionDB.closeConnection(connection);
            return names;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
