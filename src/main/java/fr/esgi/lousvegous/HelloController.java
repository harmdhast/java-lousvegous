package fr.esgi.lousvegous;

import fr.esgi.lousvegous.symbol.SymbolManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HelloController {
    public GridPane playingGrid;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        // Clean the playing grid
        playingGrid.getChildren().clear();
        for (int i = 0; i < playingGrid.getColumnCount(); i++) {
            for (int j = 0; j < playingGrid.getRowCount(); j++) {

//                Label label = new Label(rand.getRandomSymbol().getImage());
//                label.setFont(Font.font("Segoe UI Emoji", FontWeight.BOLD, 20));
                //Font font = Font.loadFont(getClass().getResourceAsStream("/fr/esgi/lousvegous/NotoColorEmoji.ttf"), 20);
                Label label = new Label(SymbolManager.getInstance().getRandomSymbol().getImage());
                label.setFont(Font.font("NotoColorEmoji", FontWeight.BOLD, 20));
                playingGrid.add(label, i, j);
            }
        }
        playingGrid.add(new Label(""), 0, 0);
    }
}