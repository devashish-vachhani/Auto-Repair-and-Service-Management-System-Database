package dao;

import config.ConnectionDB;
import models.ServiceCenter;
import models.User;
import models.UserRoleEnum;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDAO {
    public static void addService() {
    }

//    public static ServiceCenter addStore(String address, String tel, String state, String openSaturdays, String hourlyRate) {
//        try {
//            Connection connection = ConnectionDB.getConnection();
//            String query = "insert into ServiceCenters(address, tel, state_center, openSaturdays, hourlyRate) values(" + address + "," + tel + "," + state + "," + openSaturdays + "," + hourlyRate + ");";
//            Statement st = connection.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            Integer count = 0;
//            ServiceCenter serviceCenter = null;
//            while(rs.next()){
//                serviceCenter = new ServiceCenter(rs.getString("address"), rs.getString("tel"), rs.getString("state_center"), rs.getBoolean("openSaturdays"),rs.getInt("hourlyRate"));
//                count ++;
//            }
//            if(count != 1)
//                serviceCenter = null;
//            ConnectionDB.closeConnection(connection);
//            return serviceCenter;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void executeServiceGeneralInfoQuery(String serviceGeneralInfoQuery) {
    }

    public static void executeStoreGeneralInfoQuery(String storeGeneralInfoQuery) {
    }

    public static void addStore() {
    }
}
