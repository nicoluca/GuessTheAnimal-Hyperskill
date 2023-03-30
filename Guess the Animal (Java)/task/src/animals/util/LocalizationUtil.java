package animals.util;

import animals.Main;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocalizationUtil {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");
    private static final String DELIMITER = ";";

    public static String getMessage(String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return getFallbackTextFromMessages(key);
        }
    }

    private static String getFallbackTextFromMessages(String key) {
        try {
            return ResourceBundle.getBundle("messages", Locale.ENGLISH).getString(key);
        } catch (MissingResourceException e) {
            Main.LOGGER.warning("Missing resource: " + key);
            return "Missing resource: " + key;
        }
    }

    public static String[] getMessages(String key) {
        return getMessage(key).split(DELIMITER);
    }

    public static String getRandomMessage(String key) {
        String[] messages = getMessage(key).split(DELIMITER);
        return messages[(int) (Math.random() * messages.length)];
    }

    public static String getMessageWithArgument(String key, String value) {
        return getMessage(key).replace("%s", value);
    }

    public static String getMessageWithArguments(String key, String... values) {
        String message = getMessage(key);
        for (int i = 0; i < values.length; i++)
            message = message.replace("%s" + (i + 1), values[i]);
        return message;
    }

    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }
}
