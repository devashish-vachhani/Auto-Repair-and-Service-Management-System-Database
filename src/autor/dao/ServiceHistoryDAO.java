package dao;

import config.ConnectionDB;
import models.ServiceHistory;
import models.TypeEnum;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServiceHistoryDAO {
    public static ArrayList<ServiceHistory> viewServiceHistory(Long UserId, Integer ScId, String Vin) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "SELECT CS.S_ID, SE.VIN, CS.TYPE, OP.PRICE, E.NAME, SE.SERVICE_DATE FROM SERVICEEVENT SE, SERVICEEVENTDETAILS SED, CARSERVICE CS, EMPLOYEE E, OFFEREDPRICE OP, VEHICLE V WHERE SE.CUST_ID=" + UserId + " AND SE.SC_ID=" + ScId + " AND SE.VIN='" + Vin + "' AND SE.SE_ID = SED.SE_ID AND SED.S_ID = CS.S_ID AND SE.MECH_ID = E.USER_ID AND SE.VIN = V.VIN AND V.BRAND = OP.BRAND AND CS.S_ID = OP.S_ID AND SE.SC_ID = OP.SC_ID";
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            ArrayList<ServiceHistory> serviceHistories = new ArrayList<>();
            while (rs.next()) {
                ServiceHistory serviceHistory = new ServiceHistory(Integer.valueOf(rs.getInt("S_ID")), rs.getString("VIN"), TypeEnum.valueOf(rs.getString("TYPE")), Integer.valueOf(rs.getInt("PRICE")), rs.getString("NAME"), Integer.valueOf(rs.getInt("SERVICE_DATE")));
                serviceHistories.add(serviceHistory);
            }
            ConnectionDB.closeConnection(connection);
            return serviceHistories;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}