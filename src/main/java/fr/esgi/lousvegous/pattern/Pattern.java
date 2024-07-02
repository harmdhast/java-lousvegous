package fr.esgi.lousvegous.pattern;

import java.util.ArrayList;
import java.util.List;

public class Pattern {
    String pattern;
    int binary;
    List<Integer> indexes;

// list avec les index de la position des 1

    // Constructeur
    Pattern(String pattern) {
        this.pattern = pattern.replaceAll("\\s", ""); // supprime les espaces
        binary = this.binaryToDecimal();
        indexes = this.getIndexOfOnes();
    }

    // Converti le binaire en int
    public int binaryToDecimal() {
        return Integer.parseInt(this.pattern, 2);
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
