package animals;

import animals.domain.Constants;
import animals.storage.FileManager;
import animals.ui.AnimalGame;
import animals.util.ArgsUtil;
import animals.util.StringUtil;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static AnimalGame game;
    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        FileManager fileManager = new FileManager(ArgsUtil.getFileFormat(args));
        game = new AnimalGame(fileManager);
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
