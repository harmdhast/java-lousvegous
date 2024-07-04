package fr.esgi.lousvegous.symbol;

import javafx.scene.image.Image;

import java.util.Objects;

abstract public class Symbol {
    private final String id;
    private final float chance;
    private float[] multipliers;
    private Image image;

    public Symbol(String id, float chance, String image, float[] multipliers) {
        this.id = id;
        this.chance = chance;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/symbols/" + image + ".png")), 100, 100, true, true);
        this.multipliers = multipliers;
    }

    public float[] getMultipliers() {
        return multipliers;
    }

    public float getMultiplier(int idx) {
        return multipliers[idx];
    }

    public float getChance() {
        return chance;
    }

    public Image getImage() {
        return image;
    }

    public Image getImage(int width, int height) {
        this.image = new Image(image.getUrl(), width, height, true, true);
        return image;
    }

    public String getId() {
        return id;
    }
}
