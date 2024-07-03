package fr.esgi.lousvegous.login;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RegisterView {
    public static Node getView() {
        System.out.println("Login view");

        Rectangle background = new Rectangle(FXGL.getAppWidth(), FXGL.getAppHeight()); // background
        background.setFill(javafx.scene.paint.Color.GREEN);


        Label lblUsername = new Label("Username:");
        TextField txtUsername = new TextField();

        Label lblPassword = new Label("Password:");
        PasswordField txtPassword = new PasswordField();

        Button btnLogin = new Button("Login");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(lblUsername, 0, 0);
        gridPane.add(txtUsername, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(txtPassword, 1, 1);
        gridPane.add(btnLogin, 1, 2);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(background, gridPane);

        return stackPane;
    }
}
