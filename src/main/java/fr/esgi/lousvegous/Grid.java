package fr.esgi.lousvegous;

import fr.esgi.lousvegous.pattern.Pattern;
import fr.esgi.lousvegous.pattern.PatternManager;
import fr.esgi.lousvegous.symbol.S1;
import fr.esgi.lousvegous.symbol.Symbol;
import fr.esgi.lousvegous.symbol.SymbolManager;

import java.util.HashMap;
import java.util.Objects;

public class Grid {
    private static final Symbol wild = new S1();
    private static final Symbol[] grid = new Symbol[15];
    private static Grid instance = null;

    public static Grid getInstance() {
        if (instance == null) {
            instance = new Grid();
        }
        return instance;
    }

    public void setGridSymbol(int index, Symbol symbol) {
        grid[index] = symbol;
    }

    public HashMap<Pattern, Symbol> getAllMatches() {
        if (hasNullSymbol()) {
            return null;
        }

        HashMap<Pattern, Symbol> matches = new HashMap<>();
        for (Pattern pattern : PatternManager.patterns) {
            Pattern p = new Pattern(pattern);
            Symbol found = getMatchingSymbol(p);
            if (found != null) {
                matches.put(p, found);
            }
        }
        return matches;
    }

    public Symbol getMatchingSymbol(Pattern pattern) {
        if (hasNullSymbol()) {
            return null;
        }

        for (Symbol symbol : SymbolManager.getInstance().getSymbols()) {
            int binary = getBinaryStringForSymbol(symbol);
            //System.out.println("Binary: " + binary + " for symbol: " + symbol.getId() + " and pattern: " + pattern.getPattern());
            if ((pattern.getBinary() & binary) == pattern.getBinary()) {
                System.out.println("Found match: " + symbol.getId() + " for pattern: " + pattern.getName());
                return symbol;
            } else if ((pattern.fourColumns() & binary) == pattern.fourColumns()) {
                pattern.setMode(1);
                System.out.println("Found match 4 columns: " + symbol.getId() + " for pattern: " + pattern.getName());
                return symbol;
            } else if ((pattern.threeColumns() & binary) == pattern.threeColumns()) {
                pattern.setMode(2);
                System.out.println("Found match 3 columns: " + symbol.getId() + " for pattern: " + pattern.getName());
                return symbol;
            }
        }

        return null;
    }

    public int getBinaryStringForSymbol(Symbol symbol) {
        StringBuilder binary = new StringBuilder();
        for (Symbol s : grid) {
            if (Objects.equals(s.getId(), symbol.getId()) || Objects.equals(s.getId(), wild.getId())) {
                binary.append("1");
            } else {
                binary.append("0");
            }
        }
        return Integer.parseInt(binary.toString(), 2);
    }

    public boolean hasNullSymbol() {
        for (Symbol s : grid) {
            if (s == null) {
                return true;
            }
        }
        return false;
    }
}
