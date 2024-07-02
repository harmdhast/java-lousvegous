package fr.esgi.lousvegous;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import fr.esgi.lousvegous.pattern.Pattern;
import fr.esgi.lousvegous.symbol.Symbol;
import fr.esgi.lousvegous.symbol.SymbolManager;
import fr.esgi.lousvegous.ui.intro.IntroScene;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class HelloApplication extends GameApplication {
    ImageView[] displayGrid = new ImageView[15];
    Grid grid = Grid.getInstance();
    List<Animation<?>> animations = new ArrayList<>();

    public static void main(String[] args) {
        //  SymbolManager symbolManager = new SymbolManager();

        //  Random random = new Random();
        //  for (int i = 0; i < 20; i++) {
        //      System.out.print(random.getRandomSymbol().getImage());
        //  }
        Font.loadFont(HelloApplication.class.getResourceAsStream("/fonts/LEMONMILK-Bold.otf"), 10);
        Font.loadFont(HelloApplication.class.getResourceAsStream("/fonts/Sunday Chillin.ttf"), 10);
        Font.loadFont(HelloApplication.class.getResourceAsStream("/fonts/LasVegasDemo.otf"), 10);
        Font.loadFont(HelloApplication.class.getResourceAsStream("/fonts/CasinoShadow-Italic.ttf"), 10);

        launch(args);
    }

//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("lousvegous.css")).toExternalForm());
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }

    @Override
    protected void initGame() {
        // Create a rectangle background
        Background bg = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
        getGameScene().getRoot().setBackground(bg);

        // Create a new GridPane
        GridPane playingGrid = new GridPane();
        playingGrid.getStyleClass().add("playing-grid");
        playingGrid.setGridLinesVisible(true);
        playingGrid.setPrefSize(300, 500);
        playingGrid.setPadding(new Insets(10, 10, 10, 10));
        // set columns and rows to be the same size
        // Center items in grid
        playingGrid.setAlignment(javafx.geometry.Pos.CENTER);


        // Define the size of the grid
        int numColumns = 5;
        int numRows = 3;

        // Create the cells of the grid
        int idx = 0;
        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                ImageView imageView = new ImageView();
                //Image image = SymbolManager.getInstance().getRandomSymbol().getImage();
                //imageView.setImage(image);
                displayGrid[idx] = imageView;

                // Add the rectangle (cell) to the grid
                playingGrid.add(imageView, i, j);
                idx++;
            }
        }

        // Create button
        Button button = new Button("SPIN");
        button.setPrefWidth(100);
        button.setPrefHeight(50);
        button.setStyle("-fx-font-size: 20px;");
        button.setOnAction(e -> spin(e));

        // Create Vbox
        VBox vbox = new VBox();
        vbox.setPrefSize(800, 600);
        vbox.setSnapToPixel(true);

        // Create Hbox
        HBox hbox = new HBox();
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        hbox.getChildren().add(playingGrid);
        hbox.getChildren().add(button);

        Text text = new Text("LousVegous");
        text.setFont(Font.font("Casino Shadow", 100));
        text.setFill(Color.YELLOW);


        vbox.getChildren().addAll(text, hbox);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        // center the grid
        vbox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

        // Add the grid to the game's root (or another parent node)
        getGameScene().getRoot().getChildren().add(vbox);

    }

    @Override
    protected void onUpdate(double tpf) {
        animations.forEach(a -> a.onUpdate(tpf));
    }

    public void spin(ActionEvent e) {
        Button source = (Button) e.getSource();
        source.setDisable(true);
        int idx = 0;
        for (ImageView imageView : displayGrid) {
            Symbol symbol = SymbolManager.getInstance().getRandomSymbol();
            imageView.setImage(symbol.getImage());
            grid.setGridSymbol(idx, symbol);
            idx++;
        }

        //List<Animation<?>> animations = new ArrayList<>();
        HashMap<Symbol, Pattern[]> matches = grid.getAllMatches();
        for (Symbol symbol : matches.keySet()) {
            double delay = 0;
            for (Pattern pattern : matches.get(symbol)) {
                //DropShadow dropShadow = new DropShadow(20, Color.GREEN);

                double finalDelay = delay;
                pattern.getIndexes().forEach(i -> {
                    //displayGrid[i].setEffect(dropShadow);
                    Animation animation = FXGL.animationBuilder().duration(Duration.seconds(1))
                            .delay(Duration.seconds(finalDelay))
                            .autoReverse(true)
                            .interpolator(Interpolators.LINEAR.EASE_OUT())
                            .animate(displayGrid[i].rotateProperty())
                            .from(0.0)
                            .to(360.0)
                            .build();
                    animations.add(animation);
                });
                //wait for 1 second
                //displayGrid[i].setEffect(new javafx.scene.effect.DropShadow(20, Color.GREEN))
                // pattern.getIndexes().forEach(i -> displayGrid[i].setEffect(null));
                delay += 1;
                // System.out.println("Matched " + symbol + " with pattern " + pattern);
            }

        }

        if (animations.isEmpty()) {
            source.setDisable(false);
        } else {
            animations.forEach(Animation::start);
            animations.getLast().setOnFinished(() -> {
                animations = new ArrayList<>();
                source.setDisable(false);
            });
        }
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(600);
        gameSettings.setTitle("LousVegous");
        gameSettings.setVersion("0.1");
        gameSettings.setIntroEnabled(false);
        gameSettings.setDefaultCursor(new CursorInfo("dollar.gif", 0, 0));
        gameSettings.setSceneFactory(new SceneFactory() {
            @NotNull
            @Override
            public com.almasb.fxgl.app.scene.IntroScene newIntro() {
                return new IntroScene();
            }
        });
    }
}