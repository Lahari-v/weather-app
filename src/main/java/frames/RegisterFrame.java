package frames;

import model.User;
import utils.StyleUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {
    public JTextField usernameField;
    public JTextField emailField;
    public JPasswordField passwordField;
    public JTextField defaultLocationField;
    public JButton registerButton;

    public RegisterFrame() {
        setTitle("Register");
        setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().width * 0.75),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height * 0.75));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the frame

        // Panel setup with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(169, 235, 237));  // Light blue background
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Username field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(30);
        StyleUtils.styleTextField(usernameField);  // Apply rounded style
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        // Email field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);

        emailField = new JTextField(30);
        StyleUtils.styleTextField(emailField);  // Apply rounded style
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(emailField, gbc);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(30);
        StyleUtils.stylePasswordField(passwordField);  // Apply rounded style
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        // Default location field
        JLabel defaultLocationLabel = new JLabel("Default Location:");
        defaultLocationLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(defaultLocationLabel, gbc);

        defaultLocationField = new JTextField(30);
        StyleUtils.styleTextField(defaultLocationField);  // Apply rounded style
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(defaultLocationField, gbc);

        // Register button
        registerButton = new JButton("Register");
        StyleUtils.styleButton(registerButton, new Color(45, 100, 200));  // Blue color with hover effect
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, gbc);

        add(panel);

        // Register button action listener
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String defaultLocation = defaultLocationField.getText();

                User user = new User(username, email, password, defaultLocation);
                if (user.register()) {
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    new LoginFrame().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Registration failed.");
                }
            }
        });
    }
}
