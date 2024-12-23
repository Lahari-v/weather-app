package frames;

import model.FavoriteLocation;
import model.User;
import utils.StyleUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFavoriteFrame extends JFrame {
    public JTextField locationField;
    public JButton addFavoriteButton;
    private User user;

    public AddFavoriteFrame(User user) {
        this.user = user;

        setTitle("Add Favorite Location");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up panel with GridBagLayout
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
        locationField.setFont(new Font("Arial", Font.PLAIN, 14));
        locationField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // Add to favorites button
        addFavoriteButton = StyleUtils.createStyledButton("Add to Favorites", new Color(100, 149, 237));

        // Add components to panel with layout constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(locationLabel, gbc);

        gbc.gridx = 1;
        panel.add(locationField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(addFavoriteButton, gbc);

        add(panel);

        // Action listener for adding favorite location
        addFavoriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLocationToFavorites();
            }
        });
    }

    /**
     * Adds the location from the input field to the user's favorites.
     */
    private void addLocationToFavorites() {
        String location = locationField.getText().trim();

        if (location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a location.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        FavoriteLocation favoriteLocation = new FavoriteLocation(user.getId(), location);
        if (favoriteLocation.addFavorite()) {
            JOptionPane.showMessageDialog(this, "Location added to favorites!", "Success", JOptionPane.INFORMATION_MESSAGE);
            locationField.setText("");  // Clear the field after successful addition
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add location. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
