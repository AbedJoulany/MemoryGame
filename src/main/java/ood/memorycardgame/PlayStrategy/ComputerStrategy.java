package ood.memorycardgame.PlayStrategy;

import ood.memorycardgame.Card;
import ood.memorycardgame.GameController;
import ood.memorycardgame.Position;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class ComputerStrategy extends PlayerStrategy{
    @Override
    public Card playTurn(GameController gameController) {
        return null;
    }

    protected Card getCardFromRevealedCards(Map<Position, Card> map, Card firstCard){
        for (Map.Entry<Position, Card> entry : map.entrySet()) {

            if (firstCard.isMatching(entry.getValue()) && !firstCard.getPositionToGrid().isEqual(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    protected Card getRandomCard(List<Card> unflippedCards){
        // else chose random card
        int randomIndex = new Random().nextInt(unflippedCards.size());
        return unflippedCards.get(randomIndex);
    }
}
