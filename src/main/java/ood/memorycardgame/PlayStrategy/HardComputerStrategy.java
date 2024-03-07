package ood.memorycardgame.PlayStrategy;

import javafx.geometry.Pos;
import ood.memorycardgame.Card;
import ood.memorycardgame.GameController;
import ood.memorycardgame.Position;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class HardComputerStrategy extends ComputerStrategy {
    @Override
    public Card playTurn(GameController gameController) {

        Map<Position, Card> revealedCardsMap = gameController.getGameBoard().getRevealedCardsMap();
        List<Card> unflippedCards = gameController.getGameBoard().getUnflippedCards();

        Card firstCard = gameController.getFirstFlippedCard();
        if (firstCard == null) {
            // Iterate over the revealed cards to find a pair
            for (Map.Entry<Position, Card> entry : revealedCardsMap.entrySet()) {
                Card currentCard = entry.getValue();
                Position currentPosition = entry.getKey();
                for (Map.Entry<Position, Card> innerEntry : revealedCardsMap.entrySet()) {
                    Card otherCard = innerEntry.getValue();
                    Position otherPosition = innerEntry.getKey();
                    if (!currentPosition.isEqual(otherPosition) && currentCard.isMatching(otherCard)) {
                        // Return the index of the first card in the pair
                        return currentCard;
                    }
                }
            }

            // if no pair choose a random card
            return getRandomCard(unflippedCards);
        }

        // for second card

        // second card: check for match in the revealed cards
        Card secondCard = getCardFromRevealedCards(revealedCardsMap, firstCard);
        if (secondCard != null){
            return secondCard;
        }
        // else chose random card
        return getRandomCard(unflippedCards);
    }
}
