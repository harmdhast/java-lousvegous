package fr.esgi.lousvegous;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import fr.esgi.lousvegous.login.LoginView;
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

        boolean debugLogin = true;
        if (debugLogin) {
            getGameScene().getRoot().getChildren().add(LoginView.getView());
            return;
        }


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
        Button button = (Button) e.getSource();
        button.setDisable(true);
        int idx = 0;
        Symbol[] test = new Symbol[15];
        test[0] = SymbolManager.getInstance().getSymbols().get(1);
        test[1] = SymbolManager.getInstance().getSymbols().get(2);
        test[2] = SymbolManager.getInstance().getSymbols().get(8);
        test[3] = test[0];
        test[4] = SymbolManager.getInstance().getSymbols().get(2);
        test[5] = SymbolManager.getInstance().getSymbols().get(8);
        test[6] = test[0];
        test[7] = SymbolManager.getInstance().getSymbols().get(2);
        test[8] = SymbolManager.getInstance().getSymbols().get(8);
        test[9] = test[0];
        test[10] = SymbolManager.getInstance().getSymbols().get(8);
        test[11] = SymbolManager.getInstance().getSymbols().get(8);
        test[12] = test[0];
        test[13] = SymbolManager.getInstance().getSymbols().get(7);
        test[14] = SymbolManager.getInstance().getSymbols().get(7);

        int i = 0;
        for (ImageView imageView : displayGrid) {
            // Symbol symbol = SymbolManager.getInstance().getRandomSymbol();
            Symbol symbol = test[i];
            imageView.setImage(symbol.getImage());
            grid.setGridSymbol(idx, symbol);
            idx++;
            i++;
        }


        processLoop(button);
    }

    public void processLoop(Button button) {
        int toDestroy = processPatterns();
        startAnimations(button, toDestroy);
    }

    private void startAnimations(Button button, int toDestroy) {
        if (animations.isEmpty()) {
            button.setDisable(false);
        } else {
            animations.forEach(Animation::start);
            animations.getLast().setOnFinished(() -> {
                animations = new ArrayList<>();
                replaceSymbols(toDestroy);
                processLoop(button);
            });
        }
    }

    private void replaceSymbols(int toDestroy) {
        System.out.println(toDestroy);
        System.out.println(Pattern.decimalToBinary(toDestroy));
        System.out.println(Pattern.getIndexOfOnes(Pattern.decimalToBinary(toDestroy)));
        Pattern.getIndexOfOnes(Pattern.decimalToBinary(toDestroy)).forEach(i -> {
            Symbol symbol = SymbolManager.getInstance().getRandomSymbol();
            displayGrid[i].setImage(symbol.getImage());
            grid.setGridSymbol(i, symbol);
        });
    }

    private void addAnimationToIndex(int index, double delay) {
        Animation animation = FXGL.animationBuilder().duration(Duration.seconds(1))
                .delay(Duration.seconds(delay))
                .autoReverse(true)
                .interpolator(Interpolators.LINEAR.EASE_OUT())
                .animate(displayGrid[index].rotateProperty())
                .from(0.0)
                .to(360.0)
                .build();
        animations.add(animation);
    }

    private int processPatterns() {
        HashMap<Pattern, Symbol> matches = grid.getAllMatches();
        int toDestroy = 0;
        double delay = 0;
        for (Pattern pattern : matches.keySet()) {
            toDestroy = toDestroy | pattern.getBinary();
            double finalDelay = delay;
            pattern.getIndexOfOnes().forEach(i -> addAnimationToIndex(i, finalDelay));
            delay += 1;
        }

        return toDestroy;
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