package animals.ui.menu;

import animals.util.LocalizationUtil;
import animals.domain.tree.BinaryTree;
import animals.ui.game.*;

import java.io.FileInputStream;
import java.util.List;
import java.util.ResourceBundle;

public class MenuConfig {
    public static String getMenuTitle() {
        return "What do you want to do:\n";
    }

    public static List<MenuItem> getMenuItems() {
        FileInputStream fis = null;
        ResourceBundle bundle = LocalizationUtil.getResource("messages");
        BinaryTree tree = GameManager.getInstance().getTree();
        return List.of(
                new MenuItem(bundle.getString("menu.config.exit"), () -> TextMenu.getInstance().stop()), // Exit menu needs to be first
                new MenuItem(bundle.getString("play"), () -> AnimalGame.getInstance().run()),
                new MenuItem("List of all animals", () -> AnimalLister.listAllAnimals(tree)),
                new MenuItem("Search for an animal", () -> AnimalSearcher.start(tree)),
                new MenuItem("Calculate statistics", () -> GameStatsPrinter.print(tree)),
                new MenuItem("Print the knowledge tree", tree::print)
        );
    }
}
