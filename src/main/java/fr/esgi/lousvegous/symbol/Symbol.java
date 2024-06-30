package fr.esgi.lousvegous.symbol;

import javafx.scene.image.Image;

import java.util.Objects;

abstract class Symbol {
    private final String id;
    private final float chance;
    private final float[] multipliers;
    private Image image;

    public Symbol(String id, float chance, String image, float[] multipliers) {
        this.id = id;
        this.chance = chance;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/symbols/" + image + ".png")), 50, 50, true, true);
        this.multipliers = multipliers;
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
}
