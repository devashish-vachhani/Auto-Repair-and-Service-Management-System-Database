package dao;

import config.ConnectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AmountChargedDAO {
    public static Integer viewAmountCharged(Integer ScId, String Vin, String cartString) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "Select SUM(OP.PRICE) AS AMOUNT_CHARGED FROM OFFEREDPRICE OP, VEHICLE V, CARSERVICE CS WHERE OP.SC_ID=" + ScId + " AND V.VIN='" + Vin + "' AND V.BRAND = OP.BRAND AND OP.S_ID = CS.S_ID AND CS.NAME IN (" + cartString + ")";
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            Integer amountCharged = null;
            while (rs.next()) {
                amountCharged = Integer.valueOf(rs.getInt("AMOUNT_CHARGED"));
            }
            ConnectionDB.closeConnection(connection);
            return amountCharged;
        } catch (SQLException ex) {
            System.out.println("Caught SQL Exception "+ ex.getErrorCode() + "/" + ex.getSQLState() + "/" + ex.getMessage());
            return null;
        }
    }
}
