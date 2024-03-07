package ood.memorycardgame.Scoring;

import ood.memorycardgame.GameBoard;
import ood.memorycardgame.PlayerPack.Player;

public class PrecisionScoring implements ScoringSystem{
    @Override
    public int calculateScore(GameBoard board, Player player) {
        // Calculate score based on precision (fewest moves, fewer incorrect guesses)
        int incorrectFlips = player.getMoves() - player.getCorrectMoves();
        int correctFlipsPercentage = (int) ((double) player.getCorrectMoves() / board.getTotalFlips() * 10);
        int score = 1000 - (player.getMoves() * 10) - (incorrectFlips * 50) + (correctFlipsPercentage * 10);
        return Math.max(score, 0); // Ensure score is non-negative
    }
}
