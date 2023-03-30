package animals.ui.menu;

import animals.util.LocalizationUtil;
import animals.domain.tree.BinaryTree;
import animals.ui.game.*;

import java.util.List;

public class MenuConfig {
    public static String getMenuTitle() {
        return LocalizationUtil.getMessage("menu.config.title") + "\n";
    }

    public static List<MenuItem> getMenuItems() {
        BinaryTree tree = GameManager.getInstance().getTree();
        return List.of(
                new MenuItem(LocalizationUtil.getMessage("menu.config.exit"), () -> TextMenu.getInstance().stop()), // Exit menu needs to be first
                new MenuItem(LocalizationUtil.getMessage("menu.config.play"), () -> AnimalGame.getInstance().run()),
                new MenuItem(LocalizationUtil.getMessage("menu.config.list"), () -> AnimalLister.listAllAnimals(tree)),
                new MenuItem(LocalizationUtil.getMessage("menu.config.search"), () -> AnimalSearcher.start(tree)),
                new MenuItem(LocalizationUtil.getMessage("menu.config.stats"), () -> GameStatsPrinter.print(tree)),
                new MenuItem(LocalizationUtil.getMessage("menu.config.tree"), tree::print)
        );
    }
}
