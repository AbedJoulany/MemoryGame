package ood.memorycardgame;

import java.util.List;

import ood.memorycardgame.PlayerPack.Player;

public class GameModel {

    private GameBoard gameBoard;
    private TurnManager turnManager;
    private List<Player> players;

    public GameModel(Player[] players, GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.players = List.of(players);
        turnManager = new TurnManager(this.players.size());
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void startGame() {
        // Perform any initialization logic here
    }
    public void flipCard(Card card){
        gameBoard.flipCard(card);
    }
    public void addToRevealedCards(Card card, int row, int col){
        gameBoard.addToRevealedCards(card, row, col);
    }
    public boolean isFlipped(Card card) {
        return gameBoard.isCardFlipped(card);
    }

    public Player getCurrentPlayer(){
         return this.players.get(turnManager.getCurrentPlayer());
    }
    public void endTurn() {
        turnManager.endTurn(); // Move to the next player
    }

    public boolean isGameOver(){
        return gameBoard.isGameOver();
    }
    public Player getWinner(){
        if (players.get(0).getCorrectMoves() > players.get(1).getCorrectMoves()){
            return  players.get(0);
        }
        return players.get(1);
    }
    public void incrementPlayerMoves() {
        getCurrentPlayer().incrementMoves();
    }
    public void incrementPlayerCorrectMoves() {
        getCurrentPlayer().incrementCorrectMoves();
    }
}
