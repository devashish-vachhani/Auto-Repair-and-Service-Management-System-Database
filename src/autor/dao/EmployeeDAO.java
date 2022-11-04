package dao;

import config.ConnectionDB;
import models.Employee;
import models.EmployeeRoleEnum;

import java.sql.*;

public class EmployeeDAO {

    // Verify if the username and password is in the Employee table.
    // Returns a user of Employee type if found, else return null
    public static Employee verifyUser(String username, String password) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String query = "select * from Employee where name='" + username + "' and password='" + password + "'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            Integer count = 0;
            Employee employee = null;
            while(rs.next()){
                employee = new Employee(rs.getString("name"),  EmployeeRoleEnum.valueOf(rs.getString("role")));
                count ++;
            }
            if(count != 1)
                employee = null;
            ConnectionDB.closeConnection(connection);
            return employee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
