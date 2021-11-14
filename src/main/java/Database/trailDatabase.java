package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class trailDatabase {
    public static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException
    {
        // Initialize all the information regarding
        // Database Connection
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        // Database name to access
        String dbName = "trail";
        String dbUsername = "root";
        String dbPassword = "Arvind@2000";

        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL + dbName,
                dbUsername,
                dbPassword);
        return con;
    }
}
