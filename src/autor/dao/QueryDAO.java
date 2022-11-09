package dao;

import config.ConnectionDB;
import models.CarService;
import models.Customer;
import models.ServiceCenter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class QueryDAO {
    public static Integer query1Output(){
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "select C.sc_id from Customer C group by C.sc_id having count(*) >= all (SELECT count(*) from Customer C2 group by C2.sc_id)";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            Integer serviceCenterId = null;
            while(rs.next()) {
                serviceCenterId=rs.getInt("SC_ID");
            }
            ConnectionDB.closeConnection(connection);
            return serviceCenterId;
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return null;
        }
    }
    public static Float query2Output(){
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "select avg(OP.PRICE) as AVG_PRICE from OFFEREDPRICE OP where OP.S_ID = (select CS.S_ID from CARSERVICE CS where CS.NAME = 'EVAPORATORREPAIR') and OP.BRAND= 'HONDA'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            Float queryOutput = null;
            while(rs.next()) {
                queryOutput=rs.getFloat("AVG_PRICE");
            }
            ConnectionDB.closeConnection(connection);
            return queryOutput;
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return null;
        }
    }
    public static ArrayList<Customer> query3Output(){
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "select C.F_NAME as FIRSTNAME, C.L_NAME as LASTNAME, C.USER_ID from CUSTOMER C where C.SC_ID= 30003 AND C.STANDING= 0 ";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            ArrayList<Customer> customers = new ArrayList<>();
            while(rs.next()) {
                customers.add(new Customer(30003,rs.getLong("USER_ID"), rs.getString("FIRSTNAME"),rs.getString("LASTNAME")));
            }
            ConnectionDB.closeConnection(connection);
            return customers;
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return new ArrayList<>();
        }
    }
    public static ArrayList<CarService> query4Output(){
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "select CS.S_ID as SERVICEID, CS.NAME as SERVICENAME from CARSERVICE CS where CS.TYPE= 'MR'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            ArrayList<CarService> carservice = new ArrayList<>();
            while(rs.next()) {
                carservice.add(new CarService(rs.getInt("SERVICEID"), rs.getString("SERVICENAME")));
            }
            ConnectionDB.closeConnection(connection);
            return carservice;
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return new ArrayList<>();
        }
    }
    public static Float query5Output(){
        try {
            Connection connection = ConnectionDB.getConnection();
            Statement st1 = connection.createStatement();
            Statement st2 = connection.createStatement();

            String query1 = "select SUM(OP.PRICE) as SUM_PRICE1 from OFFEREDPRICE OP where OP.SC_ID= 30001 and OP.BRAND= 'TOYOTA' and OP.S_ID in (select CS.S_ID from CARSERVICE CS where CS.NAME= 'BELTREPLACEMENT' or CS.NAME= 'A')";
            String query2 = "select SUM(OP.PRICE) as SUM_PRICE2 from OFFEREDPRICE OP where OP.SC_ID= 30002 and OP.BRAND= 'TOYOTA' and OP.S_ID in (select CS.S_ID from CARSERVICE CS where CS.NAME= 'BELTREPLACEMENT' or CS.NAME= 'A')";
            ResultSet rs1 = st1.executeQuery(query1);
            ResultSet rs2 = st2.executeQuery(query2);
            Float queryOutput1 = null;
            Float queryOutput2 = null;
            while(rs1.next()) {
                queryOutput1=rs1.getFloat("SUM_PRICE1");
            }
            while(rs2.next()) {
                queryOutput2=rs2.getFloat("SUM_PRICE2");
            }
            ConnectionDB.closeConnection(connection);
            return queryOutput1-queryOutput2;
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return null;
        }
    }

    public static String query6Output(){
        try {
            Connection connection = ConnectionDB.getConnection();
            Statement st = connection.createStatement();
            String query = "select LAST_SERVICE from VEHICLE where VIN = '34KLE19D'";
            ResultSet rs1 = st.executeQuery(query);
            String type = null;
            while(rs1.next()) {
                type = rs1.getString("LAST_SERVICE");
            }
            HashMap<String, String> hm = new HashMap<>();
            hm.put("A", "B");hm.put("B", "C");hm.put("C", "A");hm.put("O", "A");
            String result = hm.get(type);
            ConnectionDB.closeConnection(connection);
            return result;
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return null;
        }
    }

}
