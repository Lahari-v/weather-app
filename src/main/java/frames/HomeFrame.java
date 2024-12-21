package frames;

import model.User;
import org.json.JSONObject;
import utils.StyleUtils;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomeFrame extends JFrame {
    private JButton checkWeatherButton;
    private JButton addFavoriteButton;
    private JButton showFavoritesButton;
    private JButton deleteFavoriteButton;
    private JButton defaultLocationButton;
    private JButton changeLocationButton;
    private User user;
    private static final String API_KEY = "087d1d2feb71d6a8b5e887ca8011b88a"; // Place API key here for easy updates

    public HomeFrame(User user) {
        this.user = user;

        setTitle("Home");
        setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().width * 0.5),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height * 0.6));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(91, 147, 197));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Initialize buttons
        checkWeatherButton = StyleUtils.createStyledButton("Check Weather", new Color(16, 39, 191));
        addFavoriteButton = StyleUtils.createStyledButton("Add Favorite Location", new Color(222, 42, 228));
        showFavoritesButton = StyleUtils.createStyledButton("Show Favorites", new Color(184, 102, 102));
        deleteFavoriteButton = StyleUtils.createStyledButton("Delete Favorite Location", new Color(255, 69, 0));
        defaultLocationButton = StyleUtils.createStyledButton("Show Default Location Weather", new Color(8, 61, 112));
        changeLocationButton = StyleUtils.createStyledButton("Change Default Location", new Color(27, 112, 27));

        // Add buttons to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(checkWeatherButton, gbc);

        gbc.gridy = 1;
        panel.add(addFavoriteButton, gbc);

        gbc.gridy = 2;
        panel.add(showFavoritesButton, gbc);

        gbc.gridy = 3;
        panel.add(deleteFavoriteButton, gbc);

        gbc.gridy = 4;
        panel.add(defaultLocationButton, gbc);

        gbc.gridy = 5;
        panel.add(changeLocationButton, gbc);

        add(panel);

        // Action listeners for buttons
        checkWeatherButton.addActionListener(e -> new WeatherCheckFrame(user).setVisible(true));
        addFavoriteButton.addActionListener(e -> new AddFavoriteFrame(user).setVisible(true));
        showFavoritesButton.addActionListener(e -> new ShowFavoritesFrame(user).setVisible(true));
        deleteFavoriteButton.addActionListener(e -> new DeleteFavoriteFrame(user).setVisible(true));
        defaultLocationButton.addActionListener(e -> handleDefaultLocationWeather());
        changeLocationButton.addActionListener(e -> new ChangeDefaultLocationFrame(user).setVisible(true));
    }

    /**
     * Handles displaying weather for the user's default location.
     */
    private void handleDefaultLocationWeather() {
        String defaultLocation = user.getDefaultLocation();
        if (defaultLocation == null || defaultLocation.isEmpty()) {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "No default location set. Would you like to set one now?",
                    "Default Location",
                    JOptionPane.YES_NO_OPTION
            );
            if (result == JOptionPane.YES_OPTION) {
                new ChangeDefaultLocationFrame(user).setVisible(true);
            }
        } else {
            showWeatherForLocation(defaultLocation);
        }
    }

    /**
     * Displays weather for a specific location.
     */
    private void showWeatherForLocation(String location) {
        String weather = fetchWeather(location);
        JOptionPane.showMessageDialog(this, "Weather for " + location + ":\n" + weather);
    }

    /**
     * Fetches weather data for a specific location.
     */
    private String fetchWeather(String location) {
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=metric&appid=" + API_KEY;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder content = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                connection.disconnect();

                // Parse the weather data
                JSONObject weatherJson = new JSONObject(content.toString());
                String description = weatherJson.getJSONArray("weather").getJSONObject(0).getString("description");
                double temperature = weatherJson.getJSONObject("main").getDouble("temp");

                return String.format("Weather: %s\nTemperature: %.1f°C", description, temperature);
            } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                return "Location not found. Please check the name.";
            } else {
                return "Failed to fetch weather data. Please check your connection.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching weather data.";
        }
    }
}
