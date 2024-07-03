package fr.esgi.lousvegous.login;

import fr.esgi.lousvegous.player.Player;

public class Login {

    private boolean checkPassword(String username, String password) {
        return Profiles.getProperty(username).equals(password);
    }

    private String withPrefix(String username) {
        return "user." + username;
    }

    public Player login(String username, String password) {
        if (Profiles.hasProperty(withPrefix(username)) && checkPassword(withPrefix(username), password)) {
            System.out.println("Login successful for " + username);
            return new Player(username);
        } else {
            System.out.println("Login failed");
            return null;
        }
    }

    public boolean register(String username, String password) {
        username = withPrefix(username);
        if (Profiles.hasProperty(username)) {
            System.out.println("Username already exists");
            return false;
        } else {
            Profiles.setProperty(username, password);
            System.out.println("Registration successful for " + username);
            return true;
        }
    }
}
