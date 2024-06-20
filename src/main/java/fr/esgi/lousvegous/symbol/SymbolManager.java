package fr.esgi.lousvegous.symbol;

import java.util.ArrayList;
import java.util.List;

public class SymbolManager {
    private static SymbolManager instance = null;
    public static List<Symbol> symbols = new ArrayList<>();
    private static float totalWeight;

    private SymbolManager() {
        symbols.add(new Symbol("s1", (float) 1 / 16, "7", new float[]{4, 10, 20}));
        symbols.add(new Symbol("s2", (float) 1 / 12, "\uD83C\uDF4D", new float[]{1.6f, 6, 10}));
        symbols.add(new Symbol("s3", (float) 1 / 8, "\uD83C\uDF50", new float[]{1, 4, 6}));
        symbols.add(new Symbol("s4", (float) 1 / 6, "\uD83C\uDF49", new float[]{.6f, 3, 5}));
        symbols.add(new Symbol("s5", (float) 1 / 5, "\uD83C\uDF4E", new float[]{.4f, 2, 4}));
        symbols.add(new Symbol("s6", (float) 1 / 4, "\uD83C\uDF48", new float[]{.28f, 1.2f, 3}));
        symbols.add(new Symbol("s7", (float) 1 / 3, "\uD83C\uDF52", new float[]{.2f, .8f, 2.4f}));
        symbols.add(new Symbol("s8", (float) 1 / 3, "\uD83C\uDF51", new float[]{.16f, .6f, 2}));
        symbols.add(new Symbol("s9", (float) 1 / 3, "\uD83C\uDF46", new float[]{.12f, .4f, 1.6f}));
        updateTotalWeight();
    }

    private void updateTotalWeight() {
        for (Symbol symbol : symbols) {
            totalWeight += symbol.getChance();
        }
    }

    public static SymbolManager getInstance() {
        if (instance == null) {
            instance = new SymbolManager();
        }
        return instance;
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    public List<Symbol> getSymbols(int count) {
        List<Symbol> symbols = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            symbols.add(getRandomSymbol());
        }
        return symbols;
    }

    public Symbol getRandomSymbol() {
        int idx = 0;
        for (double r = Math.random() * totalWeight; idx < symbols.size() - 1; ++idx) {
            r -= symbols.get(idx).getChance();
            if (r <= 0) {
                break;
            }
        }
        return symbols.get(idx);
    }
}