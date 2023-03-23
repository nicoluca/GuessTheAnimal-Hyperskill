package animals;

import java.nio.file.Path;
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
        System.out.println(StringUtil.getGoodbye());
    }

    private static void greetUser() {
        System.out.println(StringUtil.getGreetingStringBasedOnTime());
    }
}
