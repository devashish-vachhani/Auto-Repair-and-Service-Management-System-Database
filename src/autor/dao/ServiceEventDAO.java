package dao;

import config.ConnectionDB;
import models.ServiceEvent;

import java.sql.*;
import java.util.ArrayList;

public class ServiceEventDAO {
    public static ArrayList<ServiceEvent> viewServiceEventByCustomer(Long UserId, Integer ScId) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "SELECT * FROM SERVICEEVENT where CUST_ID=" + UserId + " AND SC_ID=" + ScId;
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            ArrayList<ServiceEvent> serviceEvents = new ArrayList<>();
            while (rs.next()) {
                ServiceEvent serviceEvent = new ServiceEvent(Integer.valueOf(rs.getInt("SE_ID")), rs.getString("VIN"), Long.valueOf(rs.getLong("MECH_ID")), Integer.valueOf(rs.getInt("SC_ID")), Long.valueOf(rs.getLong("CUST_ID")), Integer.valueOf(rs.getInt("AMOUNT_PAID")), Integer.valueOf(rs.getInt("AMOUNT_CHARGED")), Integer.valueOf(rs.getInt("SERVICE_DATE")), rs.getString("STATUS"), Integer.valueOf(rs.getInt("TOTAL_HOURS")));
                serviceEvents.add(serviceEvent);
            }
            ConnectionDB.closeConnection(connection);
            return serviceEvents;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static Integer countUnpaidInvoicesByCustomer(Long UserId, Integer ScId) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "SELECT COUNT(*) AS COUNT_UNPAID_INVOICES FROM SERVICEEVENT WHERE CUST_ID = " + UserId + " AND SC_ID = " + ScId + " AND STATUS = 'UNPAID'";
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            Integer count = null;
            while (rs.next()) {
                count = Integer.valueOf(rs.getInt("COUNT_UNPAID_INVOICES"));
            }
            ConnectionDB.closeConnection(connection);
            return count;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static Integer getNewSeId() {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "SELECT COUNT(*) AS COUNT_SE_ID FROM SERVICEEVENT";
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            Integer count = null;
            while (rs.next()) {
                count = Integer.valueOf(rs.getInt("COUNT_SE_ID"));
            }
            ConnectionDB.closeConnection(connection);
            return count;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static Boolean insertServiceEvent(Integer SeId, String Vin, Long MechId, Integer ScId, Long CustId, Integer AmountCharged, Integer ServiceDate, Integer TotalHours) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "INSERT INTO SERVICEEVENT(SE_ID, VIN, MECH_ID, SC_ID, CUST_ID, AMOUNT_PAID, AMOUNT_CHARGED, SERVICE_DATE, STATUS, TOTAL_HOURS) values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, SeId);
            pst.setString(2, Vin);
            pst.setLong(3, MechId);
            pst.setInt(4, ScId);
            pst.setLong(5, CustId);
            pst.setInt(6, 0);
            pst.setInt(7, AmountCharged);
            pst.setInt(8, ServiceDate);
            pst.setString(9, "UNPAID");
            pst.setInt(10, TotalHours);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
