package fr.esgi.lousvegous.pattern;

import java.util.List;

public class PatternManager {
    public static List<Pattern> patterns = List.of(
            new Pattern("100 100 100 100 100"), // top line
            new Pattern("010 010 010 010 010"), // middle line
            new Pattern("001 001 001 001 001"), // bottom line
            new Pattern("100 010 001 010 100"), // V
            new Pattern("001 010 100 010 001"), // reverse V
            new Pattern("010 100 100 100 010"), // casque diamant
            new Pattern("001 010 010 010 001"), // middle casque cuir vert
            new Pattern("010 001 001 001 010"), // bottom cup
            new Pattern("100 010 010 010 100"), // top cup
            new Pattern("100 100 010 001 001"), // hélice
            new Pattern("001 001 010 100 100"), // reverse hélice
            new Pattern("010 001 010 100 010"), // zig-zag
            new Pattern("010 100 010 001 010"), // reverse zig-zag
            new Pattern("100 010 100 010 100"), // top damier
            new Pattern("001 010 001 010 001"), // bottom damier
            new Pattern("010 010 100 010 010"), // --|--
            new Pattern("010 010 001 010 010"), // reverse --|--
            new Pattern("100 100 001 100 100"), // --_--
            new Pattern("001 001 100 001 001"), // reverse --_--
            new Pattern("100 001 001 001 100"), // -___-
            new Pattern("001 100 100 100 001"), // reverse -___-
            new Pattern("010 001 100 001 010"), // whither
            new Pattern("010 100 001 100 010"), // reverse wither
            new Pattern("100 001 100 001 100"), // -_-_-
            new Pattern("001 100 001 100 001")  // reverse -_-_-
    );
    private static PatternManager instance = null;

    public static void main(String[] args) {
        for (Pattern pattern : patterns) {
            System.out.println("Pattern: " + pattern.pattern);
            System.out.println("Indices des 1: " + pattern.getIndexOfOnes());
        }
    }

    public static PatternManager getInstance() {
        if (instance == null) {
            instance = new PatternManager();
        }
        return instance;
    }

}
