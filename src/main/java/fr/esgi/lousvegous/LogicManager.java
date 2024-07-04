package fr.esgi.lousvegous;

public class LogicManager {
    private static int minBet = 500;

    public static int getMinBet() {
        return minBet;
    }

    public static void setMinBet(int minBet) {
        LogicManager.minBet = minBet;
    }
}
