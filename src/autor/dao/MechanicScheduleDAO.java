package dao;

import config.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MechanicScheduleDAO {
    public static boolean updateMechanicSchedule(Integer SlotId, Long MechId, Integer ScId, Integer requiredServiceHours) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "UPDATE MECHANICSCHEDULE SET AVAILABLE=? WHERE SLOT_ID IN (SELECT * FROM (SELECT SLOT_ID FROM MECHANICSCHEDULE WHERE AVAILABLE='AVAILABLE' AND SLOT_ID >= " + SlotId + " AND MECH_ID = "+ MechId + " AND SC_ID = " + ScId + ") WHERE rownum <= " + requiredServiceHours + ") AND MECH_ID=" + MechId + " AND SC_ID= " + ScId;
            System.out.println(query);
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, "WORKING");
            pst.executeUpdate();
            ConnectionDB.closeConnection(connection);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
