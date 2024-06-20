package fr.esgi.lousvegous.symbol;

import java.util.ArrayList;
import java.util.List;

public class SymbolManager {
    public static List<Symbol> symbols = new ArrayList<>();
    private static SymbolManager instance = null;
    private static float totalWeight;

    private SymbolManager() {
        addSymbol(new Symbol("s1", (float) 1 / 16, "joker", new float[]{4, 10, 20}));
        addSymbol(new Symbol("s2", (float) 1 / 12, "kiwi", new float[]{1.6f, 6, 10}));
        addSymbol(new Symbol("s3", (float) 1 / 8, "strawberry", new float[]{1, 4, 6}));
        addSymbol(new Symbol("s4", (float) 1 / 6, "watermelon", new float[]{.6f, 3, 5}));
        addSymbol(new Symbol("s5", (float) 1 / 5, "grapes", new float[]{.4f, 2, 4}));
        addSymbol(new Symbol("s6", (float) 1 / 4, "banana", new float[]{.28f, 1.2f, 3}));
        addSymbol(new Symbol("s7", (float) 1 / 3, "cherries", new float[]{.2f, .8f, 2.4f}));
        addSymbol(new Symbol("s8", (float) 1 / 3, "eggplant", new float[]{.16f, .6f, 2}));
        addSymbol(new Symbol("s9", (float) 1 / 3, "peach", new float[]{.12f, .4f, 1.6f}));
    }

    public static SymbolManager getInstance() {
        if (instance == null) {
            instance = new SymbolManager();
        }
        return instance;
    }

    public void addSymbol(Symbol symbol) {
        symbols.add(symbol);
        totalWeight += symbol.getChance();
    }

    private void updateTotalWeight() {
        for (Symbol symbol : symbols) {
            totalWeight += symbol.getChance();
        }
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