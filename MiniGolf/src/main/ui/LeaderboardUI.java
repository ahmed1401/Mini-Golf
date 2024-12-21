package main.ui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.game.GestionnaireScore;
import main.game.GestionnaireScore.Score;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;

public class LeaderboardUI {

    public static void showLeaderboard(Stage stage, GestionnaireScore gestionnaireScore, Runnable onBackToMenu) {
        if (gestionnaireScore == null) {
            System.err.println("Error: gestionnaireScore is null");
            return;
        }

        TableView<PlayerScore> tableView = new TableView<>();

        // Player Name Column
        TableColumn<PlayerScore, String> playerNameColumn = new TableColumn<>("Player");
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        // Level Scores Column
        TableColumn<PlayerScore, String> levelScoresColumn = new TableColumn<>("Level Scores");
        levelScoresColumn.setCellValueFactory(new PropertyValueFactory<>("levelScoresAsString"));

        // Total Score Column
        TableColumn<PlayerScore, Integer> totalScoreColumn = new TableColumn<>("Total Score");
        totalScoreColumn.setCellValueFactory(new PropertyValueFactory<>("totalScore"));

        // Add columns to the table
        tableView.getColumns().add(playerNameColumn);
        tableView.getColumns().add(levelScoresColumn);
        tableView.getColumns().add(totalScoreColumn);

        // Populate the table with data
        ObservableList<PlayerScore> data = FXCollections.observableArrayList();

        Map<String, List<Integer>> playerScores = gestionnaireScore.getPlayerScores();
        if (playerScores == null) {
            System.err.println("Error: playerScores is null");
            return;
        }

        for (Score score : gestionnaireScore.getScoresTries()) {
            String playerName = score.playerName();
            List<Integer> levelScores = playerScores.get(playerName);
            int totalScore = score.totalScore();

            if (levelScores != null) {
                data.add(new PlayerScore(playerName, levelScores, totalScore));
            } else {
                System.err.println("Warning: Level scores for player " + playerName + " are null");
            }
        }

        tableView.setItems(data);

        // "Back to Menu" button
        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> {
            onBackToMenu.run();
        });

        // Layout with the table and button
        VBox layout = new VBox(10, tableView, backButton);
        layout.setSpacing(10);

        // Create and set the scene on the JavaFX Application Thread
        Platform.runLater(() -> {
            Scene scene = new Scene(layout, 600, 450);
            stage.setScene(scene);
            stage.setTitle("Leaderboard");
            stage.show();
        });
    }

    public static class PlayerScore {
        private String playerName;
        private List<Integer> levelScores;
        private int totalScore;

        public PlayerScore(String playerName, List<Integer> levelScores, int totalScore) {
            this.playerName = playerName;
            this.levelScores = levelScores;
            this.totalScore = totalScore;
        }

        public String getPlayerName() {
            return playerName;
        }

        public String getLevelScoresAsString() {
            return levelScores != null ? String.join(", ", levelScores.stream().map(String::valueOf).toArray(String[]::new)) : "";
        }

        public int getTotalScore() {
            return totalScore;
        }
    }
}
