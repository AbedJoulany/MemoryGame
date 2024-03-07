package ood.memorycardgame.PlayStrategy;




public class PlayerStrategyFactory {
    public static PlayerStrategy createStrategy(String strategy) {

        return switch (strategy) {
            case "Easy" -> new EasyComputerStrategy();
            case "Medium" -> new MediumComputerStrategy();
            case "Hard" -> new HardComputerStrategy();
            default -> new HumanPlayerStrategy();
        };
    }
}
