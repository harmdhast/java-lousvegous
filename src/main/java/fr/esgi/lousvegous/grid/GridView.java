package fr.esgi.lousvegous.grid;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.FXGL;
import fr.esgi.lousvegous.LogicManager;
import fr.esgi.lousvegous.LousVegous;
import fr.esgi.lousvegous.intro.Logo;
import fr.esgi.lousvegous.pattern.Pattern;
import fr.esgi.lousvegous.player.Player;
import fr.esgi.lousvegous.symbol.Symbol;
import fr.esgi.lousvegous.symbol.SymbolManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
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
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GridView {
    private static final Player currentPlayer = Player.getCurrentPlayer();
    private static final Button spinButton = new Button("SPIN");
    private static final BooleanProperty spinning = new SimpleBooleanProperty(false);
    private static int toDestroy = 0;
    private static List<Animation<?>> animations = LousVegous.animations;

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
        //Button spinButton = new Button("SPIN");
        spinButton.setPrefWidth(100);
        spinButton.setPrefHeight(50);
        spinButton.setStyle("-fx-font-size: 20px;");
        spinButton.setFont(Font.font("LEMON MILK Bold", 20));
        spinButton.disableProperty().bind(currentPlayer.balanceProperty().lessThan(currentPlayer.betProperty()).or(spinning));
        // button.setOnAction(e -> spin(e));
        spinButton.setOnAction(GridView::spin);

        // Display user balance
        Label balanceLabel = new Label("Balance: ");
        balanceLabel.setFont(Font.font("LEMON MILK Bold", 20));
        balanceLabel.setTextFill(Color.YELLOW);
        Label balance = new Label();
        balance.setFont(Font.font("LEMON MILK Bold", 20));
        balance.setTextFill(Color.YELLOW);
        balance.textProperty().bind(currentPlayer.balanceProperty().asString());

        // Display bet
        Label betLabel = new Label("Current bet :");
        betLabel.setFont(Font.font("LEMON MILK Bold", 20));
        betLabel.setTextFill(Color.YELLOW);
        Label bet = new Label();
        bet.setFont(Font.font("LEMON MILK Bold", 20));
        bet.setTextFill(Color.YELLOW);
        bet.textProperty().bind(currentPlayer.betProperty().asString());

        // Create a group of 2 buttons
        HBox buttonGroup = new HBox();
        buttonGroup.setSpacing(10);
        buttonGroup.setAlignment(javafx.geometry.Pos.CENTER);
        Button plus = new Button("+");
        plus.setFont(Font.font("LEMON MILK Bold", 20));
        plus.setPrefWidth(45);
        plus.setOnAction(e -> betMore());
        Button minus = new Button("-");
        minus.disableProperty().bind(currentPlayer.betProperty().lessThanOrEqualTo(LogicManager.getMinBet()));
        minus.setPrefWidth(45);
        minus.setFont(Font.font("LEMON MILK Bold", 20));
        minus.setOnAction(e -> betLess());
        buttonGroup.getChildren().addAll(minus, plus);

        Label multiplier = new Label("Multiplier: ");
        multiplier.setFont(Font.font("LEMON MILK Bold", 20));
        multiplier.setTextFill(Color.YELLOW);
        Label multiplierValue = new Label();
        multiplierValue.setFont(Font.font("LEMON MILK Bold", 20));
        multiplierValue.setTextFill(Color.YELLOW);
        multiplierValue.textProperty().bind(Grid.currentMultiplierProperty().asString().concat("x"));

        // Display last winning
        Label lastWinning = new Label("Last winning: ");
        lastWinning.setFont(Font.font("LEMON MILK Bold", 20));
        lastWinning.setTextFill(Color.YELLOW);
        Label lastWinningValue = new Label();
        lastWinningValue.setFont(Font.font("LEMON MILK Bold", 20));
        lastWinningValue.setTextFill(Color.YELLOW);
        lastWinningValue.textProperty().bind(currentPlayer.lastWinningProperty().asString());

        VBox vbox = new VBox();
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        vbox.setSpacing(10);
        vbox.getChildren().addAll(lastWinning, lastWinningValue, multiplier, multiplierValue, betLabel, bet, buttonGroup, balanceLabel, balance, spinButton);

        Label username = new Label("Welcome " + currentPlayer.getUsername());
        username.setFont(Font.font("LEMON MILK Bold", 20));
        username.setTextFill(Color.TEAL);
        //username.setTranslateY(25);

        // Create Hbox
        HBox hbox = new HBox();
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        hbox.getChildren().addAll(playingGrid, vbox);

        Text text = new Text("LousVegous");
        text.setFont(Font.font("Casino Shadow", 100));
        text.setFill(Color.YELLOW);

        // Create Vbox
        VBox mainVBox = new VBox();
        mainVBox.setPrefSize(800, 600);
        mainVBox.setSpacing(-50);
        //mainVBox.setSnapToPixel(true);
        mainVBox.getChildren().addAll(Logo.getLogo(0.5), username, hbox);
        mainVBox.setAlignment(javafx.geometry.Pos.CENTER);

        return mainVBox;
    }

    public static void moveTo() {
        FXGL.getGameScene().getRoot().getChildren().setAll(getView());
    }

    private static void betMore() {
        if (currentPlayer.getBalance() - currentPlayer.getBet() >= LogicManager.getMinBet()) {
            currentPlayer.setBet(currentPlayer.getBet() + LogicManager.getMinBet());
        }
    }

    public static void disableSpinButton() {
        spinning.set(true);
    }

    public static void enableSpinButton() {
        spinning.set(false);
    }

    private static void betLess() {
        if (currentPlayer.getBet() > LogicManager.getMinBet()) {
            currentPlayer.setBet(currentPlayer.getBet() - LogicManager.getMinBet());
        }
    }

    public static void spin(ActionEvent e) {
        disableSpinButton();
        currentPlayer.setBalance((int) (currentPlayer.getBalance() - currentPlayer.getBet()));
        currentPlayer.savePlayer();
        Grid.resetMultiplier();

        if (Objects.equals(currentPlayer.getUsername(), "admin")) {
            Grid.getInstance().setGridSymbol(0, SymbolManager.getInstance().getSymbols().get(1));
            Grid.getInstance().setGridSymbol(1, SymbolManager.getInstance().getSymbols().get(2));
            Grid.getInstance().setGridSymbol(2, SymbolManager.getInstance().getSymbols().get(8));
            Grid.getInstance().setGridSymbol(3, SymbolManager.getInstance().getSymbols().get(1));
            Grid.getInstance().setGridSymbol(4, SymbolManager.getInstance().getSymbols().get(2));
            Grid.getInstance().setGridSymbol(5, SymbolManager.getInstance().getSymbols().get(8));
            Grid.getInstance().setGridSymbol(6, SymbolManager.getInstance().getSymbols().get(1));
            Grid.getInstance().setGridSymbol(7, SymbolManager.getInstance().getSymbols().get(2));
            Grid.getInstance().setGridSymbol(8, SymbolManager.getInstance().getSymbols().get(8));
            Grid.getInstance().setGridSymbol(9, SymbolManager.getInstance().getSymbols().get(1));
            Grid.getInstance().setGridSymbol(10, SymbolManager.getInstance().getSymbols().get(8));
            Grid.getInstance().setGridSymbol(11, SymbolManager.getInstance().getSymbols().get(8));
            Grid.getInstance().setGridSymbol(12, SymbolManager.getInstance().getSymbols().get(1));
            Grid.getInstance().setGridSymbol(13, SymbolManager.getInstance().getSymbols().get(7));
            Grid.getInstance().setGridSymbol(14, SymbolManager.getInstance().getSymbols().get(7));
        } else {
            for (int i = 0; i < 15; i++) {
                Grid.getInstance().setGridSymbol(i, SymbolManager.getInstance().getRandomSymbol());
            }
        }


//        int i = 0;
//        for (ImageView imageView : displayGrid) {
//            // Symbol symbol = SymbolManager.getInstance().getRandomSymbol();
//            Symbol symbol = test[i];
//            imageView.setImage(symbol.getImage());
//            grid.setGridSymbol(idx, symbol);
//            idx++;
//            i++;
//        }


        processLoop();
    }

    public static void processLoop() {
        toDestroy = processPatterns();
        startAnimations();
    }

    private static void processWinning() {
        currentPlayer.setBalance((int) (currentPlayer.getBalance() + currentPlayer.getBet() * Grid.getCurrentMultiplier()));
        currentPlayer.setLastWinning(currentPlayer.getBet() * Grid.getCurrentMultiplier());
        currentPlayer.savePlayer();
    }

    private static void startAnimations() {
        if (animations.isEmpty()) {
            processWinning();
            enableSpinButton();
        } else {
            animations.forEach(Animation::start);
            animations.getLast().setOnFinished(() -> {
                System.out.println("Finished");
                LousVegous.animations = new ArrayList<>();
                animations = LousVegous.animations;
                replaceSymbols(toDestroy);
                processLoop();
            });
        }
    }

    private static int processPatterns() {
        HashMap<Pattern, Symbol> matches = Grid.getInstance().getAllMatches();
        int toDestroy = 0;
        double delay = 0;
        for (Pattern pattern : matches.keySet()) {
            toDestroy = toDestroy | pattern.getBinary();
            double finalDelay = delay;
            float mutliplier = pattern.getMultiplier(matches.get(pattern));
            pattern.getIndexOfOnes().forEach(i -> addAnimationToIndex(i, finalDelay, mutliplier));
            delay += 1;
        }

        return toDestroy;
    }

    private static void replaceSymbols(int toDestroy) {
        System.out.println(toDestroy);
        System.out.println(Pattern.decimalToBinary(toDestroy));
        System.out.println(Pattern.getIndexOfOnes(Pattern.decimalToBinary(toDestroy)));
        Pattern.getIndexOfOnes(Pattern.decimalToBinary(toDestroy)).forEach(i -> {
            Symbol symbol = SymbolManager.getInstance().getRandomSymbol();
            Grid.getInstance().setGridSymbol(i, symbol);
        });
    }

    private static void addAnimationToIndex(int index, double delay, float multiplier) {
        Animation<Number> animation = FXGL.animationBuilder().duration(Duration.seconds(1))
                .delay(Duration.seconds(delay))
                .autoReverse(true)
                .interpolator(Interpolators.LINEAR.EASE_OUT())
                .animate(Grid.getDisplayGrid(index).rotateProperty())
                .from(0.0)
                .to(360.0)
                .build();
        animations.add(animation);
        animation.setOnFinished(() -> {
            Grid.addMultiplier(multiplier);
        });
    }

}
