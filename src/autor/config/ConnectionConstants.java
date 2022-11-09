package config;

public class ConnectionConstants {
    public static String getJDBCUrl()
    {
        return "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
    }
    public static String getUser()
    {
        return "dvachha";
    }
    public static String getPassword()
    {
        return "abcd1234";
    }
}
