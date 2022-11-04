package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    // Initialize connection
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        String jdbcUrl = ConnectionConstants.getJDBCUrl();
        String user = ConnectionConstants.getUser();
        String password = ConnectionConstants.getPassword();
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("Connection started");
        } catch (SQLException e) {
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage() ) ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Close connection
    public static void closeConnection(Connection conn) {
        System.out.println("Connection closed");
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }
}
