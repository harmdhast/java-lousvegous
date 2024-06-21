package fr.esgi.lousvegous.ui.intro;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class IntroScene extends com.almasb.fxgl.app.scene.IntroScene {

    private static final int SIZE = 250;
    DropShadow dropShadow = new DropShadow();
    private List<Animation<?>> animations = new ArrayList<>();


    public IntroScene() {
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
                .from(50)
                .to(100)
                .build());


        // Create a vertical box that fill the whole screen and center its content
        var vBox = new VBox();
        vBox.setPrefSize(FXGL.getAppWidth(), FXGL.getAppHeight());
        vBox.setAlignment(javafx.geometry.Pos.CENTER);

        var label1 = new Label("to Fabulous");
        label1.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/LasVegasDemo.otf"), 50));
        label1.setStyle("-fx-text-fill: darkblue");
        label1.setTranslateY(-10.0);


        var logo = new Text("LousVegous");
        // Get font from resources
        logo.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/CasinoShadow-Italic.ttf"), 70));
        // Change logo color
        logo.setFill(Color.RED);
        logo.setStroke(Color.BLACK);
        logo.setStrokeWidth(1.0);


        var label2 = new Label("morbihan");
        label2.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/LasVegasDemo.otf"), 50));
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


        /*var circles = new Group(logo);

        circles.getChildren().forEach(c -> {
            var anim = FXGL.animationBuilder()
                    .duration(Duration.seconds(5))
                    .delay(Duration.seconds(10 * index++))
                    .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                    .scale(c)
                    .from(new Point2D(0, 0))
                    .to(new Point2D(1, 1))
                    .build();

            animations.add(anim);
        });*/

        //animations.getLast().setOnFinished(this::finishIntro);
        Pane splashApp = new Pane();
        splashApp.getChildren().addAll(
                bg,
                hBox,
                vBox
        );


//        Label label3 = new Label("Developed by");
//        label3.setFont(Font.loadFont(getClass().getResourceAsStream("/LasVegasDemo.otf"), 50));
//        label3.setStyle("-fx-text-fill: white");


        animations.add(FXGL.animationBuilder()
                .duration(Duration.seconds(3))
                //.repeatInfinitely()
                //.autoReverse(true)
                .interpolator(Interpolators.LINEAR.EASE_OUT())
                .animate(rectangle.xProperty())
                .from(0)
                .to(800)
                .build());


        getContentRoot().getChildren().addAll(
                new Rectangle(FXGL.getAppWidth(), FXGL.getAppHeight()),
                splashDev
        );


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
        Text text = new Text(String.valueOf(letter));
        text.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        text.setEffect(dropShadow);

        animations.add(FXGL.animationBuilder()
                .duration(Duration.seconds(1))
                .repeatInfinitely()
                .autoReverse(true)
                .interpolator(Interpolators.PERLIN.EASE_OUT())
                .animate(dropShadow.radiusProperty())
                .from(0)
                .to(10)
                .build());

        text.setFill(Color.ORANGERED);

        box.getChildren().add(circle);
        box.getChildren().add(text);

        return box;
    }

    @Override
    protected void onUpdate(double tpf) {
        animations.forEach(a -> a.onUpdate(tpf));
    }

    @Override
    public void startIntro() {
        animations.forEach(Animation::start);
        dropShadow.setColor(Color.RED);
    }
}