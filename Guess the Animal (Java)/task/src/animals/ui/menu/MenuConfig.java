package animals.ui.menu;

import animals.domain.tree.BinaryTree;
import animals.ui.game.*;

import java.util.List;

public class MenuConfig {
    public static String getMenuTitle() {
        return "What do you want to do:\n";
    }

    public static List<MenuItem> getMenuItems() {
        BinaryTree tree = GameManager.getInstance().getTree();
        return List.of(
                new MenuItem("Exit", () -> TextMenu.getInstance().stop()), // Exit menu needs to be first
                new MenuItem("Play the guessing game", () -> AnimalGame.getInstance().run()),
                new MenuItem("List of all animals", () -> AnimalLister.listAllAnimals(tree)),
                new MenuItem("Search for an animal", () -> AnimalSearcher.start(tree)),
                new MenuItem("Calculate statistics", () -> GameStatsPrinter.print(tree)),
                new MenuItem("Print the knowledge tree", tree::print)
        );
    }
}
