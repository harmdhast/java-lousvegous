package fr.esgi.lousvegous;

public class Random {
    private double totalWeight;

    public Random() {
        for (Symbol symbol : Main.symbols) {
            totalWeight += symbol.getChance();
        }
    }

    public Symbol getRandomSymbol() {
        int idx = 0;
        for (double r = Math.random() * totalWeight; idx < Main.symbols.size() - 1; ++idx) {
            r -= Main.symbols.get(idx).getChance();
            if (r <= 0) {
                break;
            }
        }
        return Main.symbols.get(idx);
    }
}
