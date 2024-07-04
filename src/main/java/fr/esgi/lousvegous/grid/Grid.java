package fr.esgi.lousvegous.grid;

import fr.esgi.lousvegous.pattern.Pattern;
import fr.esgi.lousvegous.pattern.PatternManager;
import fr.esgi.lousvegous.symbol.Symbol;
import fr.esgi.lousvegous.symbol.SymbolManager;
import fr.esgi.lousvegous.symbol.list.S1;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Objects;

public class Grid {
    private static final Symbol wild = new S1();
    private static final Symbol[] grid = new Symbol[15];
    private static final ImageView[] displayGrid = new ImageView[15];
    private static final DoubleProperty currentMultiplier = new SimpleDoubleProperty(0);
    private static Grid instance = null;

    static {
        for (int i = 0; i < 15; i++) {
            displayGrid[i] = new ImageView();
        }
    }

    public static ImageView[] getDisplayGrid() {
        return displayGrid;
    }

    public static ImageView getDisplayGrid(int index) {
        return displayGrid[index];
    }

    public static Symbol getSymbol(int index) {
        return grid[index];
    }

    public static Grid getInstance() {
        if (instance == null) {
            instance = new Grid();
        }
        return instance;
    }

    public static DoubleProperty currentMultiplierProperty() {
        return currentMultiplier;
    }

    public static void addMultiplier(double multiplier) {
        currentMultiplier.set(Math.round((currentMultiplier.get() + multiplier) * 10) / 10.0);
    }

    public static void resetMultiplier() {
        currentMultiplier.set(0);
    }

    public static double getCurrentMultiplier() {
        return currentMultiplier.get();
    }

    public static void setCurrentMultiplier(double currentMultiplier) {
        Grid.currentMultiplier.set(currentMultiplier);
    }

    public void setGridSymbol(int index, Symbol symbol) {
        grid[index] = symbol;
        displayGrid[index].setImage(symbol.getImage());
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
