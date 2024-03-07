package ood.memorycardgame;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


import ood.memorycardgame.PlayerPack.Player;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class GameController {

    @FXML
    private GridPane gameGridPane;
    @FXML
    private Label player1NameLabel;
    @FXML
    private Label player2NameLabel;

    @FXML
    private Label currentPlayerLabel;
    @FXML
    private Button back;

    private final PauseTransition flipbackTransiotion;

    private final GameModel gameModel;

    private Card firstFlippedCard;
    private Card secondFlippedCard;

    private Deque<Card> clickedByUser;

    public GameController(Player[] players, GameBoard gameBoard) {
        flipbackTransiotion = new PauseTransition(Duration.seconds(1));
        this.gameModel = new GameModel(players,gameBoard);
        clickedByUser = new ArrayDeque<>();
    }

    // Initialize method (called after FXML injection)
    @FXML
    public void initialize() {
        // Initialize game logic
        setCurrentPlayerName(this.gameModel.getCurrentPlayer().getName());
        back.setOnAction(e -> {
            try {
                backClicked();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        // Clear existing content in the GridPane
        gameGridPane.getChildren().clear();

        populateGridPane();
    }
    public void startGame() {
        startNextTurn();
    }

    private void startNextTurn() {
        Player currentPlayer = gameModel.getCurrentPlayer();
        setCurrentPlayerName(currentPlayer.getName());
        // Update UI to indicate current player
        Card card1 = currentPlayer.selectCard(this);
        if(card1 != null)
            flipCard(card1);
        Card card2 = currentPlayer.selectCard(this);
        if(card2 != null)
            flipCard(card2);
    }

    private void endTurn() {
        // Process end of turn actions
        gameModel.endTurn();
        clickedByUser.clear();

        flipbackTransiotion.setOnFinished(event ->{
            flipBack(firstFlippedCard, secondFlippedCard);
        });
        flipbackTransiotion.play();

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
        pauseTransition.setOnFinished(event -> {
            startNextTurn(); // Start next turn after 1 second
        });
        pauseTransition.play();
    }

    public void flipCard(Card card) {

        if (gameModel.isFlipped(card))
            return;

        gameModel.flipCard(card);
        int columnIndex = GridPane.getColumnIndex(card);
        int rowIndex = GridPane.getRowIndex(card);
        gameModel.addToRevealedCards(card,rowIndex,columnIndex);

        if (firstFlippedCard == null) {
            // First card flipped
            firstFlippedCard = card;
        } else if (secondFlippedCard == null) {
            // Second card flipped
            secondFlippedCard = card;
            // Check for match
            checkForMatch();
        }
    }

    private void checkForMatch() {

        gameModel.incrementPlayerMoves();

        if (firstFlippedCard.isMatching(secondFlippedCard)) {
            gameModel.incrementPlayerCorrectMoves();
            gameModel.getGameBoard().incrementTotalFlips();
            // Clear flipped cards
            firstFlippedCard = null;
            secondFlippedCard = null;
            if(gameModel.isGameOver()){
                    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
                    pauseTransition.setOnFinished(event -> {
                        try {
                            gameOver();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    pauseTransition.play();
            }
            else {
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
                pauseTransition.setOnFinished(event -> {
                    startNextTurn(); // Start next turn after 1 second
                });
                pauseTransition.play();
            }
        } else {
            // No match found
            // End current player's turn
            endTurn();
        }
    }

    private void populateGridPane() {

        // Get the cards as a grid
        List<List<Card>> cardsGrid = gameModel.getGameBoard().getCardsAsGrid();

        // Iterate over the grid to create ImageViews for each card
        for (int row = 0; row < gameModel.getGameBoard().getRows(); row++) {
            for (int col = 0; col < gameModel.getGameBoard().getColumns(); col++) {
                Card card = cardsGrid.get(row).get(col);
                // Create ImageView for the card
                card.setOnMouseClicked(event -> handleImageClick(card));
                // Add ImageView to the GridPane
                gameGridPane.add(card, col, row);
            }
        }
    }
    private void handleImageClick(Card card) {
        //this.clickedByUser.addLast(card);
        this.flipCard(card);
    }

    private void flipBack(Card card1, Card card2) {
        // Flip the images back
        gameModel.flipCard(card1);
        gameModel.flipCard(card2);

        // Clear flipped cards
        firstFlippedCard = null;
        secondFlippedCard = null;
    }

    public void setPlayerNames(String player1Name, String player2Name) {
        player1NameLabel.setText("Player 1: " + player1Name);
        player2NameLabel.setText("Player 2: " + player2Name);
    }
    public void setCurrentPlayerName(String currentPlayerName) {
        currentPlayerLabel.setText("Current Player: " + currentPlayerName);
    }

    public void backClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/main-menu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1280, 720);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
    }

    public GameBoard getGameBoard() {
        return this.gameModel.getGameBoard();
    }

    // Method to handle game over
    private void gameOver() throws IOException {
        // Perform any game over actions, such as displaying a message or resetting the game
        Player winner = gameModel.getWinner();
        int score = ScoreManager.getInstance().calculateScore(gameModel.getGameBoard(), winner);

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/GameOverDialog.fxml"));
                DialogPane dialogPane = loader.load(); // Load the FXML dialog pane
                GameOverDialogController controller = loader.getController();
                controller.setWinnerAndScore(winner.getName(), score);

                // Create the dialog
                Dialog<Button> dialog = new Dialog<>();
                controller.setDialog(dialog);
                dialog.setDialogPane(dialogPane); // Set the dialog pane as content

                dialog.showAndWait().ifPresent(button -> {
                    if (button == controller.getPlayAgainButton()) {
                        // Start a new game
                    } else if (button == controller.getBackToMenuButton()) {
                        try {
                            backClicked();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        // Go back to the menu
                    } else if (button == controller.getExitButton()) {
                        Platform.exit();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public Card getFirstFlippedCard() {
        return firstFlippedCard;
    }
    public Card getSecondFlippedCard() {
        return secondFlippedCard;
    }

    public Deque<Card> getClickedByUser() {
        return clickedByUser;
    }

    public void setClickedByUser(Deque<Card> clickedByUser) {
        this.clickedByUser = clickedByUser;
    }
}
