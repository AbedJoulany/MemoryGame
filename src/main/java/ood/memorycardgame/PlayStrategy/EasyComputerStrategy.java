package ood.memorycardgame.PlayStrategy;

import ood.memorycardgame.Card;
import ood.memorycardgame.GameController;

import java.util.List;
import java.util.Random;

public class EasyComputerStrategy extends ComputerStrategy {
    @Override
    public Card playTurn(GameController gameController)  {

        List<Card> unflippedCards = gameController.getGameBoard().getUnflippedCards();

        return getRandomCard(unflippedCards);
    }
}
