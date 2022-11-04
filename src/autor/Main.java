import logic.landing.Landing;

public class Main {
//    static final String jdbcURL
//            = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";

    public static void main(String[] args) {
        System.out.println("Welcome to AUTOR!!");
        Landing.homeMenu();




//        try {
//
//// Load the driver. This creates an instance of the driver
//// and calls the registerDriver method to make Oracle Thin
//// driver available to clients.
//
////  Class.forName("oracle.jdbc.driver.OracleDriver"); older
//
//
////            Class.forName("oracle.jdbc.OracleDriver");
//
////            String user = "rshukla3";	// For example, "jsmith"
////            String passwd = "200421376";	// Your 9 digit student ID number
//
//
//            Connection conn = null;
//            Statement stmt = null;
//            ResultSet rs = null;
//
//            try {
//
//                // Get a connection from the first driver in the
//                // DriverManager list that recognizes the URL jdbcURL
//
////                conn = DriverManager.getConnection(jdbcURL, user, passwd);
//                conn = connection.getConnection();
//                // Create a statement object that will be sending your
//                // SQL statements to the DBMS
//
//                stmt = conn.createStatement();
//
////                stmt.executeUpdate("ALTER TABLE TABLE1");
//
//                stmt.executeUpdate("INSERT INTO TABLE1 " +
//                        "VALUES ('Colombian', 'rishabh')");
//
//            } catch(java.sql.SQLException e) {
//                System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " +
//
//                        e.getMessage() ) ;
//            }
//            finally {
//                System.out.println("Rishabh Agarwal completed secondexample successfully!!");
//                close(rs);
//                close(stmt);
//                connection.closeConnection(conn);
//            }
//        } catch(Throwable oops) {
//            oops.printStackTrace();
//        }
//    }
//
//    static void close(Connection conn) {
//        if(conn != null) {
//            try { conn.close(); } catch(Throwable whatever) {}
//        }
//    }
//
//    static void close(Statement st) {
//        if(st != null) {
//            try { st.close(); } catch(Throwable whatever) {}
//        }
//    }
//
//    static void close(ResultSet rs) {
//        if(rs != null) {
//            try { rs.close(); } catch(Throwable whatever) {}
//        }
    }
}