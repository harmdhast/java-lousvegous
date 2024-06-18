package fr.esgi.lousvegous;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class HelloController {
    private final Random rand = new Random();
    public GridPane playingGrid;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        for (int i = 0; i < playingGrid.getColumnCount(); i++) {
            for (int j = 0; j < playingGrid.getRowCount(); j++) {
                playingGrid.getChildren().removeAll();
                Label label = new Label(rand.getRandomSymbol().getImage());
                label.setFont(new Font("OpenMoji", 16));
                playingGrid.add(label, i, j);
            }
        }
        playingGrid.add(new Label(""), 0, 0);
        welcomeText.setFont(new Font("OpenMoji", 24));
        welcomeText.setText("\uD83C\uDF4D");
    }
}