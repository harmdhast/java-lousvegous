package fr.esgi.lousvegous.grid;

import com.almasb.fxgl.dsl.FXGL;
import fr.esgi.lousvegous.intro.Logo;
import fr.esgi.lousvegous.player.Player;
import fr.esgi.lousvegous.symbol.SymbolManager;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GridView {
    private static final Player currentPlayer = Player.getCurrentPlayer();

    public static Node getView() {
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
                Grid.getInstance().setGridSymbol(idx, SymbolManager.getInstance().getRandomSymbol());

                // Add the rectangle (cell) to the grid
                playingGrid.add(Grid.getDisplayGrid(idx), i, j);
                idx++;
            }
        }

        // Create button
        Button button = new Button("SPIN");
        button.setPrefWidth(100);
        button.setPrefHeight(50);
        button.setStyle("-fx-font-size: 20px;");
        // button.setOnAction(e -> spin(e));
        button.setOnAction(e -> {
            currentPlayer.setBalance((int) (currentPlayer.getBalance() - 1.0));
        });

        // Display user balance
        Label balanceLabel = new Label("Balance: ");
        balanceLabel.setFont(Font.font("LEMON MILK Bold", 20));
        balanceLabel.setTextFill(Color.YELLOW);
        Label balance = new Label();
        balance.setFont(Font.font("LEMON MILK Bold", 20));
        balance.setTextFill(Color.YELLOW);
        balance.textProperty().bind(currentPlayer.balanceProperty().asString());

        VBox vbox = new VBox();
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        vbox.setSpacing(10);
        vbox.getChildren().addAll(balanceLabel, balance, button);

        // Create Hbox
        HBox hbox = new HBox();
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        hbox.getChildren().add(playingGrid);
        hbox.getChildren().add(vbox);

        Text text = new Text("LousVegous");
        text.setFont(Font.font("Casino Shadow", 100));
        text.setFill(Color.YELLOW);

        // Create Vbox
        VBox mainVBox = new VBox();
        mainVBox.setPrefSize(800, 600);
        mainVBox.setSnapToPixel(true);
        mainVBox.getChildren().addAll(Logo.getLogo(0.5), hbox);
        mainVBox.setAlignment(javafx.geometry.Pos.CENTER);

        return mainVBox;
    }

    public static void moveTo() {
        FXGL.getGameScene().getRoot().getChildren().setAll(getView());
    }
}
