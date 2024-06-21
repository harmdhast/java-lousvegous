package fr.esgi.lousvegous.ui;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.IntroScene;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class MyIntroScene extends IntroScene {

    private static final int SIZE = 150;

    private List<Animation<?>> animations = new ArrayList<>();

    private int index = 0;

    public MyIntroScene() {
        var circle1 = new Circle(SIZE, SIZE, SIZE, Color.color(0.94, 0.5, 0.73, 0.6));
        var circle2 = new Circle(SIZE, SIZE, SIZE, Color.color(0.4, 0.5, 0.33, 0.6));
        circle2.setTranslateX(300);
        circle2.setTranslateY(250);
        var circle3 = new Circle(SIZE, SIZE, SIZE, Color.color(0.14, 0.95, 0.13, 0.6));
        circle3.setTranslateX(500);
        circle3.setTranslateY(50);

        var circles = new Group(circle1, circle2, circle3);

        circles.getChildren().forEach(c -> {
            var anim = FXGL.animationBuilder()
                    .duration(Duration.seconds(1.66))
                    .delay(Duration.seconds(1.66 * index++))
                    .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                    .scale(c)
                    .from(new Point2D(0, 0))
                    .to(new Point2D(1, 1))
                    .build();

            animations.add(anim);
        });

        animations.getLast().setOnFinished(this::finishIntro);

        getContentRoot().getChildren().addAll(
                new Rectangle(FXGL.getAppWidth(), FXGL.getAppHeight()),
                circles
        );
    }

    @Override
    protected void onUpdate(double tpf) {
        animations.forEach(a -> a.onUpdate(tpf));
    }

    @Override
    public void startIntro() {
        animations.forEach(Animation::start);
    }
}