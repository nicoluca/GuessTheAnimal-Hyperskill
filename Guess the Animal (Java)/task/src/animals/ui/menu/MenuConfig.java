package animals.ui.menu;

import animals.Main;
import animals.domain.tree.BinaryTree;
import animals.ui.game.*;
import animals.util.CLIUtil;

import java.util.List;

public class MenuConfig {
    public static String getMenuTitle() {
        return "What do you want to do:\n";
    }

    // TODO Continue here
    public static List<MenuItem> getMenuItems() {
        BinaryTree tree = GameManager.getInstance().getTree();
        return List.of(
                new MenuItem("Exit", () -> TextMenu.getInstance().stop()),
                new MenuItem("Play the guessing game", () -> AnimalGame.getInstance().run()),
                new MenuItem("List of all animals", () -> AnimalLister.listAllAnimals(tree)),
                new MenuItem("Search for an animal", () -> AnimalSearcher.start(tree)),
                new MenuItem("Calculate statistics", () -> GameStatsPrinter.print(tree)),
                new MenuItem("Print the knowledge tree", () -> tree.print())
        );
    }
}
