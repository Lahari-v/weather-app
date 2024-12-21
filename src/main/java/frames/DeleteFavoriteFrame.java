//package frames;
//
//import model.FavoriteLocation;
//import model.User;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class DeleteFavoriteFrame extends JFrame {
//    public JTextField locationField;
//    public JButton deleteFavoriteButton;
//    private User user;
//
//    public DeleteFavoriteFrame(User user) {
//        this.user = user;
//
//        setTitle("Delete Favorite Location");
//        setSize(350, 180);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // Setting up the panel with GridBagLayout for better alignment
//        JPanel panel = new JPanel(new GridBagLayout());
//        panel.setBackground(new Color(245, 245, 245));
//        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.insets = new Insets(10, 10, 10, 10);
//
//        // Location label
//        JLabel locationLabel = new JLabel("Location:");
//        locationLabel.setFont(new Font("Arial", Font.BOLD, 14));
//
//        // Location text field
//        locationField = new JTextField(20);
//        locationField.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(new Color(200, 200, 200)),
//                BorderFactory.createEmptyBorder(5, 5, 5, 5)
//        ));
//        locationField.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        // Delete button styling
//        deleteFavoriteButton = new JButton("Delete from Favorites");
//        deleteFavoriteButton.setBackground(new Color(220, 20, 60));
//        deleteFavoriteButton.setForeground(Color.WHITE);
//        deleteFavoriteButton.setFont(new Font("Arial", Font.BOLD, 14));
//        deleteFavoriteButton.setFocusPainted(false);
//        deleteFavoriteButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
//
//        // Adding components to panel with GridBag constraints
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        panel.add(locationLabel, gbc);
//
//        gbc.gridx = 1;
//        panel.add(locationField, gbc);
//
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        gbc.anchor = GridBagConstraints.CENTER;
//        panel.add(deleteFavoriteButton, gbc);
//
//        add(panel);
//
//        // Delete button action listener
//        deleteFavoriteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String location = locationField.getText();
//                if (!location.isEmpty()) {
//                    // Creating a new FavoriteLocation instance with the user ID and location
//                    FavoriteLocation favoriteLocation = new FavoriteLocation(user.getId(), location);
//
//                    // Attempt to delete the location from the user's favorites
//                    if (favoriteLocation.deleteFavorite()) {
//                        JOptionPane.showMessageDialog(null, "Location deleted from favorites!");
//                        locationField.setText("");  // Clear the text field after deletion
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Failed to delete location. Make sure it exists in favorites.");
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "Please enter a location.");
//                }
//            }
//        });
//    }
//}

package frames;

import model.FavoriteLocation;
import model.User;

import javax.swing.*;
import java.awt.*;

public class DeleteFavoriteFrame extends JFrame {
    private JTextField locationField;
    private JButton deleteFavoriteButton;
    private User user;

    public DeleteFavoriteFrame(User user) {
        this.user = user;

        setTitle("Delete Favorite Location");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setting up the panel with GridBagLayout for better alignment
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Location label
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Location text field
        locationField = new JTextField(20);
        locationField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        locationField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Delete button styling
        deleteFavoriteButton = new JButton("Delete from Favorites");
        deleteFavoriteButton.setBackground(new Color(220, 20, 60));
        deleteFavoriteButton.setForeground(Color.WHITE);
        deleteFavoriteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteFavoriteButton.setFocusPainted(false);
        deleteFavoriteButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Adding components to panel with GridBag constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(locationLabel, gbc);

        gbc.gridx = 1;
        panel.add(locationField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(deleteFavoriteButton, gbc);

        add(panel);

        // Delete button action listener
        deleteFavoriteButton.addActionListener(e -> {
            String location = locationField.getText().trim();
            if (location.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a location.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // Create a new FavoriteLocation instance with the user ID and location
            FavoriteLocation favoriteLocation = new FavoriteLocation(user.getId(), location);

            // Attempt to delete the location from the user's favorites
            if (favoriteLocation.deleteFavorite()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Location deleted from favorites!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                locationField.setText("");  // Clear the text field after deletion
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Location not found in your favorites. Please check the name and try again.",
                        "Deletion Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
