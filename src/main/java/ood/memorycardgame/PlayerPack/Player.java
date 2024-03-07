package ood.memorycardgame.PlayerPack;

import ood.memorycardgame.Card;
import ood.memorycardgame.GameController;
import ood.memorycardgame.PlayStrategy.PlayerStrategy;

public abstract class Player {
    protected String name;
    protected PlayerStrategy playStrategy;
    protected int moves;

    public Player(String name) {
        this.name = name;
        this.moves = 0;
        this.correctMoves = 0;
    }
    public Player(String name, PlayerStrategy playStrategy) {
        this.name = name;
        this.playStrategy = playStrategy;
    }
    public int getMoves() {
        return moves;
    }
    protected int correctMoves;

    public void setMoves(int moves) {
        this.moves = moves;
    }
    public void incrementMoves() {
        this.moves += 1;
    }
    public int getCorrectMoves() {
        return correctMoves;
    }
    public void setCorrectMoves(int correctMoves) {
        this.correctMoves = correctMoves;
    }
    public void incrementCorrectMoves() {
        this.moves += 1;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    public PlayerStrategy getPlayStrategy() {
        return playStrategy;
    }

    public void setPlayStrategy(PlayerStrategy playStrategy) {
        this.playStrategy = playStrategy;
    }


    public Card selectCard(GameController gameController){
        return this.playStrategy.playTurn(gameController);
    }

    // Implement methods to update player's score, retrieve player's name, etc.
}
