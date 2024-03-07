package ood.memorycardgame.Scoring;

import ood.memorycardgame.GameBoard;
import ood.memorycardgame.PlayerPack.Player;

public class StandardScoring implements ScoringSystem{
    @Override
    public int calculateScore(GameBoard board, Player player) {
        int correctFlipBonus = board.getTotalFlips() * 10;
        int incorrectFlips = player.getMoves() - player.getCorrectMoves();
        int incorrectGuessPenalty = incorrectFlips * 20;
        int score = 1000 + correctFlipBonus - incorrectGuessPenalty;
        return Math.max(score, 0); // Ensure score is non-negative
    }
}
