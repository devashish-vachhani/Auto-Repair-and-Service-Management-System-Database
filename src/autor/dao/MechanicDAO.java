package dao;

import config.ConnectionDB;
import models.AvailableMechanicsAndSlots;
import models.AvailableSwapRequests;
import models.BookedMechanic;
import models.User;

import java.sql.*;
import java.util.ArrayList;

public class MechanicDAO {
    public static ArrayList<BookedMechanic> viewSchedule(User user) {
        Integer sc_id = user.getSc_id();
        Long mech_id = user.getUserid();
        try {
            Connection connection = ConnectionDB.getConnection();

            Statement st = connection.createStatement();
            String query = "SELECT S.WEEK, S.SLOT_DAY, S.SLOTS FROM MECHANICSCHEDULE MS, SLOTS S WHERE S.SLOT_ID = MS.SLOT_ID AND MS.MECH_ID=" + mech_id + " AND MS.SC_ID=" + sc_id + " AND MS.AVAILABLE = 'WORKING'";
            ResultSet rs = st.executeQuery(query);
            ArrayList<BookedMechanic> bookedMechanicArrayList = new ArrayList<>();
            while(rs.next()){
                bookedMechanicArrayList.add(new BookedMechanic(rs.getInt("WEEK"), rs.getInt("SLOT_DAY"), rs.getInt("SLOTS")));
            }
            ConnectionDB.closeConnection(connection);
            return bookedMechanicArrayList;
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return new ArrayList<BookedMechanic>();
        }
    }

//    public static void sendRequestTimeOff(ArrayList<Object> inputList) {
//
//    }


