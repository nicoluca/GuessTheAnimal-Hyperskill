package animals.ui;

import animals.Main;
import animals.logic.AnimalLister;
import animals.logic.GameManager;

import java.util.List;

public class MenuConfig {
    public static String getMenuTitle() {
        return "What do you want to do:\n";
    }

    // TODO Continue here
    public static List<MenuItem> getMenuItems() {
        return List.of(
                new MenuItem("Play the guessing game", () -> AnimalGame.getInstance().run()),
                new MenuItem("List of all animals", () -> {
                    Main.LOGGER.info("Listing all animals");
                    AnimalLister.listAllAnimals(GameManager.getInstance().getTree());
                }),
                new MenuItem("Exit", () -> {
                    Main.LOGGER.info("Exiting program");
                    TextMenu.getInstance().stop();
                })
        );
    }
}
