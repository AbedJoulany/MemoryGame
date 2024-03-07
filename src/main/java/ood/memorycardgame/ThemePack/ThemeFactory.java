package ood.memorycardgame.ThemePack;

import ood.memorycardgame.PlayStrategy.*;

public class ThemeFactory {

        public static Theme createTheme(String theme) {

            return switch (theme) {
                case "Animal" -> new AnimalTheme();
                case "Food" -> new FoodTheme();
                case "Fruit" -> new FruitTheme();
                default -> new AnimalTheme();
            };
        }
}
