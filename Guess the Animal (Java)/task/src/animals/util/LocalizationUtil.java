package animals.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class LocalizationUtil {
    public static String RESOURCES_FOLDER = ""; // ""Guess the Animal (Java)/task/src/resources/";

    public static PropertyResourceBundle getResourceBundle(String name) {
        try {
            FileInputStream fis = new FileInputStream(RESOURCES_FOLDER + name);
            return new PropertyResourceBundle(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResourceBundle getResource(String name) {
        return ResourceBundle.getBundle(RESOURCES_FOLDER + name);
    }
}
