package fr.esgi.lousvegous;

import fr.esgi.lousvegous.symbol.SymbolManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        //SymbolManager symbolManager = new SymbolManager();

//        Random random = new Random();
//        for (int i = 0; i < 20; i++) {
//            System.out.print(random.getRandomSymbol().getImage());
//        }


        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("lousvegous.css")).toExternalForm());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}