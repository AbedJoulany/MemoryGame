package ood.memorycardgame.PlayStrategy;

import ood.memorycardgame.Card;
import ood.memorycardgame.GameController;

import java.util.List;
import java.util.Random;

public abstract class PlayerStrategy {
    public abstract Card playTurn(GameController gameController) ;

}
