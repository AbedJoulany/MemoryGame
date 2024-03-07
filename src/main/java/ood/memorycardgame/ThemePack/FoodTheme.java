package ood.memorycardgame.ThemePack;

public class FoodTheme extends Theme {
    @Override
    protected String createCardImagePath(int cardId) {
        return "Images/food/food-" + cardId + ".jpg";
    }
}