package ood.memorycardgame;

import ood.memorycardgame.PlayerPack.Player;

import java.util.List;

public class TurnManager {
    private final int playersSize;
    private int currentPlayerIndex;

    public TurnManager(int playersSize) {
        this.playersSize = playersSize;
        this.currentPlayerIndex = 0;
    }

    public int getCurrentPlayer() {
        return currentPlayerIndex;
    }

    public void endTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % playersSize;
    }
}

