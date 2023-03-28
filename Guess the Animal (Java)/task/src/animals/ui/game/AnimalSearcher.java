package animals.ui.game;

import animals.domain.animals.Animal;
import animals.domain.animals.AnimalFact;
import animals.domain.tree.BinaryTree;
import animals.domain.tree.Node;
import animals.util.CLIUtil;
import animals.util.StringUtil;

import java.util.Deque;
import java.util.LinkedList;

public class AnimalSearcher {

    public static void start(BinaryTree tree) {
        String animalName = CLIUtil.getString("Enter the animal:");
        animalName = StringUtil.getWithoutArticle(animalName).toLowerCase();
        search(animalName, tree);
    }

    private static void search(String animalName, BinaryTree tree) {
        Deque<Node> nodes = new LinkedList<>();
        nodes.push(tree.getRoot());

        boolean found = traverseNodes(animalName, nodes);

        if (found)
            printFacts(animalName, nodes);
        else
            System.out.println("No facts about the " + animalName);
    }

    private static void printFacts(String animalName, Deque<Node> nodes) {
        System.out.println("Facts about the " + animalName + ":");

        while (nodes.size() > 1) {
            Node currentFactNode = nodes.removeLast();
            boolean isYes = currentFactNode.getYes().equals(nodes.peekLast()) ? true : false;
            AnimalFact currentFact = (AnimalFact) currentFactNode.getData();
            String fact = isYes ? currentFact.getStringForTrue() : currentFact.getStringForFalse();
            System.out.println("- It " + fact);
        }
    }

    private static boolean traverseNodes(String animalName, Deque<Node> nodes) {
        // Check if correct animal
        if (nodes.peek().getData() instanceof Animal currentAnimal) {
            if (StringUtil.getWithoutArticle(currentAnimal.getName()).equalsIgnoreCase(animalName)) {
                return true;
            }
        }

        if (!nodes.peek().isLeaf()) {
            nodes.push(nodes.peek().getYes());
            if (traverseNodes(animalName, nodes))
                return true;
            nodes.push(nodes.peek().getNo());
            if (traverseNodes(animalName, nodes))
                return true;
        }

        nodes.pop();
        return false;
    }
}