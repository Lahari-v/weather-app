package frames;

import model.User;

import javax.swing.*;
import java.awt.*;

public class ChangeDefaultLocationFrame extends JFrame {
    private User user;

    // Constructor accepts a User object
    public ChangeDefaultLocationFrame(User user) {
        this.user = user;

        setTitle("Change Default Location");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);

        // Set up panel with GridBagLayout for better alignment
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Location label
        JLabel lblLocation = new JLabel("Enter new default location:");
        lblLocation.setFont(new Font("Arial", Font.BOLD, 14));

        // Location text field
        JTextField txtLocation = new JTextField(20);
        txtLocation.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        txtLocation.setFont(new Font("Arial", Font.PLAIN, 14));

        // Save button
        JButton btnSave = new JButton("Save");
        btnSave.setBackground(new Color(34, 139, 34));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Arial", Font.BOLD, 14));
        btnSave.setFocusPainted(false);
        btnSave.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 100, 30)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        // Action listener for the save button
        btnSave.addActionListener(e -> {
            String newLocation = txtLocation.getText().trim();
            if (newLocation.isEmpty()) {
                JOptionPane.showMessageDialog(
                        ChangeDefaultLocationFrame.this,
                        "Location cannot be empty!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                boolean success = user.updateDefaultLocation(newLocation); // Using the User object to update location
                if (success) {
                    JOptionPane.showMessageDialog(
                            ChangeDefaultLocationFrame.this,
                            "Default location updated successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(
                            ChangeDefaultLocationFrame.this,
                            "Failed to update location.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // Add components to panel with layout constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblLocation, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(txtLocation, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(btnSave, gbc);

        add(panel);
    }
}
