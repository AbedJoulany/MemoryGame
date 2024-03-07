package ood.memorycardgame.PlayerPack;

import ood.memorycardgame.GameBoard;
import ood.memorycardgame.PlayStrategy.PlayerStrategy;

// ComputerPlayer.java
public class ComputerPlayer extends Player {

    public ComputerPlayer(String name) {
        super(name);
    }
    public ComputerPlayer(String name, PlayerStrategy playStrategy) {
        super(name, playStrategy);
    }


}