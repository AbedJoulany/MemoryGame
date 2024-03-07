package ood.memorycardgame.Scoring;

import ood.memorycardgame.GameBoard;
import ood.memorycardgame.PlayerPack.Player;

public interface ScoringSystem {
    int calculateScore(GameBoard board, Player player);
}
