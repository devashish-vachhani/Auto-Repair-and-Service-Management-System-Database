package dao;

import config.ConnectionDB;
import models.CarService;
import models.TypeEnum;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ManagerDAO {
    public static String addEmployees(User user, Long userId, String firstName, String lastName, String address, String emailAddress, Long phoneNumber, String role, String startDate, Integer compensation, String username) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String empFullName = firstName + " " + lastName;
            String password = lastName;
            if(role.equals("R")){
                role = "RECEPTIONIST";
            } else {
                role = "MECHANIC";
            }

            PreparedStatement pst = connection.prepareStatement("insert into USERS(USER_ID, SC_ID, USERNAME, PASSWORD, ROLE) values(?,?,?,?,?)");
            pst.setLong(1, userId);
            pst.setInt(2, user.getSc_id());
            pst.setString(3, username);
            pst.setString(4, password);
            pst.setString(5, role);
            Integer res = pst.executeUpdate();

            PreparedStatement pst1 = connection.prepareStatement("insert into EMPLOYEE(USER_ID, NAME, ADDRESS, EMAIL, PHONE_NO, ROLE, SC_ID) values(?,?,?,?,?,?,?)");
            pst1.setLong(1, userId);
            pst1.setString(2, empFullName);
            pst1.setString(3, address);
            pst1.setString(4, emailAddress);
            pst1.setLong(5, phoneNumber);
            pst1.setString(6, role);
            pst1.setInt(7, user.getSc_id());
            Integer res1 = pst1.executeUpdate();

            if (role.equals("RECEPTIONIST")){
                PreparedStatement pst2 = connection.prepareStatement("insert into RECEPTIONIST(USER_ID, SC_ID, ANNUAL_SALARY) values(?,?,?)");
                pst2.setLong(1, userId);
                pst2.setInt(2, user.getSc_id());
                pst2.setInt(3, compensation);
                Integer res2= pst2.executeUpdate();
            } else {
                PreparedStatement pst3 = connection.prepareStatement("insert into MECHANIC(USER_ID, SC_ID) values(?,?)");
                pst3.setLong(1, userId);
                pst3.setInt(2, user.getSc_id());;
                Integer res3= pst3.executeUpdate();

                Statement st = connection.createStatement();
                String query = "SELECT OPEN_SAT FROM SERVICECENTER WHERE SC_ID=" + user.getSc_id();
                Integer svcId = user.getSc_id();
                ResultSet rs = st.executeQuery(query);
                Integer openSat = null;
                while(rs.next()){
                    openSat = rs.getInt("OPEN_SAT");
                }
                StringBuilder queryForMechSchedule = new StringBuilder("INSERT ALL ");
                StringBuilder queryForSlots = new StringBuilder("INSERT ALL ");
                Integer slot_id = 1;
                for(int week=1;week<=4;week++){
                    for(int day=1;day<=6;day++){
                        if(day!=6){
                            for(int slot=1;slot<=11;slot++){
                                queryForMechSchedule.append("into mechanicschedule (MECH_ID,SC_ID,SLOT_ID,AVAILABLE) VALUES (" + userId + ", " + svcId + ", " + slot_id + ", 'AVAILABLE')");
//                                queryForSlots.append("into slots (WEEK,SLOT_DAY,SLOTS) VALUES (" + week + ", " + day + ", " + slot + ")");
                                slot_id++;
                                if (slot_id != 241){
                                    queryForMechSchedule.append(" \n");
                                    queryForSlots.append(" \n");
                                }
                                else{
                                    queryForMechSchedule.append("SELECT 1 FROM DUAL");
                                    queryForSlots.append("SELECT 1 FROM DUAL");
                                }
                            }
                        }
                        else{
//                          For saturdays
                            for(int slot=1;slot<=5;slot++) {
                                if (openSat == 1) {
                                    queryForMechSchedule.append("into mechanicschedule (MECH_ID,SC_ID,SLOT_ID,AVAILABLE) VALUES (" + userId + ", " + svcId + ", " + slot_id + ", 'AVAILABLE')");
                                } else {
                                    queryForMechSchedule.append("into mechanicschedule (MECH_ID,SC_ID,SLOT_ID,AVAILABLE) VALUES (" + userId + ", " + svcId + ", " + slot_id + ", 'CLOSED')");
                                }
//                                queryForSlots.append("into slots (WEEK,SLOT_DAY,SLOTS) VALUES (" + week + ", " + day + ", " + slot + ")");
                                slot_id++;
                                if (slot_id != 241){
                                    queryForMechSchedule.append(" \n");
                                   queryForSlots.append(" \n");
                                }
                                else{
                                    queryForMechSchedule.append("SELECT 1 FROM DUAL");
                                    queryForSlots.append("SELECT 1 FROM DUAL");
                                }
                            }
                        }
                    }
                }
//                String queryAllSlots = queryForSlots.toString();
//                System.out.println(queryAllSlots);
//                st.executeQuery(queryAllSlots);
                String queryAllInserts = queryForMechSchedule.toString();
                System.out.println(queryAllInserts);
                st.executeQuery(queryAllInserts);
            }
            ConnectionDB.closeConnection(connection);
            return "Success";
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return "Failed";
        }
    }

    public static String setOperationalHours(User user, String operationalHours) {
        try {
            Connection connection = ConnectionDB.getConnection();
            Integer isOpen = 0;
            if(operationalHours.equals("Y")){
                isOpen = 1;
            }

            Statement st = connection.createStatement();
            String query = "UPDATE SERVICECENTER SET OPEN_SAT=" + isOpen + " WHERE SC_ID=" + user.getSc_id();
            st.executeUpdate(query);
            Integer[] slotIdRange = new Integer[]{56,57,58,59,60,116,117,118,119,120,176,177,178,179,180,236,237,238,239,240};
            for(int i =0; i<slotIdRange.length; i++){
                if (isOpen == 1) {
                    Statement st1 = connection.createStatement();
                    String query1 = "UPDATE MECHANICSCHEDULE SET AVAILABLE = 'AVAILABLE' WHERE SC_ID=" + user.getSc_id() + " AND SLOT_ID=" + slotIdRange[i];
                    st1.executeQuery(query1);
                } else {
                    Statement st2 = connection.createStatement();
                    String query2 = "UPDATE MECHANICSCHEDULE SET AVAILABLE = 'CLOSED' WHERE SC_ID=" + user.getSc_id()+ " AND SLOT_ID=" + slotIdRange[i];
                    st2.executeQuery(query2);
                }
            }
            ConnectionDB.closeConnection(connection);
            return "Success";
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return "Failed";
        }
    }

    public static String setupMaintenancePrice(User user, String bundle, Integer hondaPrice, Integer infinitiPrice, Integer lexusPrice, Integer nissanPrice, Integer toyotaPrice) {
        try {
            Connection connection = ConnectionDB.getConnection();
            Integer sc_id = user.getSc_id();

            Statement st = connection.createStatement();
            String query = "SELECT S_ID FROM CARSERVICE WHERE NAME= '" + bundle +"'";
            //single row
            ResultSet rs = st.executeQuery(query);
            Integer s_id = null;
//            CarService carService = null;
            while(rs.next()){
//                carService = new CarService(rs.getInt("S_ID"), rs.getString("NAME"), TypeEnum.valueOf(rs.getString("TYPE")));
                s_id = rs.getInt("S_ID");
            }

            Statement st1 = connection.createStatement();
            String query1 = "UPDATE OFFEREDPRICE SET PRICE=" + hondaPrice + " WHERE BRAND= 'HONDA' AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st1.executeUpdate(query1);

            Statement st2 = connection.createStatement();
            String query2 = "UPDATE OFFEREDPRICE SET PRICE=" + lexusPrice + " WHERE BRAND= 'LEXUS'AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st2.executeUpdate(query2);

            Statement st3 = connection.createStatement();
            String query3 = "UPDATE OFFEREDPRICE SET PRICE=" + infinitiPrice + " WHERE BRAND= 'INFINITI'AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st3.executeUpdate(query3);

            Statement st4 = connection.createStatement();
            String query4 = "UPDATE OFFEREDPRICE SET PRICE=" + nissanPrice + " WHERE BRAND= 'NISSAN'AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st4.executeUpdate(query4);

            Statement st5 = connection.createStatement();
            String query5 = "UPDATE OFFEREDPRICE SET PRICE=" + toyotaPrice + " WHERE BRAND= 'TOYOTA'AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st5.executeUpdate(query5);

            ConnectionDB.closeConnection(connection);
            return "Success";
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return "Failed";
        }
    }

    public static ArrayList<CarService> displayRepairCarService(User user) {
        try {
            Connection connection = ConnectionDB.getConnection();

            Statement st = connection.createStatement();
            String query = "SELECT * FROM CARSERVICE WHERE TYPE= 'R' OR TYPE='MR'";
            ResultSet rs = st.executeQuery(query);
            ArrayList<CarService> carServices = new ArrayList<>();
            while(rs.next()){
                carServices.add(new CarService(rs.getInt("S_ID"), rs.getString("NAME"), TypeEnum.valueOf(rs.getString("TYPE"))));
            }
            ConnectionDB.closeConnection(connection);
            return carServices;
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return new ArrayList<CarService>();
        }
    }

    public static String setupRepairPrice(User user, Integer s_id, Integer hondaPrice, Integer infinitiPrice, Integer lexusPrice, Integer nissanPrice, Integer toyotaPrice) {
        try {
            Connection connection = ConnectionDB.getConnection();
            Integer sc_id = user.getSc_id();
            Statement st1 = connection.createStatement();
            String query1 = "UPDATE OFFEREDPRICE SET PRICE=" + hondaPrice + " WHERE BRAND= 'HONDA' AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st1.executeUpdate(query1);

            Statement st2 = connection.createStatement();
            String query2 = "UPDATE OFFEREDPRICE SET PRICE=" + lexusPrice + " WHERE BRAND= 'LEXUS' AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st2.executeUpdate(query2);

            Statement st3 = connection.createStatement();
            String query3 = "UPDATE OFFEREDPRICE SET PRICE=" + infinitiPrice + " WHERE BRAND= 'INFINITI' AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st3.executeUpdate(query3);

            Statement st4 = connection.createStatement();
            String query4 = "UPDATE OFFEREDPRICE SET PRICE=" + nissanPrice + " WHERE BRAND= 'NISSAN' AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st4.executeUpdate(query4);

            Statement st5 = connection.createStatement();
            String query5 = "UPDATE OFFEREDPRICE SET PRICE=" + toyotaPrice + " WHERE BRAND= 'TOYOTA' AND SC_ID=" + sc_id + " AND S_ID=" + s_id;
            st5.executeUpdate(query5);

            ConnectionDB.closeConnection(connection);
            return "Success";
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return "Failed";
        }
    }

    public static String setHourlyRate(User user, Integer hourlyRate) {
        try {
            Connection connection = ConnectionDB.getConnection();

            Statement st = connection.createStatement();
            String query = "UPDATE SERVICECENTER SET HOURLY_WAGE=" + hourlyRate + " WHERE SC_ID=" + user.getSc_id();
            st.executeUpdate(query);
            ConnectionDB.closeConnection(connection);
            return "Success";
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return "Failed";
        }
    }
}
