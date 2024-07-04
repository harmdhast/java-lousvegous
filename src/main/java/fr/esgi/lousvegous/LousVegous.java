package fr.esgi.lousvegous;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import fr.esgi.lousvegous.grid.Grid;
import fr.esgi.lousvegous.grid.GridView;
import fr.esgi.lousvegous.intro.IntroScene;
import fr.esgi.lousvegous.login.Login;
import fr.esgi.lousvegous.login.LoginView;
import fr.esgi.lousvegous.player.Player;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class LousVegous extends GameApplication {
    public static List<Animation<?>> animations = new ArrayList<>();
    ImageView[] displayGrid = new ImageView[15];
    Grid grid = Grid.getInstance();

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

        FXGL.loopBGM("bgm.mp3");

        // Create default player
        Login.register("admin", "admin");
        Player p = Login.login("admin", "admin");
        Player.setCurrentPlayer(p);

        // Move to login screen
        LoginView.moveTo();

        GridView.moveTo();
    }

    @Override
    protected void onUpdate(double tpf) {
        animations.forEach(a -> a.onUpdate(tpf));
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
        vars.put("lastWinning", 0.0);
        vars.put("total_rolls", 0);
        vars.put("total_wins", 0);
        vars.put("best_win", 0);
        vars.put("time_played", 0);
    }
}