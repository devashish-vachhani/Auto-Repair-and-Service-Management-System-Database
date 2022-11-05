package dao;

import config.ConnectionDB;
import models.User;
import models.UserRoleEnum;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    // Verify if the username and password is in the Employee table.
    // Returns a user of Employee type if found, else return null
    public static User verifyUser(String username, String password) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "select * from USERS where USERNAME='" + username + "' AND PASSWORD='" + password + "'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            Integer count = 0;
            User user = null;
            while(rs.next()){
                user = new User(rs.getInt("USERID"), UserRoleEnum.valueOf(rs.getString("ROLE")));
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
