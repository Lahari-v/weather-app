package test;

import config.DatabaseConnection;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseConnectionTest {

    @Test
    public void testGetConnection() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        assertNotNull(connection);
        connection.close();
    }
}