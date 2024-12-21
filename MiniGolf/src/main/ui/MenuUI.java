package main.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import main.game.GestionnaireScore;
import main.game.GestionnaireNiveaux;

public class MenuUI {

    private static String selectedPlayer;

    public static void showMenu(Stage stage, Runnable onStartGameForJoueur1, Runnable onStartGameForJoueur2, GestionnaireNiveaux gestionnaireNiveaux, GestionnaireScore gestionnaireScore) {
        // Label for "Welcome to Mini-Golf"
        Label welcomeLabel = new Label("Welcome to Our Game");
        welcomeLabel.setStyle(
            "-fx-font-size: 36px; " + // Make the font slightly smaller (36px)
            "-fx-font-weight: bold; " +
            "-fx-text-fill: #333; " +
            "-fx-background-color: rgba(255, 255, 255, 0.8); " + // Add background color with transparency
            "-fx-padding: 20px; " + // Add padding inside the box
            "-fx-border-radius: 15px;" // Remove the border color for no contour
        );

        // Buttons for players and other actions
        Button joueur1Button = new Button("Joueur1");
        joueur1Button.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 10px;");
        joueur1Button.setOnAction(e -> {
            selectedPlayer = "Joueur1";
            onStartGameForJoueur1.run();
        });

        Button joueur2Button = new Button("Joueur2");
        joueur2Button.setStyle("-fx-font-size: 18px; -fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 10px;");
        joueur2Button.setOnAction(e -> {
            selectedPlayer = "Joueur2";
            onStartGameForJoueur2.run();
        });

        Button leaderboardButton = new Button("Leaderboard");
        leaderboardButton.setStyle("-fx-font-size: 18px; -fx-background-color: #FFC107; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 10px;");
        leaderboardButton.setOnAction(e -> showLeaderboard(stage, gestionnaireScore, 
            () -> showMenu(stage, onStartGameForJoueur1, onStartGameForJoueur2, gestionnaireNiveaux, gestionnaireScore)
        ));

        Button quitterButton = new Button("Quitter");
        quitterButton.setStyle("-fx-font-size: 18px; -fx-background-color: #F44336; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 10px;");
        quitterButton.setOnAction(e -> System.exit(0)); // Exit the application

        // Layout for the buttons (VBox)
        VBox buttonLayout = new VBox(20, joueur1Button, joueur2Button, leaderboardButton, quitterButton);
        buttonLayout.setAlignment(Pos.CENTER);

        // Label for "BY Skander & Ahmed" at the bottom left
        Label creditsLabel = new Label("By: SKANDER & AHMED");
        creditsLabel.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " + // Make it bold
            "-fx-text-fill: #333; " +
            "-fx-padding: 10px; " +
            "-fx-background-color: rgba(255, 255, 255, 0.7); " + // Semi-transparent background
            "-fx-border-radius: 5px; " + // No border for the box
            "-fx-alignment: center-left; " +
            "-fx-pref-width: 200px;"
        );

        // Layout for the entire menu (BorderPane for better control over the positioning)
        BorderPane layout = new BorderPane();
        layout.setTop(welcomeLabel); // Place the welcome label at the top
        layout.setCenter(buttonLayout); // Place the buttons in the center
        layout.setBottom(creditsLabel); // Place the credits label at the bottom
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER); // Center the welcome label
        BorderPane.setAlignment(creditsLabel, Pos.BOTTOM_LEFT); // Align the credits label to the bottom left

        // Set background image with try-catch block
        String imagePath = "file:C:/Users/ahmed/OneDrive/Desktop/MiniGolf/src/main/ressources/golfimg.jpg"; // Your image path
        try {
            layout.setStyle(
                "-fx-background-image: url('" + imagePath + "');" + // Set background image
                "-fx-background-size: cover;" + // Ensures the image covers the whole area
                "-fx-padding: 50px;"
            );
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            layout.setStyle("-fx-background-color: #F5F5F5;"); // Fallback background color
        }

        Scene scene = new Scene(layout, 600, 600);
        stage.setScene(scene);
        stage.setTitle("Mini Golf - Main Menu");
        stage.show();
    }

    private static void showLeaderboard(Stage stage, GestionnaireScore gestionnaireScore, Runnable onBackToMenu) {
        // Here, you call the showLeaderboard method with the provided parameters
        LeaderboardUI.showLeaderboard(stage, gestionnaireScore, onBackToMenu);
    }

    public static String getSelectedPlayer() {
        return selectedPlayer;
    }
}
