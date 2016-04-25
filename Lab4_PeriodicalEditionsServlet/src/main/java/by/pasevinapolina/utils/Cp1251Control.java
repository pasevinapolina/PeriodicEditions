package by.pasevinapolina.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Control for russian resource bundles
 * @author Polina Pasevina
 * @version 1.0
 */
public class Cp1251Control extends ResourceBundle.Control {

    public static Logger LOGGER = Logger.getLogger(Cp1251Control.class);

    @Override
    public ResourceBundle newBundle (String baseName, Locale locale,
                                      String format, ClassLoader loader, boolean reload)
            throws IllegalAccessException, InstantiationException, IOException {

        String bundleName = toBundleName(baseName, locale);
        String resourceName = toResourceName(bundleName, "properties");
        ResourceBundle bundle = null;
        InputStream stream = null;
        if (reload) {
            URL url = loader.getResource(resourceName);
            if (url != null) {
                URLConnection connection = url.openConnection();
                if (connection != null) {
                    connection.setUseCaches(false);
                    stream = connection.getInputStream();
                }
            }
        }
        else {
            stream = loader.getResourceAsStream(resourceName);
        }
        if (stream != null) {
            try {
                bundle = new PropertyResourceBundle(new InputStreamReader(stream, "Cp1251"));
            }
            catch (UnsupportedEncodingException e) {
                LOGGER.error(e.getMessage());
            }
            finally {
                stream.close();
            }
        }
        return bundle;
    }
}
