package animals;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StringUtil {
    private static final Random random = new Random();
    public static String getGreetingBasedOnTime() {
        int hour = LocalTime.now().getHour();
        if (hour >= 5 && hour < 12) {
            return "Good morning!";
        } else if (hour >= 12 && hour < 18) {
            return "Good afternoon!";
        } else {
            return "Good evening!";
        }
    }

    public static String formatAnimalInput(String word) {
        String[] parts = word.split(" ");
        String result;

        if (parts.length == 1)
            result =  getArticleBasedonFirstLetter(word) + " " + word;
        else if (parts[0].equalsIgnoreCase("a") || parts[0].equalsIgnoreCase("an"))
            result = word;
        else if (parts[0].equalsIgnoreCase("the"))
            result = getArticleBasedonFirstLetter(parts[1]) + " " +
                    Arrays.stream(parts).skip(1).reduce((a, b) -> a + " " + b).get();
        else
            result = getArticleBasedonFirstLetter(parts[0]) + " " +
                    Arrays.stream(parts).reduce((a, b) -> a + " " + b).get();

        return result.toLowerCase();
    }

    public static String getArticleBasedonFirstLetter(String word) {
        char firstLetter = word.charAt(0);
        if (firstLetter == 'a' || firstLetter == 'e' || firstLetter == 'i' || firstLetter == 'o' || firstLetter == 'u') {
            return "an";
        } else {
            return "a";
        }
    }

    public static boolean isPositiveAnswer(String answer) {
        List<String> positiveAnswers = Arrays.asList(
                "y",
                "yes",
                "yeah",
                "yep",
                "sure",
                "right",
                "affirmative",
                "correct",
                "indeed",
                "you bet",
                "exactly",
                "you said it");

        answer = trimString(answer);
        return positiveAnswers.contains(answer);
    }

    private static String trimString(String string) {
        // Trim leading and trailing spaces
        string = string.replaceAll("^[\\s]+|[\\s]+$", "");
        // Trim last char if dot or exclamation mark
        string = string.replaceAll("[\\.!]$", "");
        // Return lowercase
        return string.toLowerCase();
    }

    public static boolean isNegativeAnswer(String answer) {
        List<String> negativeAnswers = Arrays.asList(
                "n",
                "no",
                "no way",
                "nah",
                "nope",
                "negative",
                "i don't think so",
                "yeah no");

        answer = trimString(answer);
        return negativeAnswers.contains(answer);
    }

    public static String getClarification() {
        List<String> clarifications = Arrays.asList(
                "I'm not sure I caught you: was it yes or no?",
                "Funny, I still don't understand, is it yes or no?",
                "Oh, it's too complicated for me: just tell me yes or no.",
                "Could you please simply say yes or no?",
                "Oh, no, don't try to confuse me: say yes or no."
        );

        return clarifications.get(random.nextInt(clarifications.size()));
    }

    public static String getGoodbye() {
        List<String> goodbyes = Arrays.asList(
                "Bye!",
                "See you!",
                "Goodbye!",
                "Have a nice day!",
                "Have a good day!"
        );

        return goodbyes.get(random.nextInt(goodbyes.size()));
    }
}
