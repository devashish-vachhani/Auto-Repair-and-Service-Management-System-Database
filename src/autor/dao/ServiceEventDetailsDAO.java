package dao;

import config.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class ServiceEventDetailsDAO {
    public static boolean insertServiceEventDetails(Integer SeId, Set<Integer> SIds) {
        try {
            Connection connection = ConnectionDB.getConnection();
            for(Integer SId: SIds) {
                String query = "INSERT INTO SERVICEEVENTDETAILS(SE_ID, S_ID) values (?,?)";
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setInt(1, SeId);
                pst.setInt(2, SId);
                pst.executeUpdate();
            }
            ConnectionDB.closeConnection(connection);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
