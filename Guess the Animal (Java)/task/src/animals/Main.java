package animals;

import animals.ui.game.GameManager;
import animals.storage.FileManager;
import animals.ui.game.AnimalGame;
import animals.ui.menu.TextMenu;
import animals.util.ArgsUtil;
import animals.util.LocalizationUtil;
import animals.util.StringUtil;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static FileManager fileManager;
    private static GameManager gameManager;
    public static AnimalGame animalGame;

    public static void main(String[] args) {
        Main.LOGGER.setLevel(Level.WARNING);
        setUpProgram(args);
        launchMenu();
        exitProgram();
    }

    private static void setUpProgram(String[] args) {
        fileManager = new FileManager(ArgsUtil.getFileFormat(args));
        launchAnimalGame();
    }

    private static void launchMenu() {
        TextMenu menu = TextMenu.getInstance();
        menu.launch();
    }

    private static void launchAnimalGame() {
        gameManager = GameManager.getInstance();
        animalGame = AnimalGame.getInstance();
        printTimeBasedGreeting();
    }

    private static void exitProgram() {
        gameManager.saveGame();
        printGoodbye();
        scanner.close();
    }

    private static void printTimeBasedGreeting() {
        System.out.println(StringUtil.getGreetingStringBasedOnTime());
    }

    private static void printGoodbye() {
        System.out.println("\n" + LocalizationUtil.getRandomMessage("goodbye"));
    }
}
