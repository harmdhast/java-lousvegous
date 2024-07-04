package fr.esgi.lousvegous.pattern;

import fr.esgi.lousvegous.symbol.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Pattern {
    String pattern;
    String name;
    int binary;
    List<Integer> indexes;
    int mode = 0; // 0 = 5 colonnes, 1 = 4 colonnes, 2 = 3 colonnes

// list avec les index de la position des 1

    // Constructeur
    public Pattern(String pattern, String name) {
        this.pattern = pattern.replaceAll("\\s", ""); // supprime les espaces
        binary = this.binaryToDecimal();
        indexes = this.getIndexOfOnes();
        this.name = name;
    }

    public Pattern(Pattern pattern) {
        this.pattern = pattern.pattern;
        this.binary = pattern.binary;
        this.indexes = pattern.indexes;
        this.name = pattern.name;
    }

    public static List<Integer> getIndexOfOnes(String pattern) {
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '1') {
                indexList.add(i);
            }
        }
        return indexList;
    }

    public static String decimalToBinary(int decimal) {
        return Integer.toBinaryString(decimal);
    }

    public int threeColumns() {
        return Integer.parseInt(this.pattern.substring(0, 9), 2) << 6;
    }

    public int fourColumns() {
        return Integer.parseInt(this.pattern.substring(0, 12), 2) << 3;
    }

    // Converti le binaire en int
    public int binaryToDecimal() {
        return Integer.parseInt(this.pattern, 2);
    }

    // Converti le int en binaire
    public String decimalToBinary() {
        return Integer.toBinaryString(this.binary);
    }

    public List<Integer> getIndexOfOnes() {
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < this.pattern.length() - 3 * mode; i++) {
            if (this.pattern.charAt(i) == '1') {
                indexList.add(i);
            }
        }
        return indexList;
    }

    public int getBinary() {
        return switch (mode) {
            case 1 -> fourColumns();
            case 2 -> threeColumns();
            default -> binary;
        };
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public List<Integer> getIndexes() {
        return indexes;
    }

    public String getPattern() {
        // Pad with 0
        return String.format("%15s", Integer.toBinaryString(getBinary())).replace(' ', '0');
    }

    public String getName() {
        return name;
    }

    public float getMultiplier(Symbol symbol) {
        return switch (mode) {
            case 1 -> symbol.getMultiplier(1);
            case 2 -> symbol.getMultiplier(0);
            default -> symbol.getMultiplier(2);
        };
    }
}
