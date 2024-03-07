package ood.memorycardgame.PlayerPack;


import ood.memorycardgame.PlayStrategy.HumanPlayerStrategy;
import ood.memorycardgame.PlayStrategy.PlayerStrategy;

public class PlayerFactory {
    public static Player[] createPlayers(String mode, String player1, String player2, PlayerStrategy player2Strategy) {

        if (mode.equalsIgnoreCase("Player vs Player")) {
            return new Player[]{new HumanPlayer(player1, new HumanPlayerStrategy()), new HumanPlayer(player2, new HumanPlayerStrategy())};
        } else if (mode.equalsIgnoreCase("Player vs Computer")) {
            return new Player[]{new HumanPlayer(player1, new HumanPlayerStrategy()), new ComputerPlayer(player2,player2Strategy)};
        } else {
            throw new IllegalArgumentException("Invalid mode: " + mode);
        }
    }
}