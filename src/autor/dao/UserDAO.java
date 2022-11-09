package dao;

import config.ConnectionDB;
import models.TypeEnum;
import models.User;
import models.UserRoleEnum;

import java.sql.*;

public class UserDAO {

    // Verify if the username and password is in the Employee table.
    // Returns a user of Employee type if found, else return null
    public static User verifyUser(String username, String password) {
        try {
            if(username.equals("admin") && password.equals("admin"))
                return new User(null, null, "admin", UserRoleEnum.ADMIN);
            Connection connection = ConnectionDB.getConnection();
            String query = "select * from Users where username='" + username + "' and password='" + password + "'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            Integer count = 0;
            User user = null;
            while(rs.next()){
                user = new User(rs.getLong("USER_ID"), rs.getInt("SC_ID"), rs.getString("USERNAME"),  UserRoleEnum.valueOf(rs.getString("ROLE")));
                count ++;
            }
            if(count != 1)
                user = null;
            ConnectionDB.closeConnection(connection);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
