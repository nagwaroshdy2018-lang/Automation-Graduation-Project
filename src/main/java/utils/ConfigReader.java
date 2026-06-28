package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Reads config.properties at class-load and exposes typed getters.
 */
public class ConfigReader {

    private static final Properties props = new Properties();
    private static final String PATH = "src/test/resources/config.properties";

    static {
        try (FileInputStream fis = new FileInputStream(PATH)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not load " + PATH, e);
        }
    }

    private ConfigReader() {}

    public static String get(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing key in config.properties: " + key);
        }
        return value.trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}