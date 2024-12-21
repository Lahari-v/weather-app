package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/weather_app";
        String user = "root";
        String password = "Lahari@321"; // replace with your database password
        return DriverManager.getConnection(url, user, password);
    }

}
