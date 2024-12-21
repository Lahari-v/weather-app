package frames;

import model.User;
import org.json.JSONObject;
import utils.StyleUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherCheckFrame extends JFrame {
    public JTextField locationField;
    public JButton checkWeatherButton;
    public JTextArea weatherDetailsArea;

    private final String API_KEY = "087d1d2feb71d6a8b5e887ca8011b88a";  // Replace with your OpenWeatherMap API key

    public WeatherCheckFrame(User user) {
        setTitle("Check Weather");
        StyleUtils.setWindowTo75Percent(this);  // Set size to 75% and center

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Setting up panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(230, 240, 250));  // Updated background color
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Label for location input
        JLabel locationLabel = new JLabel("Enter Location:");
        locationLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Input field and button
        locationField = new JTextField(30);
        StyleUtils.styleTextField(locationField);  // Apply rounded and hover effect

        checkWeatherButton = new JButton("Check Weather");
        StyleUtils.styleButton(checkWeatherButton, new Color(70, 130, 180));  // Apply rounded and hover effect

        // Weather details display area with rounded edges
        weatherDetailsArea = new JTextArea(12, 40);
        weatherDetailsArea.setEditable(false);
        weatherDetailsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        weatherDetailsArea.setLineWrap(true);
        weatherDetailsArea.setWrapStyleWord(true);
        weatherDetailsArea.setBackground(new Color(126, 166, 225));
        weatherDetailsArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(199, 219, 241), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        JScrollPane scrollPane = new JScrollPane(weatherDetailsArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());  // No border for scroll pane

        // Adding components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(locationLabel, gbc);

        gbc.gridx = 1;
        panel.add(locationField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(checkWeatherButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        add(panel);

        // Event listener for the check weather button
        checkWeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String location = locationField.getText();
                if (!location.isEmpty()) {
                    String weatherDetails = getWeatherDetails(location);
                    weatherDetailsArea.setText(weatherDetails);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a location.");
                }
            }
        });
    }

    // Fetch weather data from OpenWeatherMap API
    private String getWeatherDetails(String location) {
        try {
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + API_KEY + "&units=metric";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());

            // Extract weather details
            String weatherDescription = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");
            double temperature = jsonResponse.getJSONObject("main").getDouble("temp");
            int humidity = jsonResponse.getJSONObject("main").getInt("humidity");

            return String.format("Weather details for %s:\nTemperature: %.2f°C\nHumidity: %d%%\nDescription: %s",
                    location, temperature, humidity, weatherDescription);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching weather data. Please try again.";
        }
    }
}
