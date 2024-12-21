package model;

import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a User in the system with basic operations for authentication and profile management.
 */
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String defaultLocation; // Field for default location

    // Constructor for new User (registration)
    public User(String username, String email, String password, String defaultLocation) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.defaultLocation = defaultLocation;
    }

    // Constructor for existing User with ID (login)
    public User(int id, String username, String email, String password, String defaultLocation) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.defaultLocation = defaultLocation;
    }

    public User(int i, String johnDoe) {
    }

    public User() {

    }

    /**
     * Registers the user in the database.
     *
     * @return true if registration is successful, false otherwise.
     */
    public boolean register() {
        String query = "INSERT INTO users (username, email, password, default_location) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, this.username);
            stmt.setString(2, this.email);
            stmt.setString(3, this.password); // Storing password as plain text (not secure)
            stmt.setString(4, this.defaultLocation);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error during user registration: " + e.getMessage());
            return false;
        }
    }

    /**
     * Logs in the user by checking the username and password in the database.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @return User object if login is successful, null otherwise.
     */
    public static User login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String defaultLocation = rs.getString("default_location");
                return new User(id, username, email, password, defaultLocation);
            }

        } catch (SQLException e) {
            System.err.println("Error during user login: " + e.getMessage());
        }
        return null;
    }

    /**
     * Updates the user's default location in the database.
     *
     * @param defaultLocation The new default location.
     * @return true if update is successful, false otherwise.
     */
    public boolean updateDefaultLocation(String defaultLocation) {
        String query = "UPDATE users SET default_location = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, defaultLocation);
            stmt.setInt(2, this.id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                this.defaultLocation = defaultLocation; // Update the object if DB update is successful
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error updating default location: " + e.getMessage());
        }
        return false;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    // Setter methods
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password; // Storing plain text password (not secure)
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }
}
