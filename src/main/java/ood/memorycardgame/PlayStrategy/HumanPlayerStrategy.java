package ood.memorycardgame.PlayStrategy;

import ood.memorycardgame.Card;
import ood.memorycardgame.GameController;

public class HumanPlayerStrategy extends PlayerStrategy {
    @Override
    public Card playTurn(GameController gameController) {

        return gameController.getClickedByUser().pollFirst();
    }
}
