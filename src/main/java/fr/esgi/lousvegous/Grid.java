package fr.esgi.lousvegous;

import fr.esgi.lousvegous.pattern.Pattern;
import fr.esgi.lousvegous.pattern.PatternManager;
import fr.esgi.lousvegous.symbol.S1;
import fr.esgi.lousvegous.symbol.Symbol;
import fr.esgi.lousvegous.symbol.SymbolManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public HashMap<Symbol, Pattern[]> getAllMatches() {
        if (hasNull()) {
            return null;
        }

        HashMap<Symbol, Pattern[]> matches = new HashMap<>();
        for (Symbol symbol : SymbolManager.getInstance().getSymbols()) {
            //System.out.printf("Checking for symbol %s\n", symbol.getId());
            Pattern[] patterns = getMatchesForSymbol(symbol);
            //System.out.printf("Found %d matches\n", patterns.length);
            if (patterns.length > 0) {
                matches.put(symbol, patterns);
            }
        }
        return matches;
    }

    public Pattern[] getMatchesForSymbol(Symbol symbol) {
        if (hasNull()) {
            return null;
        }

        int binary = gridToBinary(symbol);
        List<Pattern> matchs = new ArrayList<>();

        for (Pattern pattern : PatternManager.patterns) {
            if ((pattern.getBinary() & binary) == pattern.getBinary()) {
                System.out.println("Match: " + pattern.getPattern());
                matchs.add(pattern);
            }
        }

        return matchs.toArray(new Pattern[0]);
    }

    public int gridToBinary(Symbol symbol) {
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

    public boolean hasNull() {
        for (Symbol s : grid) {
            if (s == null) {
                return true;
            }
        }
        return false;
    }
}
