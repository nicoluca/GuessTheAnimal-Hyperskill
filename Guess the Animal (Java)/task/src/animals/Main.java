package animals;

import animals.domain.Constants;
import animals.ui.AnimalGame;
import animals.util.StringUtil;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static AnimalGame game = new AnimalGame();
    public static void main(String[] args) {
        greetUser();
        game.start();
        printGoodbye();
        scanner.close();
    }

    private static void printGoodbye() {
        System.out.println();
        System.out.println(Constants.getGoodbye());
    }

    private static void greetUser() {
        System.out.println(StringUtil.getGreetingStringBasedOnTime());
    }
}
