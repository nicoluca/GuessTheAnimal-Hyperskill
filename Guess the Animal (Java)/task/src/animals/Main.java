package animals;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        greetUser();
        AnimalGame game = new AnimalGame();
        game.start();
        printGoodbye();
        scanner.close();
    }


    private static void printGoodbye() {
        System.out.println();
        System.out.println(StringUtil.getGoodbye());
    }

    private static void printAnswer(boolean answer) {
        if (answer)
            System.out.println("You answered: Yes");
        else
            System.out.println("You answered: No");
    }

    private static boolean isAnswerYes() {
        boolean isPositiveAnswer;
        while(true) {
            String answer = CLIUtil.getString();
            if (StringUtil.isPositiveAnswer(answer)) {
                isPositiveAnswer = true;
                break;
            } else if (StringUtil.isNegativeAnswer(answer)) {
                isPositiveAnswer = false;
                break;
            } else {
                System.out.println(StringUtil.getClarification());
            }
        }
        return isPositiveAnswer;
    }

    private static void askForAnimal(String animal) {
        String animalWithArticle = StringUtil.formatAnimalInput(animal);
        System.out.println("Is it " + animalWithArticle + "?");
    }

    private static void greetUser() {
        System.out.println(StringUtil.getGreetingStringBasedOnTime());
    }
}
