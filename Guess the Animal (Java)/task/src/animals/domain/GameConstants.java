package animals.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameConstants {
    private static final Random random = new Random();
    public static String getGameGreeting() {
        return "I want to learn about animals.\n" +
                "Which animal do you like most?";
    }

    public static String getSecondGameGreeting() {
        return """
                Wonderful!
                I've learned so much about animals!
                Let's play a game!
                You think of an animal, and I guess it.
                Press enter when you're ready.""";
    }

    public static String getGreetingForSavedGame() {
        return """
                I know a lot about animals.
                Let's play a game!
                You think of an animal, and I guess it.
                Press enter when you're ready.""";
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

    public static String getNegativeResponse() {
        return "I give up. What animal do you have in mind?";
    }

    public static String getPlayAgain() {
        return "Would you like to play again?";
    }

    public static String correctGuess() { return "I guessed the animal!"; }
}
