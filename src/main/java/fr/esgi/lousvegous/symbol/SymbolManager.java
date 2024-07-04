package fr.esgi.lousvegous.symbol;

import fr.esgi.lousvegous.symbol.list.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SymbolManager {
    public static List<Symbol> symbols = new ArrayList<>();
    private static SymbolManager instance = null;
    private static float totalWeight;

    private SymbolManager() {
        addSymbol(new S1());
        addSymbol(new S2());
        addSymbol(new S3());
        addSymbol(new S4());
        addSymbol(new S5());
        addSymbol(new S6());
        addSymbol(new S7());
        addSymbol(new S8());
        addSymbol(new S9());
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

    // Random symbol with seed
    public Symbol getRandomSymbol(long seed) {
        Random random = new Random(seed);
        double r = random.nextDouble() * totalWeight;
        int idx = 0;
        for (; idx < symbols.size() - 1; ++idx) {
            r -= symbols.get(idx).getChance();
            if (r <= 0) {
                break;
            }
        }
        return symbols.get(idx);
    }
}