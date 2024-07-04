package fr.esgi.lousvegous.login;

import com.almasb.fxgl.dsl.FXGL;
import fr.esgi.lousvegous.grid.GridView;
import fr.esgi.lousvegous.player.Player;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class LoginView {
    private static final Label errorLabel = new Label();
    private static final TextField txtUsername = new TextField();
    private static final PasswordField txtPassword = new PasswordField();
    private static final Button btnLogin = new Button("Login");
    private static final Button btnRegister = new Button("Register");

    private static boolean fieldsAreEmpty() {
        return txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty();
    }

    private static void setErrorMessage(String message) {
        errorLabel.setText(message);
    }

    private static void disableButtons() {
        btnLogin.setDisable(true);
        btnRegister.setDisable(true);
    }

    private static void enableButtons() {
        btnLogin.setDisable(false);
        btnRegister.setDisable(false);
    }

    private static void login() {
        disableButtons();
        setErrorMessage("");
        if (fieldsAreEmpty()) {
            setErrorMessage("Username and password are required");
            enableButtons();
            return;
        }

        Player player = Login.login(txtUsername.getText(), txtPassword.getText());
        if (player == null) {
            setErrorMessage("Invalid username or password");
            enableButtons();
            return;
        }

        Player.setCurrentPlayer(player);
        GridView.moveTo();
    }

    private static void register() {
        disableButtons();
        setErrorMessage("");
        if (fieldsAreEmpty()) {
            setErrorMessage("Username and password are required");
            enableButtons();
            return;
        }

        Player player = Login.register(txtUsername.getText(), txtPassword.getText());

        if (player == null) {
            setErrorMessage("Username already exists");
            enableButtons();
            return;
        }

        Player.setCurrentPlayer(player);
        GridView.moveTo();
    }

    public static Node getView() {
        System.out.println("Login view");

        errorLabel.setStyle("-fx-text-fill: red;");

        Label lblUsername = new Label("Username:");
        lblUsername.setStyle("-fx-text-fill: white;");

        Label lblPassword = new Label("Password:");
        lblPassword.setStyle("-fx-text-fill: white;");

        btnLogin.setPrefWidth(100);
        btnLogin.setOnAction(e -> {
            login();
        });
        btnRegister.setPrefWidth(100);
        btnRegister.setOnAction(e -> {
            register();
        });


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(lblUsername, 0, 0);
        gridPane.add(txtUsername, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(txtPassword, 1, 1);
        gridPane.add(btnLogin, 0, 2);
        gridPane.add(btnRegister, 1, 2);
        gridPane.add(errorLabel, 0, 3, 2, 1);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPrefSize(800, 600);
        stackPane.getChildren().addAll(gridPane);

        return stackPane;
    }

    public static void moveTo() {
        FXGL.getGameScene().getRoot().getChildren().setAll(getView());
    }
}
