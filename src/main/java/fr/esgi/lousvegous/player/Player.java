package fr.esgi.lousvegous.player;


import com.almasb.fxgl.core.serialization.Bundle;
import com.almasb.fxgl.profile.DataFile;
import com.almasb.fxgl.profile.SaveLoadHandler;
import fr.esgi.lousvegous.login.Profiles;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Player {
    private static Player currentPlayer;
    private final String saveDirectory = "saves";
    private final String username;
    private DoubleProperty balance = new SimpleDoubleProperty(200000);
    private LocalDateTime lastLogin;

    public Player(String username) {
        // Check for save directory and create if not exists
        try {
            Files.createDirectories(Paths.get(saveDirectory));
        } catch (IOException e) {
            e.printStackTrace();
        }
        configSave();

        // check if file exists
        if (!Files.exists(getSavePath(username))) {
            // create new player
            this.username = username;
            savePlayer(username);
        } else {
            // load player
            loadPlayer(username);
            this.username = gets("username");
            setBalance(getd("balance"));
        }
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public static boolean exists(String username) {
        return Profiles.hasProperty("user." + username);
    }

    private Path getSavePath(String username) {
        return Path.of(saveDirectory, "player_" + username + ".dat");
    }

    public void configSave() {
        getSaveLoadService().addHandler(new SaveLoadHandler() {
            @Override
            public void onLoad(@NotNull DataFile data) {
                // get your previously saved bundle
                var bundle = data.getBundle("gameData");

                // retrieve some data
                String username = bundle.get("username");
                Double balance = bundle.get("balance");

                // update your game with saved data
                set("username", username);
                set("balance", balance);
            }

            @Override
            public void onSave(@NotNull DataFile data) {
                // create a new bundle to store your data
                Bundle bundle;

                try {
                    bundle = new Bundle("gameData");
                    data.putBundle(bundle);
                } catch (Exception e) {
                    bundle = data.getBundle("gameData");
                }

                bundle.put("username", username);
                bundle.put("balance", getBalance());

                // give the bundle to data file
            }
        });
    }

    public void loadPlayer(String username) {
        getSaveLoadService().readAndLoadTask(getSavePath(username).toString()).run();
    }

    public void savePlayer(String username) {
        getSaveLoadService().saveAndWriteTask(getSavePath(username).toString()).run();
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance.getValue();
    }

    public void setBalance(double i) {
        this.balance.set(i);
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }
}
