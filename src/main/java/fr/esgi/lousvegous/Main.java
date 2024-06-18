package fr.esgi.lousvegous;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Symbol> symbols = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Starting application");
    }

    public void defineSymbols() {
        symbols.add(new Symbol("s1", (float) 1 / 16, "7\uFE0F", new float[]{4, 10, 20}));
        symbols.add(new Symbol("s2", (float) 1 / 12, "\uD83C\uDF4D", new float[]{1.6f, 6, 10}));
        symbols.add(new Symbol("s3", (float) 1 / 8, "\uD83C\uDF50", new float[]{1, 4, 6}));
        symbols.add(new Symbol("s4", (float) 1 / 6, "\uD83C\uDF49", new float[]{.6f, 3, 5}));
        symbols.add(new Symbol("s5", (float) 1 / 5, "\uD83C\uDF4E", new float[]{.4f, 2, 4}));
        symbols.add(new Symbol("s6", (float) 1 / 4, "\uD83C\uDF48", new float[]{.28f, 1.2f, 3}));
        symbols.add(new Symbol("s7", (float) 1 / 3, "\uD83C\uDF52", new float[]{.2f, .8f, 2.4f}));
        symbols.add(new Symbol("s8", (float) 1 / 3, "\uD83C\uDF51", new float[]{.16f, .6f, 2}));
        symbols.add(new Symbol("s9", (float) 1 / 3, "\uD83C\uDF46", new float[]{.12f, .4f, 1.6f}));
    }

}
