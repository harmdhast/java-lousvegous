package fr.esgi.lousvegous.login;

import fr.esgi.lousvegous.player.Player;

public class Login {

    private static boolean checkPassword(String username, String password) {
        return Profiles.getProperty(username).equals(password);
    }

    private static String withPrefix(String username) {
        return "user." + username;
    }

    public static Player login(String username, String password) {
        if (Profiles.hasProperty(withPrefix(username)) && checkPassword(withPrefix(username), password)) {
            System.out.println("Login successful for " + username);
            return new Player(username);
        } else {
            System.out.println("Login failed");
            return null;
        }
    }

    public static Player register(String username, String password) {
        if (Profiles.hasProperty(withPrefix(username))) {
            System.out.println("Username already exists");
            return null;
        } else {
            Profiles.setProperty(withPrefix(username), password);
            System.out.println("Registration successful for " + username);
            return new Player(username);
        }
    }
}
