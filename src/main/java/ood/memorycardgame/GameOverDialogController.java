package ood.memorycardgame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;

public class GameOverDialogController {

    @FXML
    private Label winnerLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private Button playAgainButton;

    @FXML
    private Button backToMenuButton;

    @FXML
    private Button exitButton;

    private Dialog<Button> dialog;

    public void setWinnerAndScore(String winnerName, int score) {
        winnerLabel.setText("Winner: " + winnerName);
        scoreLabel.setText("Score: " + score);
    }

    public void setDialog(Dialog<Button> dialog) {
        this.dialog = dialog;
    }

    @FXML
    void handlePlayAgain() {
        dialog.setResult(playAgainButton);
    }

    @FXML
    void handleBackToMenu() {
        dialog.setResult(backToMenuButton);
    }

    @FXML
    void handleExit() {
        dialog.setResult(exitButton);
    }

    // Getters for the buttons
    public Button getPlayAgainButton() {
        return playAgainButton;
    }

    public Button getBackToMenuButton() {
        return backToMenuButton;
    }

    public Button getExitButton() {
        return exitButton;
    }
}
