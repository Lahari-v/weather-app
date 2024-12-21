package model;

import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteLocation {
    private int id;
    private int userId;
    private String location;

    public FavoriteLocation(int userId, String location) {
        this.userId = userId;
        this.location = location;
    }

    // Method to add a favorite location for the user with duplicate check
    public boolean addFavorite() {
        // First, check if the location already exists for this user
        if (isLocationAlreadyFavorite()) {
            System.out.println("Location already added to favorites.");
            return false;  // Location already in favorites
        }

        String query = "INSERT INTO favorites (user_id, location) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.userId);
            stmt.setString(2, this.location);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check if the location is already in the user's favorites
    private boolean isLocationAlreadyFavorite() {
        String query = "SELECT 1 FROM favorites WHERE user_id = ? AND location = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.userId);
            stmt.setString(2, this.location);
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // Returns true if the location exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a favorite location for the user
    public boolean deleteFavorite() {
        String query = "DELETE FROM favorites WHERE user_id = ? AND location = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.userId);
            stmt.setString(2, this.location);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Static method to get a list of favorite locations by user ID
    public static List<FavoriteLocation> getFavoritesByUserId(int userId) {
        List<FavoriteLocation> favorites = new ArrayList<>();
        String query = "SELECT * FROM favorites WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FavoriteLocation favorite = new FavoriteLocation(userId, rs.getString("location"));
                favorite.id = rs.getInt("id");
                favorites.add(favorite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favorites;
    }

    // Getter for location
    public String getLocation() {
        return location;
    }

    // Getter for ID (optional)
    public int getId() {
        return id;
    }
}
