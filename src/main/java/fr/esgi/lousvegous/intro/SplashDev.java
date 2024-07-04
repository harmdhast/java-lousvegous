package fr.esgi.lousvegous.intro;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class SplashDev implements Splash {
    private final List<Animation<?>> animations = new ArrayList<>();
    private final Pane splashDev = new Pane();

    SplashDev() {
        StackPane container = new StackPane();
        container.setPrefSize(FXGL.getAppWidth(), FXGL.getAppHeight());
        container.setAlignment(Pos.CENTER);

        Label devName = new Label("Twenty-Seven Club");
        devName.setFont(Font.font("Sunday Chillin", 80));
        devName.setStyle("-fx-text-fill: linear-gradient(to right, rgba(255,0,0,1) 0%, rgba(255,154,0,1) 10%, rgba(208,222,33,1) 20%, rgba(79,220,74,1) 30%, rgba(63,218,216,1) 40%, rgba(47,201,226,1) 50%, rgba(28,127,238,1) 60%, rgba(95,21,242,1) 70%, rgba(186,12,248,1) 80%, rgba(251,7,217,1) 100%);");
        devName.setScaleX(1.5);

        Label aGameBy = new Label("A GAME BY");
        aGameBy.setStyle("-fx-text-fill: white");
        aGameBy.setFont(Font.font("LEMON MILK Bold", 20));
        aGameBy.setTranslateY(-40);
        aGameBy.setTranslateX(-220.0);

        Rectangle hider = new Rectangle(800, 400);
        hider.setFill(Color.BLACK);

        container.getChildren().addAll(aGameBy, devName);

        this.splashDev.getChildren().addAll(
                container,
                hider
        );

        animations.add(FXGL.animationBuilder()
                .duration(Duration.seconds(3))
                .interpolator(Interpolators.LINEAR.EASE_OUT())
                .animate(hider.xProperty())
                .from(0)
                .to(800)
                .build());

        animations.add(FXGL.animationBuilder()
                .duration(Duration.seconds(0))
                .interpolator(Interpolators.LINEAR.EASE_OUT())
                .delay(Duration.seconds(5))
                .animate(hider.yProperty())
                .build());
        FXGL.play("sm64_mario_falling.wav");
    }

    public Pane getPane() {
        return this.splashDev;
    }

    public List<Animation<?>> getAnimations() {
        return this.animations;
    }
}
