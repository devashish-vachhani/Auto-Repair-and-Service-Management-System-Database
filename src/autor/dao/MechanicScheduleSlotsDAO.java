package dao;

import config.ConnectionDB;
import models.MechanicScheduleSlots;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MechanicScheduleSlotsDAO {
    public static ArrayList<MechanicScheduleSlots> viewAvailableMechanicSlots(Integer ScId, Integer requiredServiceHours) {
        try {
            Connection connection = ConnectionDB.getConnection();
            ArrayList<MechanicScheduleSlots> mechanicScheduleSlots = new ArrayList<>();
            for(int week=1; week<=4; week++) {
                String query = "SELECT S.WEEK, S.SLOT_DAY, MS.MECH_ID, MIN(S.SLOT_ID) AS SLOT_ID_MIN, MIN(S.SLOTS) AS SLOTS_MIN FROM MECHANICSCHEDULE MS, SLOTS S WHERE MS.AVAILABLE='AVAILABLE' AND MS.SC_ID = " + ScId + " AND MS.SLOT_ID = S.SLOT_ID AND S.WEEK = " + week + " AND MS.MECH_ID NOT IN (SELECT MS2.MECH_ID FROM MECHANICSCHEDULE MS2, SLOTS S2 WHERE MS2.SC_ID = MS.SC_ID AND S2.WEEK = S.WEEK AND MS2.SLOT_ID = S2.SLOT_ID AND MS2.AVAILABLE='WORKING' GROUP BY MS2.MECH_ID HAVING COUNT(*) >= 50 - " + requiredServiceHours + ") GROUP BY S.WEEK, S.SLOT_DAY, MS.MECH_ID HAVING COUNT(*) >= " + requiredServiceHours;
                System.out.println(query);
                Statement st = connection.createStatement();
                ResultSet rs =  st.executeQuery(query);
                while (rs.next()) {
                    MechanicScheduleSlots mechanicScheduleSlot = new MechanicScheduleSlots(Integer.valueOf(rs.getInt("WEEK")), Integer.valueOf(rs.getInt("SLOT_DAY")), Long.valueOf(rs.getLong("MECH_ID")), Integer.valueOf(rs.getInt("SLOT_ID_MIN")), Integer.valueOf(rs.getInt("SLOTS_MIN")));
                    mechanicScheduleSlots.add(mechanicScheduleSlot);
                }
            }
            ConnectionDB.closeConnection(connection);
            return mechanicScheduleSlots;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
