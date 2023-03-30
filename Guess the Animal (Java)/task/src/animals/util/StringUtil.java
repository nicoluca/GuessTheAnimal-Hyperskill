package animals.util;

import animals.Main;
import animals.domain.animals.TypeOfFact;

import java.time.LocalTime;
import java.util.Arrays;

public class StringUtil {
    public static String getGreetingStringBasedOnTime() {
        int hour = LocalTime.now().getHour();
        String greeting;
        if (hour >= 5 && hour < 12)
            greeting =  LocalizationUtil.getMessage("welcome.morning") + "\n";
        else if (hour >= 12 && hour < 18)
            greeting = LocalizationUtil.getMessage("welcome.afternoon") + "\n";
        else
            greeting =  LocalizationUtil.getMessage("welcome.evening") + "\n";
        return greeting + LocalizationUtil.getMessage("welcome") + "\n";
    }

    public static String getWithoutArticle(String word) {
        // Example: "a dog" -> "dog", "the dog" -> "dog", "a nice dog" -> "nice dog"
        // Esperanto: "hundo" -> "hundo", "la hundo" -> "hundo", "la bela hundo" -> "bela hundo"
        String[] parts = word.split(" ");
        if (parts[0].matches(LocalizationUtil.getMessage("regex.article")))
            return Arrays.stream(parts).skip(1).reduce((a, b) -> a + " " + b).get();
        else
            return word;
    }

    public static String formatAnimalInput(String word) {
        if (word.isEmpty())
            throw new IllegalArgumentException("Animal name cannot be empty");

        // Example: "a dog" -> "a dog", "the dog" -> "a dog", "dog" -> "a dog"
        // Esperanto: "hundo" -> "hundo", "la hundo" -> "hundo", "la bela hundo" -> "bela hundo"
        String[] parts = word.split(" ");
        String result;

        if (parts.length == 1)
            result =  getArticleBasedOnFirstLetter(word) + " " + word;
        else if (parts[0].matches(LocalizationUtil.getMessage("regex.indefinitearticle")))
            result = word;
        else if (parts[0].matches(LocalizationUtil.getMessage("regex.definitearticle")))
            result = getArticleBasedOnFirstLetter(parts[1]) + " " +
                    Arrays.stream(parts).skip(1).reduce((a, b) -> a + " " + b).get();
        else
            result = getArticleBasedOnFirstLetter(parts[0]) + " " +
                    Arrays.stream(parts).reduce((a, b) -> a + " " + b).get();

        return result.toLowerCase().trim();
    }

    public static String getArticleBasedOnFirstLetter(String word) {
        char firstLetter = word.charAt(0);
        firstLetter = Character.toLowerCase(firstLetter);

        if (firstLetter == 'a' ||
                firstLetter == 'e' ||
                firstLetter == 'i' ||
                firstLetter == 'o' ||
                firstLetter == 'u')
            return LocalizationUtil.getMessage("regex.an");
        else
            return LocalizationUtil.getMessage("regex.a");
    }

    public static boolean isPositiveAnswer(String answer) {
        String[] positiveAnswers = LocalizationUtil.getMessages("stringutil.yes");
        answer = trimString(answer);
        return Arrays.asList(positiveAnswers).contains(answer);
    }

    private static String trimString(String string) {
        // Example: "  Yes!  " -> "yes"
        // Trim leading and trailing spaces
        string = string.replaceAll("^[\\s]+|[\\s]+$", "");
        // Trim last char if dot or exclamation mark
        string = string.replaceAll("[.!]$", "");
        // Return lowercase
        return string.toLowerCase();
    }

    public static boolean isNegativeAnswer(String answer) {
        String[] negativeAnswers = LocalizationUtil.getMessages("stringutil.no");
        answer = trimString(answer);
        return Arrays.asList(negativeAnswers).contains(answer);
    }



    public static boolean sentenceIsFact(String sentence) {
        // Needs to start with 'It can/has/is'
        Main.LOGGER.info("Checking if sentence is a fact: " + sentence);
        boolean result = sentence.matches(LocalizationUtil.getMessage("regex.isfact"));
        if (!result)
            Main.LOGGER.warning("Sentence is not a fact");
        return result;
    }

    public static TypeOfFact getTypeOfFact(String distinguishingFact) {
        String[] parts = distinguishingFact.split(" ");

        if (parts[1].matches(LocalizationUtil.getMessage("regex.can")))
            return TypeOfFact.CAN;
        else if (parts[1].matches(LocalizationUtil.getMessage("regex.has")))
            return TypeOfFact.HAS;
        else if (parts[1].matches(LocalizationUtil.getMessage("regex.is")))
            return TypeOfFact.IS;
        else
            return TypeOfFact.GENERIC;
    }

    public static String formatFact(String fact) {
        // Example: "It can swim." -> "swim", "It has a tail." -> "a tail"
        // Esperanto: "Li povas nadi." -> "nadi", "Li havas voston." -> "voston"
        fact = fact.toLowerCase();
        String[] parts = fact.split(" ");
        String result;
        if (getTypeOfFact(fact) == TypeOfFact.GENERIC)
            result = Arrays.stream(parts).skip(1).reduce((a, b) -> a + " " + b).get();
        else
            result = Arrays.stream(parts).skip(2).reduce((a, b) -> a + " " + b).get();

        // Remove period or question mark at the end
        return result.replaceAll("[.|?]$", "");
    }
}