    public static ArrayList<AvailableMechanicsAndSlots> sendRequestTimeOffRetrievalQuery(User user) {
        Integer sc_id = user.getSc_id();
        Long mech_id = user.getUserid();
        try {
            Connection connection = ConnectionDB.getConnection();

            Statement st = connection.createStatement();
            String query = "SELECT S.WEEK, S.SLOT_DAY, S.SLOTS, S.SLOT_ID FROM MECHANICSCHEDULE MS, SLOTS S WHERE S.SLOT_ID = MS.SLOT_ID AND MS.MECH_ID=" + mech_id + " AND MS.SC_ID=" + sc_id + " AND MS.AVAILABLE = 'AVAILABLE'";
            ResultSet rs = st.executeQuery(query);
            ArrayList<AvailableMechanicsAndSlots> availableMechanicsAndSlotsArrayList = new ArrayList<>();
            while(rs.next()){
                availableMechanicsAndSlotsArrayList.add(new AvailableMechanicsAndSlots(rs.getInt("SLOT_ID"), rs.getInt("WEEK"), rs.getInt("SLOT_DAY"), rs.getInt("SLOTS")));
            }
            ConnectionDB.closeConnection(connection);
            return availableMechanicsAndSlotsArrayList;
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static String sendSwap(Long inputMech_id, User user, Integer inputSlotId1, Integer inputSlotId2) {
        try {
            Connection connection = ConnectionDB.getConnection();

            Integer sc_id = user.getSc_id();
            Long mech_id = user.getUserid();
            Statement st1 = connection.createStatement();
            String query1 = "SELECT MS_ID FROM MECHANICSCHEDULE WHERE SLOT_ID =" + inputSlotId1 + " AND MECH_ID=" + mech_id + " AND SC_ID=" + sc_id;
            ResultSet rs1 = st1.executeQuery((query1));
            Long ms_id1 = null;
            while (rs1.next()) {
                ms_id1 = rs1.getLong("MS_ID");
            }

            Statement st2 = connection.createStatement();
            String query2 = "SELECT MS_ID FROM MECHANICSCHEDULE WHERE SLOT_ID =" + inputSlotId2 + " AND MECH_ID=" + inputMech_id + " AND SC_ID=" + sc_id;
            ResultSet rs2 = st1.executeQuery((query2));
            Long ms_id2 = null;
            while (rs2.next()) {
                ms_id2 = rs2.getLong("MS_ID");
            }

            PreparedStatement ps = connection.prepareStatement("INSERT INTO MECHANICSCHEDULESWAP(MS_ID1,MS_ID2,APPROVED) VALUES(?,?,?)");
            ps.setLong(1, ms_id1);
            ps.setLong(2, ms_id2);
            ps.setInt(3, 0);
            Integer rs3 = ps.executeUpdate();

            ConnectionDB.closeConnection(connection);
            return "Request Created";
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return "Failed";
        }
    }

    public static String updateSwap(Long requestId, Integer approved) {
        try{
            Connection connection = ConnectionDB.getConnection();

            Statement st = connection.createStatement();
            String query = "UPDATE MECHANICSCHEDULESWAP SET APPROVED=" + approved + " WHERE REGISTER_ID=" + requestId;
            st.executeUpdate(query);

            ConnectionDB.closeConnection(connection);
            return "Request Status Updated";
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return "Failed";
        }
    }

    public static String sendRequestTimeOffCountQuery(User user, Integer inputSlotId) {
        Integer sc_id = user.getSc_id();
        Long mech_id = user.getUserid();
        try {
            Connection connection = ConnectionDB.getConnection();

            Statement st = connection.createStatement();
            String query = "SELECT COUNT(*) AS COUNT_EMP FROM MECHANICSCHEDULE WHERE SLOT_ID = " + inputSlotId + " AND SC_ID = " + sc_id + " AND MECH_ID !=" + mech_id + " AND AVAILABLE = 'AVAILABLE'";
            ResultSet rs = st.executeQuery(query);
            Integer count = null;
            while(rs.next()) {
                count = rs.getInt("COUNT_EMP");
            }
            ConnectionDB.closeConnection(connection);
            if(count < 3){
                return "These slots are not available for time off";
            }
            String s = sendRequestTimeOffUpdateQuery(user, inputSlotId);
            return s;
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return "Failed";
        }
    }

    private static String sendRequestTimeOffUpdateQuery(User user, Integer inputSlotId) {
        Integer sc_id = user.getSc_id();
        Long mech_id = user.getUserid();
        try {
            Connection connection = ConnectionDB.getConnection();

            Statement st = connection.createStatement();
            String query = "UPDATE MECHANICSCHEDULE SET AVAILABLE='TIMEOFF' WHERE MECH_ID = " + mech_id + " AND SC_ID = " + sc_id + " AND SLOT_ID = " + inputSlotId;
            Integer res1 = st.executeUpdate(query);
            ConnectionDB.closeConnection(connection);
            if(res1 == 0){
                return "Timeoff Allotted Successfully";
            }
            return "These slots are not available for time off";
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return "Failed";
        }
    }

    public static ArrayList<AvailableMechanicsAndSlots> requestSwapRetrievalQuery(Integer sc_id, Long mech_id, Integer week, Integer day) {
        try {
            Connection connection = ConnectionDB.getConnection();

            Statement st = connection.createStatement();
            String query = "SELECT S.WEEK, S.SLOT_DAY, S.SLOTS, S.SLOT_ID FROM MECHANICSCHEDULE MS, SLOTS S WHERE S.SLOT_ID = MS.SLOT_ID AND MS.MECH_ID=" + mech_id + " AND MS.SC_ID=" + sc_id + " AND MS.AVAILABLE = 'AVAILABLE'";
            ResultSet rs = st.executeQuery(query);
            ArrayList<AvailableMechanicsAndSlots> availableMechanicsAndSlotsArrayList = new ArrayList<>();
            while(rs.next()){
                availableMechanicsAndSlotsArrayList.add(new AvailableMechanicsAndSlots(rs.getInt("SLOT_ID"), rs.getInt("WEEK"), rs.getInt("SLOT_DAY"), rs.getInt("SLOTS")));
            }
            ConnectionDB.closeConnection(connection);
            return availableMechanicsAndSlotsArrayList;
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return new ArrayList<>();
        }

    }

    public static Integer requestSwapCheckQuery(Integer sc_id, Long inputMechId) {
        try {
            Connection connection = ConnectionDB.getConnection();

            Statement st = connection.createStatement();
            String query = "SELECT COUNT(*) AS COUNT_MECH FROM MECHANIC WHERE USER_ID=" + inputMechId + " AND SC_ID=" + sc_id;
            ResultSet rs = st.executeQuery(query);

            Integer count = null;
            while(rs.next()) {
                count = rs.getInt("COUNT_MECH");
            }
            ConnectionDB.closeConnection(connection);
            return count;
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return null;
        }
    }

    public static ArrayList<AvailableSwapRequests> requestSwapInfo(User user) {
        Integer sc_id = user.getSc_id();
        Long mech_id = user.getUserid();
        try {
            Connection connection = ConnectionDB.getConnection();

            Statement st = connection.createStatement();
            String query = "SELECT MSS.REGISTER_ID, E.NAME, MS2.SLOT_ID  FROM MECHANICSCHEDULESWAP MSS, MECHANICSCHEDULE MS1, MECHANICSCHEDULE MS2, EMPLOYEE E WHERE MSS.MS_ID1 = MS1.MS_ID AND MSS.MS_ID2 = MS2.MS_ID AND MS2.MECH_ID=" + mech_id + " AND MS2.SC_ID=" + sc_id + " AND MSS.APPROVED = 0 AND MS1.MECH_ID = E.USER_ID AND E.SC_ID =" + sc_id;
            ResultSet rs = st.executeQuery(query);
            ArrayList<AvailableSwapRequests> availableSwapRequestsArrayList = new ArrayList<>();
            while(rs.next()){
                availableSwapRequestsArrayList.add(new AvailableSwapRequests(rs.getLong("REGISTER_ID"), rs.getString("NAME"), rs.getLong("SLOT_ID")));
            }
            ConnectionDB.closeConnection(connection);
            return availableSwapRequestsArrayList;
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return new ArrayList<>();
        }
    }
}
