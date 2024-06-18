package fr.esgi.lousvegous;

public class Symbol {
    private final String id;
    private final float chance;
    private final String image;
    private final float[] multipliers;

    public Symbol(String id, float chance, String image, float[] multipliers) {
        this.id = id;
        this.chance = chance;
        this.image = image;
        this.multipliers = multipliers;
    }

    public float getChance() {
        return chance;
    }

    public String getImage() {
        return image;
    }
}
