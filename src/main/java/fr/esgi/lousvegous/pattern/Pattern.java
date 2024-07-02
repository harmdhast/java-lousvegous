package fr.esgi.lousvegous.pattern;

import java.util.ArrayList;
import java.util.List;

public class Pattern {
    String pattern;
    int binary;
    List<Integer> indexes;

// list avec les index de la position des 1

    // Constructeur
    public Pattern(String pattern) {
        this.pattern = pattern.replaceAll("\\s", ""); // supprime les espaces
        binary = this.binaryToDecimal();
        indexes = this.getIndexOfOnes();
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
        return Integer.parseInt(this.pattern.substring(0, 8), 2) << 6;
    }

    public int fourColumns() {
        return Integer.parseInt(this.pattern.substring(0, 11), 2) << 3;
    }

    public Pattern asThreeColumns() {
        return new Pattern(decimalToBinary(threeColumns()));
    }

    public Pattern asFourColumns() {
        return new Pattern(decimalToBinary(fourColumns()));
    }

    // Converti le binaire en int
    public int binaryToDecimal() {
        return Integer.parseInt(this.pattern, 2);
    }

    // Converti le int en binaire
    public String decimalToBinary() {
        return Integer.toBinaryString(this.binary);
    }

    //
    public List<Integer> getIndexOfOnes() {
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < this.pattern.length(); i++) {
            if (this.pattern.charAt(i) == '1') {
                indexList.add(i);
            }
        }
        return indexList;
    }

    public int getBinary() {
        return binary;
    }

    public List<Integer> getIndexes() {
        return indexes;
    }

    public String getPattern() {
        return pattern;
    }
}
