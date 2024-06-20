package fr.esgi.lousvegous;

import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.IntroScene;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import fr.esgi.lousvegous.ui.MyIntroScene;
import org.jetbrains.annotations.NotNull;

public class HelloApplication extends GameApplication {
    public static void main(String[] args) {
        //  SymbolManager symbolManager = new SymbolManager();

        //  Random random = new Random();
        //  for (int i = 0; i < 20; i++) {
        //      System.out.print(random.getRandomSymbol().getImage());
        //  }


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
        var image = FXGL.getAssetLoader().loadTexture("test.jpg");
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(600);
        gameSettings.setTitle("LousVegous");
        gameSettings.setVersion("0.1");
        gameSettings.setIntroEnabled(false);

        CursorInfo cursorInfo = new CursorInfo("dollar.gif",0, 0);
        gameSettings.setDefaultCursor(cursorInfo);
        gameSettings.setIntroEnabled(true);
        gameSettings.setSceneFactory(new SceneFactory() {
            @NotNull
            @Override
            public IntroScene newIntro() {
                return new MyIntroScene();
            }
        });
    }


}