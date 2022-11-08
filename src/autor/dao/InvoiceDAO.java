package dao;

import config.ConnectionDB;
import models.Invoice;
import models.TypeEnum;

import java.sql.*;
import java.util.ArrayList;

public class InvoiceDAO {
    public static Boolean updateInvoiceStatusBySeId(Integer SeId) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "SELECT STATUS FROM SERVICEEVENT WHERE SE_ID = " + SeId;
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            String status = "";
            while (rs.next()) {
                status = rs.getString("STATUS");
            }
            if(status.equals("PAID")) {
                System.out.println("The invoice is already paid");
                ConnectionDB.closeConnection(connection);
                return true;
            }
            query = "Update SERVICEEVENT set STATUS=? WHERE SE_ID =?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, "PAID");
            pst.setInt(2, SeId);
            pst.executeUpdate();
            ConnectionDB.closeConnection(connection);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Boolean.valueOf(false);
        }
    }
    public static ArrayList<Invoice> viewInvoiceBySeId(Integer SeId) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "SELECT SE.SE_ID, SE.CUST_ID, SE.VIN, SE.SERVICE_DATE, CS.S_ID, CS.TYPE, SE.STATUS, E.NAME, OP.PRICE, SE.AMOUNT_CHARGED FROM SERVICEEVENT SE, CARSERVICE CS, EMPLOYEE E, OFFEREDPRICE OP, SERVICEEVENTDETAILS SED, Vehicle V WHERE SE.SE_ID =" + SeId + " AND SE.SE_ID = SED.SE_ID AND SED.S_ID = CS.S_ID AND SE.MECH_ID = E.USER_ID AND SE.VIN = V.VIN AND CS.S_ID = OP.S_ID AND SE.SC_ID = OP.SC_ID AND V.BRAND = OP.BRAND";
            Statement st = connection.createStatement();
            ResultSet rs =  st.executeQuery(query);
            ArrayList<Invoice> invoices = new ArrayList<>();
            while (rs.next()) {
                Invoice invoice = new Invoice(Integer.valueOf(rs.getInt("SE_ID")), Long.valueOf(rs.getLong("CUST_ID")), rs.getString("VIN"), Integer.valueOf(rs.getInt("SERVICE_DATE")), Integer.valueOf(rs.getInt("S_ID")), TypeEnum.valueOf(rs.getString("TYPE")), rs.getString("STATUS"), rs.getString("NAME"), Integer.valueOf(rs.getInt("PRICE")), Integer.valueOf(rs.getInt("AMOUNT_CHARGED")));
                invoices.add(invoice);
            }
            ConnectionDB.closeConnection(connection);
            return invoices;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
