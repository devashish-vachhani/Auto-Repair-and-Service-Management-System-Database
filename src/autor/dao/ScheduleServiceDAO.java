package dao;

import config.ConnectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScheduleServiceDAO {
    public static Integer countTotalHours(String Vin, String names) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "SELECT SUM(HRS) AS TOTAL_HOURS FROM OFFEREDTIME OT, VEHICLE V WHERE V.VIN = '" + Vin + "' AND V.BRAND = OT.BRAND AND OT.S_ID IN (SELECT S_ID FROM CARSERVICE CS WHERE CS.NAME IN (" + names + "))";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            Integer totalHours = null;
            while (rs.next()) {
                totalHours = Integer.valueOf(rs.getInt("TOTAL_HOURS"));
            }
            ConnectionDB.closeConnection(connection);
            return totalHours;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}



