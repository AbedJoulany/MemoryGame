package ood.memorycardgame;

import javafx.util.Pair;
import ood.memorycardgame.ThemePack.Theme;

import java.util.*;

public class GameBoard {

    private List<Card> cards;
    private Map<Position, Card> revealedCardsMap; // Map to store flipped cards
    private final int rows;
    private final int columns;
    private Theme themeFactory;
    private int totalFlips;

    public GameBoard(int rows, int columns, Theme themeFactory) {
        this.rows = rows;
        this.columns = columns;
        this.themeFactory = themeFactory;
        reset();
    }

    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public void setThemeFactory(Theme themeFactory) {
        this.themeFactory = themeFactory;
    }

    private List<Card> createCards() {
        int numPairs = rows * columns / 2;
        List<Card> cards = themeFactory.createCards(numPairs);
        Collections.shuffle(cards);
        return cards;
    }
    public List<List<Card>> getCardsAsGrid() {
        List<List<Card>> grid = new ArrayList<>();
        int currentIndex = 0;

        for (int i = 0; i < rows; i++) {
            List<Card> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                if (currentIndex < cards.size()) {
                    cards.get(currentIndex).setPositionToGrid(new Position(i,j));
                    row.add(cards.get(currentIndex));
                    currentIndex++;
                }
            }
            grid.add(row);
        }

        return grid;
    }

    public boolean isCardFlipped(Card card) {
        return card.isFlipped();
    }

    public void flipCard(Card card) {
        card.flip();
    }

    public boolean isGameOver() {
        return totalFlips == (this.cards.size() / 2);
    }

    public int getTotalFlips() {
        return totalFlips;
    }

    public void setTotalFlips(int totalFlips) {
        this.totalFlips = totalFlips;
    }
    public void incrementTotalFlips() {
        this.totalFlips += 1;
    }
    public void decrementTotalFlips() {
        this.totalFlips -= 1;
    }
    public List<Card> getCards() {
        return cards;
    }
    public Map<Position, Card> getRevealedCardsMap() {
        return revealedCardsMap;
    }
    public void addToRevealedCards(Card card, int row, int col) {
        Position position = new Position(row,col);
        revealedCardsMap.put(position, card); // Store the flipped card with its position
    }
    public void resetRevealedCardsMap() {
        revealedCardsMap.clear();
    }


    public List<Card> getUnflippedCards() {
        List<Card> unflippedCards = new ArrayList<>();
        for (Card card :cards) {
            if (!card.isFlipped()) {
                unflippedCards.add(card);
            }
        }
        return unflippedCards;
    }

    // Initialize the game board
    private void reset() {
        cards = createCards();
        revealedCardsMap = new HashMap<>();
        totalFlips = 0;
    }
    public void resetGameBoard() {
        reset(); // Reset cards and revealed cards map
    }
    // ... other methods for score calculation based on chosen scoring system
}

