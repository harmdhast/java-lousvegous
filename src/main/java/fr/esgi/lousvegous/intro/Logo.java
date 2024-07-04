package fr.esgi.lousvegous.intro;

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

public class Logo {
    private static final int SIZE = 250;

    public static Node getLogo(double scale) {
        // Create a polygon shape eclipse
        Path bg = new Path();
        bg.getElements().addAll(
                new MoveTo(0, 0),
                new CubicCurveTo(0, 0, SIZE * 3, (double) SIZE / 2, 0, SIZE),
                new CubicCurveTo(0, SIZE, -SIZE * 3, (double) SIZE / 2, 0, 0)
        );
        bg.setFill(Color.WHITE);
        //bg.setTranslateX(FXGL.getAppWidth() / 2.0);
        //bg.setTranslateY(FXGL.getAppHeight() / 2.0 - SIZE / 2.0);
        bg.setStroke(Color.web("#ffb703"));
        bg.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        bg.setStrokeWidth(15.0);
        DropShadow dropShadow1 = new DropShadow(100, Color.CYAN);
        bg.setEffect(dropShadow1);


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
        //hBox.setTranslateY(FXGL.getAppHeight() / 2.0 - 150.0);
        //hBox.setPrefSize(FXGL.getAppWidth(), 50.0);
        hBox.setAlignment(Pos.CENTER);
        for (var letter : "welcome".toUpperCase().toCharArray()) {
            hBox.getChildren().add(createDisplayableUnit(letter));
        }
        hBox.setTranslateY(-125);


        VBox vBox2 = new VBox();
        vBox2.setAlignment(Pos.CENTER);
        vBox2.getChildren().addAll(label1, logo, label2);

        // Create a vertical box that fill the whole screen and center its content
        var vBox = new StackPane();
        //vBox.setPrefSize(250, 200);
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.getChildren().addAll(hBox, vBox2);
        //vBox.setSpacing(25);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(bg, vBox);

        Pane pane = new Pane();
        pane.getChildren().addAll(stackPane);
        pane.setScaleX(scale);
        pane.setScaleY(scale);
        return pane;
    }

    public static Pane createDisplayableUnit(char letter) {
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
        text.setFill(Color.ORANGERED);

        box.getChildren().add(circle);
        box.getChildren().add(text);

        return box;
    }
}
