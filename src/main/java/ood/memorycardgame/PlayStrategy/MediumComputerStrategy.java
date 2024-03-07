package ood.memorycardgame.PlayStrategy;

import ood.memorycardgame.Card;
import ood.memorycardgame.GameController;
import ood.memorycardgame.Position;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MediumComputerStrategy extends ComputerStrategy {
    @Override
    public Card playTurn(GameController gameController)  {

        List<Card> unflippedCards = gameController.getGameBoard().getUnflippedCards();

        if (unflippedCards.isEmpty()){
            return null;
        }

        Map<Position, Card> revealedCardsMap = gameController.getGameBoard().getRevealedCardsMap();

        // first card to be selected randomly
        Card firstCard = gameController.getFirstFlippedCard();
        if ( firstCard == null){
            int randomIndex = new Random().nextInt(unflippedCards.size());
            return unflippedCards.get(randomIndex);
        }

        // second card: check for match in the revealed cards
        Card secondCard = getCardFromRevealedCards(revealedCardsMap, firstCard);
        if (secondCard != null){
            return secondCard;
        }
        // else chose random card
        return getRandomCard(unflippedCards);
    }
}
