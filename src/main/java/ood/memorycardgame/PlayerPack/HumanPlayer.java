package ood.memorycardgame.PlayerPack;

import ood.memorycardgame.GameBoard;
import ood.memorycardgame.PlayStrategy.PlayerStrategy;

// HumanPlayer.java
public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }
    public HumanPlayer(String name, PlayerStrategy playStrategy) {
        super(name, playStrategy);
    }

}