package animals.ui.game;

import animals.domain.animals.Animal;
import animals.domain.animals.AnimalFact;
import animals.domain.tree.BinaryTree;
import animals.domain.tree.TreeStats;

public class GameStatsPrinter {
    public static void print(BinaryTree tree) {
        TreeStats stats = new TreeStats(tree);
        System.out.println("The Knowledge Tree stats\n");

        String rootString = "It ";
        rootString += tree.getRoot().getData() instanceof Animal
                ? ((Animal) tree.getRoot().getData()).getName()
                : ((AnimalFact) tree.getRoot().getData()).getStringForTrue();

        System.out.println("- root node                  " + rootString);
        System.out.println("- total number of nodes      " + (stats.getNumberOfNodes() + stats.getNumberOfLeaves()));
        System.out.println("- total number of animals    " + stats.getNumberOfLeaves());
        System.out.println("- total number of statements " + stats.getNumberOfNodes());
        System.out.println("- height of the tree         " + stats.getMaxDepth());
        System.out.println("- minimum animal's depth     " + stats.getMinLeafDepth());
        System.out.println("- average animal's depth     " + stats.getAverageLeafDepth());
    }
}


