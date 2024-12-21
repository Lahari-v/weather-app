package frames;

import model.FavoriteLocation;
import model.User;
import utils.StyleUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowFavoritesFrame extends JFrame {
    private User user;
    private JTextArea favoritesArea;

    public ShowFavoritesFrame(User user) {
        this.user = user;

        setTitle("Favorite Locations");
        StyleUtils.setWindowTo75Percent(this);  // Set size to 75% and center

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel setup
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(230, 240, 250));  // Updated background color
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title label
        JLabel titleLabel = new JLabel("Your Favorite Locations");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(60, 120, 180));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);  // Center title
        panel.add(titleLabel, BorderLayout.NORTH);

        // Favorites display area with rounded edges
        favoritesArea = new JTextArea(10, 30);
        favoritesArea.setEditable(false);
        favoritesArea.setFont(new Font("Arial", Font.PLAIN, 14));
        favoritesArea.setBackground(new Color(250, 250, 255));
        favoritesArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Scroll pane for the text area
        JScrollPane scrollPane = new JScrollPane(favoritesArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());  // No border for scroll pane
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        displayFavorites();
    }

    private void displayFavorites() {
        List<FavoriteLocation> favorites = FavoriteLocation.getFavoritesByUserId(user.getId());
        if (favorites.isEmpty()) {
            favoritesArea.setText("No favorite locations found.");
        } else {
            StringBuilder sb = new StringBuilder("Favorite Locations:\n\n");
            for (FavoriteLocation favorite : favorites) {
                sb.append("• ").append(favorite.getLocation()).append("\n");
            }
            favoritesArea.setText(sb.toString());
        }
    }
}
