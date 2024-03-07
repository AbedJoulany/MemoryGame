package ood.memorycardgame.ThemePack;


public class FruitTheme extends Theme {
    @Override
    protected String createCardImagePath(int cardId) {
        return "Images/fruit/fruit-" + cardId + ".jpg";
    }
}
