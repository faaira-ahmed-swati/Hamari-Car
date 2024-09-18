package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
//connection class for msql
    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/se_proj", "faaira", "Password123#@!");
        return connection;
    }
}
