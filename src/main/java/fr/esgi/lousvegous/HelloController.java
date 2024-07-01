package fr.esgi.lousvegous;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
                // Create image from resources
                // Image image = SymbolManager.getInstance().getRandomSymbol().getImage();
                //ImageView imageView = new ImageView(image);
                //playingGrid.add(imageView, i, j);
            }
        }
        playingGrid.add(new Label(""), 0, 0);
    }
}