package fr.esgi.lousvegous.intro;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class SplashApp implements Splash {

    private static final int SIZE = 250;
    private final List<Animation<?>> animations = new ArrayList<>();
    private final Pane splashApp = new Pane();

    SplashApp() {
        this.splashApp.getChildren().addAll(LousVegousLogo());

        FXGL.play("coins.mp3");
        FXGL.play("casino-ambiance.mp3");
    }

    public Node LousVegousLogo() {
        // Create a polygon shape eclipse
        Path bg = new Path();
        bg.getElements().addAll(
                new MoveTo(0, 0),
                new CubicCurveTo(0, 0, SIZE * 3, (double) SIZE / 2, 0, SIZE),
                new CubicCurveTo(0, SIZE, -SIZE * 3, (double) SIZE / 2, 0, 0)
        );
        bg.setFill(Color.WHITE);
        bg.setTranslateX(FXGL.getAppWidth() / 2.0);
        bg.setTranslateY(FXGL.getAppHeight() / 2.0 - SIZE / 2.0);
        bg.setStroke(Color.web("#ffb703"));
        bg.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        bg.setStrokeWidth(15.0);
        DropShadow dropShadow1 = new DropShadow(100, Color.CYAN);
        bg.setEffect(dropShadow1);

        animations.add(FXGL.animationBuilder()
                .duration(Duration.seconds(2))
                .repeatInfinitely()
                .autoReverse(true)
                .interpolator(Interpolators.PERLIN.EASE_OUT())
                .animate(dropShadow1.radiusProperty())
                .from(50.0)
                .to(100.0)
                .build());

        // Create a vertical box that fill the whole screen and center its content
        var vBox = new VBox();
        vBox.setPrefSize(FXGL.getAppWidth(), FXGL.getAppHeight());
        vBox.setAlignment(javafx.geometry.Pos.CENTER);

        var label1 = new Label("to Fabulous");
        label1.setFont(Font.font("Las VegasDemo", 50));
        label1.setStyle("-fx-text-fill: darkblue");
        label1.setTranslateY(-10.0);

        var logo = new Text("LousVegous");
        // Get font from resources
        logo.setFont(Font.font("Casino Shadow", 70));
        // Change logo color
        logo.setFill(Color.RED);
        logo.setStroke(Color.BLACK);
        logo.setStrokeWidth(1.0);


        var label2 = new Label("morbihan");
        label2.setFont(Font.font("Las VegasDemo", 50));
        label2.setStyle("-fx-text-fill: darkblue");

        HBox hBox = new HBox();
        hBox.setSpacing(3.0);
        hBox.setTranslateY(FXGL.getAppHeight() / 2.0 - 150.0);
        hBox.setPrefSize(FXGL.getAppWidth(), 50.0);
        hBox.setAlignment(Pos.CENTER);
        for (var letter : "welcome".toUpperCase().toCharArray()) {
            hBox.getChildren().add(createDisplayableUnit(letter));
        }

        vBox.getChildren().add(label1);
        vBox.getChildren().add(logo);
        vBox.getChildren().add(label2);

        Pane pane = new Pane();
        pane.getChildren().addAll(bg, hBox, vBox);
        return pane;
    }

    private void stopAllSounds() {
        double volume = FXGL.getSettings().getGlobalMusicVolume();
        FXGL.animationBuilder()
                .duration(Duration.seconds(1))
                .onFinished(() -> {
                    FXGL.getAudioPlayer().stopAllSoundsAndMusic();
                    FXGL.getSettings().setGlobalMusicVolume(volume);
                })
                .animate(FXGL.getSettings().globalMusicVolumeProperty())
                .from(FXGL.getSettings().getGlobalMusicVolume())
                .to(0.0)
                .buildAndPlay();
    }

    public Pane createDisplayableUnit(char letter) {
        // Create a container with a circle and a letter inside
        StackPane box = new StackPane();
        box.setPrefSize(40, 40);
        box.setAlignment(Pos.CENTER);

        // Create a circle as the background
        Circle circle = new Circle(30);
        circle.setFill(Color.WHITE);
        circle.setCenterX(20);
        circle.setCenterY(20);
        circle.setStroke(Color.ORANGE);
        circle.setStrokeWidth(4.0);

        // Create a text node with the input character
        DropShadow dropShadow = new DropShadow(10.0, 0.0, 0.0, Color.RED);
        Text text = new Text(String.valueOf(letter));
        text.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        text.setEffect(dropShadow);

        Animation<Number> anim = FXGL.animationBuilder()
                .duration(Duration.seconds(1))
                .repeat(10)
                .autoReverse(true)
                .interpolator(Interpolators.PERLIN.EASE_OUT())
                .animate(dropShadow.radiusProperty())
                .from(2.0)
                .to(10.0)
                .build();

        anim.setOnFinished(this::stopAllSounds);

        animations.add(anim);

        text.setFill(Color.ORANGERED);

        box.getChildren().add(circle);
        box.getChildren().add(text);

        return box;
    }

    public Pane getPane() {
        return splashApp;
    }

    public List<Animation<?>> getAnimations() {
        return this.animations;
    }
}
