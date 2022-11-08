package dao;

import config.ConnectionDB;
import logic.admin.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDAO {

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

    public static String addStore(Integer serviceCenterId, String address, Long telephone, String state, Integer openSaturdays, Integer mechMaxWage, Integer mechMinWage, Integer mechHourlyWage, String mgrFirstName, String mgrLastName, String mgrUsername, String mgrPassword, Integer mgrSalary, Long mgrId, String mgrEmail, Long mgrPhone) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String mgrFullName = mgrFirstName + " " + mgrLastName;
            PreparedStatement pst1 = connection.prepareStatement("insert into servicecenter(SC_ID, ADDRESS, TELEPHONE_NO, STATE, OPEN_SAT, MIN_WAGE, MAX_WAGE, HOURLY_WAGE) values(?,?,?,?,?,?,?,?)");
            pst1.setInt(1, serviceCenterId);
            pst1.setString(2, address);
            pst1.setLong(3, telephone);
            pst1.setString(4, state);
            pst1.setInt(5, openSaturdays);
            pst1.setInt(6, mechMinWage);
            pst1.setInt(7, mechMaxWage);
            pst1.setInt(8, mechHourlyWage);
            Integer res1 = pst1.executeUpdate();

            PreparedStatement pst2 = connection.prepareStatement("insert into users(USER_ID, SC_ID, USERNAME, PASSWORD, ROLE) values(?,?,?,?,?)");
            pst2.setLong(1, mgrId);
            pst2.setInt(2, serviceCenterId);
            pst2.setString(3, mgrUsername);
            pst2.setString(4, mgrPassword);
            pst2.setString(5, "MANAGER");
            Integer res2 = pst2.executeUpdate();

            PreparedStatement pst3 = connection.prepareStatement("insert into employee(USER_ID, NAME, ADDRESS, EMAIL, PHONE_NO, ROLE, SC_ID) values(?,?,?,?,?,?,?)");
            pst3.setLong(1, mgrId);
            pst3.setString(2, mgrFullName);
            pst3.setString(3, address);
            pst3.setString(4, mgrEmail);
            pst3.setLong(5, mgrPhone);
            pst3.setString(6, "MANAGER");
            pst3.setInt(7, serviceCenterId);
            Integer res3 = pst3.executeUpdate();

            PreparedStatement pst4 = connection.prepareStatement("insert into manager(USER_ID, ANNUAL_SALARY, SC_ID) values(?,?,?)");
            pst4.setLong(1, mgrId);
            pst4.setInt(2, mgrSalary);
            pst4.setInt(3, serviceCenterId);
            Integer res4 = pst4.executeUpdate();

            ConnectionDB.closeConnection(connection);
            return "Success";
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return "Failed";
        }
    }

    public static String addService(Integer serviceId, String serviceType, String serviceBundle, String serviceCategory, String serviceName, Integer hondaDuration, Integer lexusDuration, Integer infinityDuration, Integer nissanDuration, Integer toyotaDuration) {
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement pst1 = connection.prepareStatement("insert into CARSERVICE(S_ID, NAME, TYPE) values(?,?,?)");
            pst1.setInt(1, serviceId);
            pst1.setString(2, serviceName);
            pst1.setString(3, serviceType);
            Integer res1 = pst1.executeUpdate();

            if(serviceType.contains("MAINTENANCE")){
                PreparedStatement pst2 = connection.prepareStatement("insert into MAINTAINANCE(S_ID, BUNDLE) values(?,?)");
                pst2.setInt(1, serviceId);
                pst2.setString(2, serviceBundle);
                Integer res2 = pst2.executeUpdate();
            }
            if(serviceType.contains("REPAIR")){
                PreparedStatement pst3 = connection.prepareStatement("insert into REPAIR(S_ID, CATEGORY) values(?,?)");
                pst3.setInt(1, serviceId);
                pst3.setString(2, serviceCategory);
                Integer res3 = pst3.executeUpdate();
            }

            PreparedStatement pst4 = connection.prepareStatement("insert into OFFEREDTIME(S_ID, BRAND, HRS) values(?,?,?)");
            pst4.setInt(1, serviceId);
            pst4.setString(2, "HONDA");
            pst4.setInt(3, hondaDuration);
            Integer res4 = pst4.executeUpdate();

            PreparedStatement pst5 = connection.prepareStatement("insert into OFFEREDTIME(S_ID, BRAND, HRS) values(?,?,?)");
            pst5.setInt(1, serviceId);
            pst5.setString(2, "LEXUS");
            pst5.setInt(3, lexusDuration);
            Integer res5 = pst5.executeUpdate();

            PreparedStatement pst6 = connection.prepareStatement("insert into OFFEREDTIME(S_ID, BRAND, HRS) values(?,?,?)");
            pst6.setInt(1, serviceId);
            pst6.setString(2, "INFINITI");
            pst6.setInt(3, infinityDuration);
            Integer res6 = pst6.executeUpdate();

            PreparedStatement pst7 = connection.prepareStatement("insert into OFFEREDTIME(S_ID, BRAND, HRS) values(?,?,?)");
            pst7.setInt(1, serviceId);
            pst7.setString(2, "NISSAN");
            pst7.setInt(3, nissanDuration);
            Integer res7 = pst7.executeUpdate();

            PreparedStatement pst8 = connection.prepareStatement("insert into OFFEREDTIME(S_ID, BRAND, HRS) values(?,?,?)");
            pst8.setInt(1, serviceId);
            pst8.setString(2, "TOYOTA");
            pst8.setInt(3, toyotaDuration);
            Integer res8 = pst8.executeUpdate();

            ConnectionDB.closeConnection(connection);
            return "Success";
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception "+ e.getErrorCode() + "/" + e.getSQLState() + "/" + e.getMessage());
            return "Failed";
        }
    }
}
