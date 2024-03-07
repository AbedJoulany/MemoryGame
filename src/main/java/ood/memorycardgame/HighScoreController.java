package ood.memorycardgame;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class HighScoreController {
    @FXML
    private TableView<Map.Entry<String, Integer>> tableView;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, String> playerNameColumn;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, Integer> scoreColumn;

    @FXML
    private Button back;

    public void initialize() {
        playerNameColumn.setCellValueFactory(data -> {
            String playerName = data.getValue().getKey();
            return new SimpleStringProperty(playerName);
        });

        scoreColumn.setCellValueFactory(data -> {
            Integer score = data.getValue().getValue();
            return new SimpleIntegerProperty(score).asObject();
        });

        ScoreManager scoreManager = ScoreManager.getInstance();
        Map<String, Integer> highScores = scoreManager.getPlayerScores();
        ObservableList<Map.Entry<String, Integer>> data = FXCollections.observableArrayList(highScores.entrySet());

        tableView.setItems(data);
    }

    public void backClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/main-menu.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) back.getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}
