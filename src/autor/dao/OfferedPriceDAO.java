package dao;

import config.ConnectionDB;
import models.OfferedPrice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OfferedPriceDAO {
    public static OfferedPrice viewOfferedPrice(Integer SId, Integer ScId, String Brand) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "Select * FROM OFFEREDPRICE WHERE S_ID=" + SId + " AND SC_ID=" + ScId + " AND BRAND='" + Brand + "'";
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            OfferedPrice offeredPrice = null;
            while (rs.next()) {
                offeredPrice = new OfferedPrice(Integer.valueOf(rs.getInt("S_ID")), Integer.valueOf(rs.getInt("SC_ID")), rs.getString("BRAND"), Integer.valueOf(rs.getInt("PRICE")));
            }
            ConnectionDB.closeConnection(connection);
            return offeredPrice;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
