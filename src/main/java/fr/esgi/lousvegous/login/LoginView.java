package fr.esgi.lousvegous.login;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class LoginView {
    public static Node getView() {
        System.out.println("Login view");

        Rectangle test = new Rectangle(100, 100);
        test.setFill(javafx.scene.paint.Color.RED);

        return test;
    }
}
