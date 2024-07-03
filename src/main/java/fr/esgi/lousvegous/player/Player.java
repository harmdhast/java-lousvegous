package fr.esgi.lousvegous.player;


import com.almasb.fxgl.core.serialization.Bundle;
import com.almasb.fxgl.profile.DataFile;
import com.almasb.fxgl.profile.SaveLoadHandler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Player {
    private final String saveDirectory = "saves";
    private final String username;
    private final double balance = 200000;
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
        }
    }

    private Path getSavePath(String username) {
        return Path.of(saveDirectory, "player_" + username + ".dat");
    }

    public void configSave() {
        getSaveLoadService().addHandler(new SaveLoadHandler() {
            @Override
            public void onLoad(@NotNull DataFile dataFile) {
                // get your previously saved bundle
                var bundle = dataFile.getBundle("gameData");

                // retrieve some data
                String username = bundle.get("username");

                // update your game with saved data
                set("username", username);
            }

            @Override
            public void onSave(DataFile data) {
                // create a new bundle to store your data
                var bundle = new Bundle("gameData");

                bundle.put("username", username);

                // give the bundle to data file
                data.putBundle(bundle);
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
}
