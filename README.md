# Weather Application

## Overview
This Java-based desktop application allows users to check weather details for a specific location, manage their favorite locations, and set a default location for quick weather updates. It leverages the OpenWeatherMap API to fetch real-time weather data and includes a simple database for user and favorite location management.

---

## Features
- **User Authentication**: Login and registration functionality.
- **Weather Check**: Fetch weather data for any location.
- **Favorite Locations**: Add, view, and delete favorite locations.
- **Default Location**: Set a default location for quick weather updates.
- **Custom Styling**: User-friendly interface with a modern design.

---

## Project Structure
```
src
└── main
    └── java
        ├── config
        │   └── DatabaseConnection.java
        ├── frames
        │   ├── AddFavoriteFrame.java
        │   ├── ChangeDefaultLocationFrame.java
        │   ├── DeleteFavoriteFrame.java
        │   ├── HomeFrame.java
        │   ├── LoginFrame.java
        │   ├── RegisterFrame.java
        │   ├── ShowFavoritesFrame.java
        │   └── WeatherCheckFrame.java
        ├── model
        │   ├── FavoriteLocation.java
        │   └── User.java
        └── utils
            └── StyleUtils.java
```

---

## Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher.
- **MySQL**: For database management.

---

## Database Setup
1. **Create a Database**:
   ```sql
   CREATE DATABASE weather_app;
   ```

2. **Create Tables**:
   ```sql
   CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(255) NOT NULL UNIQUE,
       password VARCHAR(255) NOT NULL,
       default_location VARCHAR(255)
   );

   CREATE TABLE favorite_locations (
       id INT AUTO_INCREMENT PRIMARY KEY,
       user_id INT NOT NULL,
       location_name VARCHAR(255) NOT NULL,
       FOREIGN KEY (user_id) REFERENCES users(id)
   );
   ```

3. **Update Database Connection**:
   Update the `DatabaseConnection.java` file with your MySQL database credentials:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/weather_app";
   private static final String USER = "your_username";
   private static final String PASSWORD = "your_password";
   ```

---

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/Lahari-v/weather-app.git
   ```
2. Open the project in your favorite IDE (e.g., IntelliJ IDEA or Eclipse).
3. Build and run the `Main` class to launch the application.

---

## API Integration
- **OpenWeatherMap API**:
  - The application uses the OpenWeatherMap API to fetch weather data.
  - Replace the `API_KEY` in `WeatherCheckFrame.java` with your own API key:
    ```java
    private final String API_KEY = "your_api_key";
    ```
