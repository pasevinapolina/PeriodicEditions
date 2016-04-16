package by.pasevinapolina.utils;

import java.util.ResourceBundle;

/**
 *
 */
public class ConfigurationManager {

    public static final ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    private ConfigurationManager() {

    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
