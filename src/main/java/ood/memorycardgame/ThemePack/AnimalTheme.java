package ood.memorycardgame.ThemePack;

// Animal Theme Factory
public class AnimalTheme extends Theme {
    @Override
    protected String createCardImagePath(int cardId) {
        return "Images/animals/animal-" + cardId + ".jpg";
    }
}

