package ood.memorycardgame;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ood.memorycardgame.PlayStrategy.PlayerStrategyFactory;
import ood.memorycardgame.PlayerPack.Player;
import ood.memorycardgame.PlayerPack.PlayerFactory;
import ood.memorycardgame.ThemePack.Theme;
import ood.memorycardgame.ThemePack.ThemeFactory;

import java.io.IOException;
import java.util.ArrayList;

public class MainMenuController {

    @FXML
    private TextField player1NameTextField;

    @FXML
    private TextField player2NameTextField;

    @FXML
    private ComboBox<String> gridSizeComboBox;

    @FXML
    private ComboBox<String> modeComboBox;

    @FXML
    private Label difficultyLabel;

    @FXML
    private ComboBox<String> difficultyComboBox;

    @FXML
    private ComboBox<String> themeComboBox;

    @FXML
    private Button startBtn;
    @FXML
    private Button highScoresBtn;


    private ArrayList<String> errors;

    // Initialize the controller
    public void initialize() {
        initializeComboBoxes();
        configureModeComboBox();
        initializeGridSizeComboBox();
        errors = new ArrayList<String>();
    }

    // Initialize combo boxes with default values
    private void initializeComboBoxes() {
        modeComboBox.setItems(FXCollections.observableArrayList("Player vs Player", "Player vs Computer"));
        difficultyComboBox.setItems(FXCollections.observableArrayList("Easy", "Medium", "Hard"));
        themeComboBox.setItems(FXCollections.observableArrayList("Animal", "Fruit", "Food"));
        // Set initial values for FXML fields
        gridSizeComboBox.setValue("4x4"); // Set default grid size
        modeComboBox.setValue("Player vs Player"); // Set default mode
        difficultyComboBox.setValue("Easy"); // Set default difficulty
        themeComboBox.setValue("Animal"); // Set default theme

    }
    private void initializeGridSizeComboBox() {
        gridSizeComboBox.setItems(FXCollections.observableArrayList(
                "2x2","3x4","4x4", "4x5", "5x4"
        ));
    }
    // Configure mode combo box
    private void configureModeComboBox() {
        modeComboBox.setOnAction(event -> {
            boolean disableDifficulty = !"Player vs Computer".equals(modeComboBox.getValue());
            difficultyComboBox.setDisable(disableDifficulty);
        });
    }

    // Handle start game button click
    @FXML
    void startGame() {
        try {
            validateInputs();
            createGameScene();
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
            // Handle validation errors or file loading errors
            showAlert(Alert.AlertType.ERROR, "Error");
        }
    }



    // Create and load game scene
    private void createGameScene() throws IOException {
        String gridSize = gridSizeComboBox.getValue();
        String[] dimensions = gridSize.split("x");

        int rows = Integer.parseInt(dimensions[0]);
        int columns = Integer.parseInt(dimensions[1]);

        Theme theme = ThemeFactory.createTheme(themeComboBox.getValue());
        GameBoard gameBoard = new GameBoard(rows, columns, theme);
        Player[] players = initiatePlayers();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/GameScene.fxml"));
        GameController gameController = new GameController(players, gameBoard);
        loader.setController(gameController);
        Parent gameSceneRoot = loader.load();
        GameController gameSceneController = loader.getController();
        gameController.startGame();
        gameSceneController.setPlayerNames(player1NameTextField.getText(), player2NameTextField.getText());

        Stage stage = (Stage) startBtn.getScene().getWindow();
        Scene gameScene = new Scene(gameSceneRoot);
        stage.setScene(gameScene);
        stage.setTitle("Memory Card GameModel");
        stage.show();
    }

    // Create player instances based on selected mode and inputs
    private Player[] initiatePlayers() {
        return PlayerFactory.createPlayers(modeComboBox.getValue(),
                player1NameTextField.getText(), player2NameTextField.getText(),
                PlayerStrategyFactory.createStrategy(difficultyComboBox.getValue())
        );
    }


    // Validate user inputs
    private void validateInputs() {
        if (modeComboBox.getValue() == null ){
            errors.add("Please select mode");
        }
        if(player1NameTextField.getText().isEmpty()) {
                errors.add("Please enter Player 1 name.");
        }
        if (player2NameTextField.getText().isEmpty()) {
            errors.add("Please enter Player 2 name.");
        }
    }

    // Show alert dialog with specified type, title, and message
    // Show alert dialog with specified type, title, and list of errors
    private void showAlert(Alert.AlertType alertType, String title) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);

        // Concatenate the list of errors into a single string
        StringBuilder errorMessage = new StringBuilder();
        for (String error : errors) {
            errorMessage.append(error).append("\n");
        }

        // Set the error message in the content text of the alert
        alert.setContentText(errorMessage.toString());
        alert.showAndWait();

        errors.clear();
    }

    @FXML
    void showHighScores() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/HighScores.fxml"));
        Stage stage = (Stage) highScoresBtn.getScene().getWindow();
        stage.getScene().setRoot(root);

    }
    @FXML
    void exitGame() {
        Platform.exit();
    }
}
