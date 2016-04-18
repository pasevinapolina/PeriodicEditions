package by.pasevinapolina.utils;

import java.util.ResourceBundle;

/**
 *
 */
public class MessageManager {

    public static final ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", new Cp1251Control());

    private MessageManager() {

    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
