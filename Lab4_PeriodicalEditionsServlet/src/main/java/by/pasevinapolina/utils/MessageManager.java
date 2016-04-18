package by.pasevinapolina.utils;

import java.util.ResourceBundle;

/**
 * Contains resource bundle to get messages
 * @see ResourceBundle
 * @author Polina Pasevina
 * @version 1.0
 */
public class MessageManager {

    /**
     * Message resource
     */
    public static final ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", new Cp1251Control());

    private MessageManager() {
    }

    /**
     * Gets message from the resource
     * @param key Key string
     * @return Value for the key
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
