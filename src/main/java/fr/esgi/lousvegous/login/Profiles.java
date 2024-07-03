package fr.esgi.lousvegous.login;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Profiles {
    static Properties properties = new Properties();

    static {
        loadProperties();
    }

    public static void loadProperties() {
        try {
            properties.loadFromXML(new FileInputStream("config.xml"));
        } catch (Exception e) {
            try {
                properties.storeToXML(new FileOutputStream("config.xml"), null);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void saveProperties() {
        try {
            properties.storeToXML(new FileOutputStream("config.xml"), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveProperties();
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static boolean hasProperty(String key) {
        return properties.containsKey(key);
    }
}
