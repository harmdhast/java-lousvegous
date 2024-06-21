package fr.esgi.lousvegous.ui.intro;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class SplashDev {
    private List<Animation<?>> animations = new ArrayList<>();

    SplashDev() {
        StackPane vBox1 = new StackPane();
        vBox1.setPrefSize(FXGL.getAppWidth(), FXGL.getAppHeight());
        vBox1.setAlignment(Pos.CENTER);

        Label label4 = new Label("Twenty-Seven Club");
        label4.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Sunday Chillin.ttf"), 80));
        label4.setStyle("-fx-text-fill: linear-gradient(to right, rgba(255,0,0,1) 0%, rgba(255,154,0,1) 10%, rgba(208,222,33,1) 20%, rgba(79,220,74,1) 30%, rgba(63,218,216,1) 40%, rgba(47,201,226,1) 50%, rgba(28,127,238,1) 60%, rgba(95,21,242,1) 70%, rgba(186,12,248,1) 80%, rgba(251,7,217,1) 100%);");
        label4.setScaleX(1.5);

        Label label5 = new Label("A GAME BY");
        label5.setStyle("-fx-text-fill: white");
        label5.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/LEMONMILK-Bold.otf"), 20));
        label5.setTranslateY(-40);
        label5.setTranslateX(-220.0);

        Rectangle rectangle = new Rectangle(800, 400);
        rectangle.setFill(Color.BLACK);

        vBox1.getChildren().addAll(label5, label4);

        Pane splashDev = new Pane();
        splashDev.getChildren().addAll(
                vBox1,
                rectangle
        );
    }
}
