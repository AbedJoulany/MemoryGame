package ood.memorycardgame.ThemePack;

import ood.memorycardgame.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Theme {
    public List<Card> createCards(int numberOfPairs) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < numberOfPairs; i++) {
            String imagePath = createCardImagePath(i);
            cards.add(new Card(i, imagePath));
            cards.add(new Card(i, imagePath));
        }
        return cards;
    }

    protected abstract String createCardImagePath(int cardId);
}
