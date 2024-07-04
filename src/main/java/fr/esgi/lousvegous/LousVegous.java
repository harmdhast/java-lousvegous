package fr.esgi.lousvegous;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import fr.esgi.lousvegous.grid.Grid;
import fr.esgi.lousvegous.grid.GridView;
import fr.esgi.lousvegous.intro.IntroScene;
import fr.esgi.lousvegous.login.Login;
import fr.esgi.lousvegous.pattern.Pattern;
import fr.esgi.lousvegous.player.Player;
import fr.esgi.lousvegous.symbol.Symbol;
import fr.esgi.lousvegous.symbol.SymbolManager;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class LousVegous extends GameApplication {
    ImageView[] displayGrid = new ImageView[15];
    Grid grid = Grid.getInstance();
    List<Animation<?>> animations = new ArrayList<>();

    public static void main(String[] args) {
        Font.loadFont(LousVegous.class.getResourceAsStream("/fonts/LEMONMILK-Bold.otf"), 10);
        Font.loadFont(LousVegous.class.getResourceAsStream("/fonts/Sunday Chillin.ttf"), 10);
        Font.loadFont(LousVegous.class.getResourceAsStream("/fonts/LasVegasDemo.otf"), 10);
        Font.loadFont(LousVegous.class.getResourceAsStream("/fonts/CasinoShadow-Italic.ttf"), 10);

        launch(args);
    }

    @Override
    protected void initGame() {
        // Set the background color of the game
        Background bg = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
        getGameScene().getRoot().setBackground(bg);

        // Create default player
        Login.register("admin", "admin");
        Player p = Login.login("admin", "admin");
        Player.setCurrentPlayer(p);

        // Move to login screen
        //LoginView.moveTo();

        GridView.moveTo();
    }

    @Override
    protected void onUpdate(double tpf) {
        this.animations.forEach(a -> a.onUpdate(tpf));
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
                System.out.println("Finished");
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

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("username", "");
        vars.put("last_login", LocalDateTime.now());
        vars.put("balance", 0.0);
        vars.put("total_rolls", 0);
        vars.put("total_wins", 0);
        vars.put("best_win", 0);
        vars.put("time_played", 0);
    }
}