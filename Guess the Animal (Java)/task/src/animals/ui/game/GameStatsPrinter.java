package animals.ui.game;

import animals.domain.animals.Animal;
import animals.domain.animals.AnimalFact;
import animals.domain.tree.BinaryTree;
import animals.domain.tree.TreeStats;
import animals.util.LocalizationUtil;

public class GameStatsPrinter {
    public static void print(BinaryTree tree) {
        TreeStats stats = new TreeStats(tree);
        System.out.println(LocalizationUtil.getMessage("gamestatsprinter.title") + "\n");

        String rootString = "It ";
        rootString += tree.getRoot().getData() instanceof Animal
                ? ((Animal) tree.getRoot().getData()).getName()
                : ((AnimalFact) tree.getRoot().getData()).getStringForTrue();

        System.out.println("- " + LocalizationUtil.getMessage("gamestatsprinter.root") + rootString);
        System.out.println("- " + LocalizationUtil.getMessage("gamestatsprinter.nodes") + (stats.getNumberOfNodes() + stats.getNumberOfLeaves()));
        System.out.println("- " + LocalizationUtil.getMessage("gamestatsprinter.animals") + stats.getNumberOfLeaves());
        System.out.println("- " + LocalizationUtil.getMessage("gamestatsprinter.statements") + stats.getNumberOfNodes());
        System.out.println("- " + LocalizationUtil.getMessage("gamestatsprinter.depth") + stats.getMaxDepth());
        System.out.println("- " + LocalizationUtil.getMessage("gamestatsprinter.minimumdepth") + stats.getMinLeafDepth());
        System.out.println("- " + LocalizationUtil.getMessage("gamestatsprinter.averagedepth") + stats.getAverageLeafDepth());
    }
}


