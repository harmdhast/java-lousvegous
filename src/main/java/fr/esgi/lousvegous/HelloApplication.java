package fr.esgi.lousvegous;

import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.SceneFactory;
import fr.esgi.lousvegous.ui.intro.IntroScene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class HelloApplication extends GameApplication {
    Rectangle[] rectangles = new Rectangle[15];

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
        playingGrid.setPrefSize(400, 400);

        // Define the size of the grid
        int numColumns = 5;
        int numRows = 3;

        // Create the cells of the grid
        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                Rectangle rect = new Rectangle(60, 60); // adjust size as needed
                rectangles[i * numColumns + j] = rect;
                rect.setStroke(Color.BLACK); // grid line color
                rect.setFill(Color.WHITE); // cell color

                // Add the rectangle (cell) to the grid
                playingGrid.add(rect, i, j);
            }
        }

        // Create button
        Button button = new Button("Randomize");
        button.setOnAction(e -> changeColorOnClick());

        // Create Vbox
        VBox vbox = new VBox();
        vbox.getChildren().add(playingGrid);
        vbox.getChildren().add(button);
        vbox.setSpacing(10);


        // Add the grid to the game's root (or another parent node)
        getGameScene().getRoot().getChildren().add(vbox);

    }

    public void changeColorOnClick() {
        for (Rectangle rectangle : rectangles) {
            int red = (int) (Math.random() * 256);
            int green = (int) (Math.random() * 256);
            int blue = (int) (Math.random() * 256);
            Color randomColor = Color.rgb(red, green, blue);
            // Set the fill color of the rectangle to the random color
            rectangle.setFill(randomColor);
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
        gameSettings.setIntroEnabled(false);
        gameSettings.setSceneFactory(new SceneFactory() {
            @NotNull
            @Override
            public com.almasb.fxgl.app.scene.IntroScene newIntro() {
                return new IntroScene();
            }
        });


    }


}