package by.pasevinapolina.utils;

import java.util.ResourceBundle;

/**
 * Contains resource bundle to get configuration properties
 * @see ResourceBundle
 * @author Polina Pasevina
 * @version 1.0
 */
public class ConfigurationManager {

    /**
     * Configuration resource
     */
    public static final ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    private ConfigurationManager() {
    }

    /**
     * Gets configuration string
     * @param key Key string
     * @return Value for the key
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
