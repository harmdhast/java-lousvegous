package fr.esgi.lousvegous.pattern;

import java.util.ArrayList;
import java.util.List;

public class PatternManager {
    public static List<Pattern> patterns = new ArrayList<>();

    private PatternManager() {
        // Ajouter les patterns ici
        addPattern(new Pattern("01101010101111"));
    }

    public void addPattern(Pattern pattern) {
        patterns.add(pattern);
    }
}
