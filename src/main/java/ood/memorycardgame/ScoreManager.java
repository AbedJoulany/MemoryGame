package ood.memorycardgame;

import ood.memorycardgame.PlayerPack.Player;
import ood.memorycardgame.Scoring.ScoringSystem;
import ood.memorycardgame.Scoring.StandardScoring;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScoreManager {
    private static ScoreManager instance;
    private ScoringSystem scoringSystem;
    private final Map<String, Integer> playerScores;


    private ScoreManager() {
        // Private constructor to prevent instantiation from outside
        scoringSystem = new StandardScoring();
        playerScores = new HashMap<>();
        // Load scores from file when ScoreManager instance is created
        loadScoresFromFile();
    }

    public static ScoreManager getInstance() {
        if (instance == null) {
            synchronized (ScoreManager.class) {
                if (instance == null) {
                    instance = new ScoreManager();
                }
            }
        }
        return instance;
    }

    public void setScoringSystem(ScoringSystem scoringSystem) {
        this.scoringSystem = scoringSystem;
    }

    public int calculateScore(GameBoard board, Player player) {
        if (scoringSystem == null) {
            throw new IllegalStateException("Scoring system is not set");
        }
        int score = scoringSystem.calculateScore(board, player);
        addScore(player.getName(), score);
        return score;
    }

    public void addScore(String playerName, int score) {
        playerScores.put(playerName, score);
        saveScoresToFile();
    }

    public Map<String, Integer> getPlayerScores() {
        return Collections.unmodifiableMap(playerScores);
    }

    private void saveScoresToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("scores.dat"))) {
            outputStream.writeObject(playerScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadScoresFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("scores.dat"))) {
            Object obj = inputStream.readObject();
            if (obj instanceof Map) {
                playerScores.putAll((Map<String, Integer>) obj);
            }
        } catch (IOException | ClassNotFoundException e) {
            // File doesn't exist or failed to load, it's okay to proceed with an empty playerScores map
        }
    }
}

