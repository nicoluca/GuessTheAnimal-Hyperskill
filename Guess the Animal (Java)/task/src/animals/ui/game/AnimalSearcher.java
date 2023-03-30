package animals.ui.game;

import animals.domain.animals.Animal;
import animals.domain.animals.AnimalFact;
import animals.domain.tree.BinaryTree;
import animals.domain.tree.Node;
import animals.util.CLIUtil;
import animals.util.LocalizationUtil;
import animals.util.StringUtil;

import java.util.Deque;
import java.util.LinkedList;

public class AnimalSearcher {

    public static void start(BinaryTree tree) {
        String prompt = LocalizationUtil.getMessage("animalsearcher.getfact");
        String animalName = CLIUtil.getString(prompt);
        animalName = StringUtil.getWithoutArticle(animalName).toLowerCase();
        search(animalName, tree);
    }

    private static void search(String animalName, BinaryTree tree) {
        Deque<Node> nodes = new LinkedList<>();
        nodes.push(tree.getRoot());

        boolean found = depthFirstSearch(animalName, nodes);

        if (found)
            printFacts(animalName, nodes);
        else
            System.out.println(LocalizationUtil.getMessageWithArgument("animalsearcher.notfound", animalName));
    }

    private static boolean depthFirstSearch(String animalName, Deque<Node> nodes) {
        // Recursive depth-first search, returns true if animalName is found, adds nodes to the stack on the way
        if (nodes.peek().getData() instanceof Animal currentAnimal) {
            if (StringUtil.getWithoutArticle(currentAnimal.getName()).equalsIgnoreCase(animalName)) {
                return true;
            }
        }

        if (!nodes.peek().isLeaf()) {
            nodes.push(nodes.peek().getYes());
            if (depthFirstSearch(animalName, nodes))
                return true;
            nodes.push(nodes.peek().getNo());
            if (depthFirstSearch(animalName, nodes))
                return true;
        }

        nodes.pop();
        return false;
    }

    private static void printFacts(String animalName, Deque<Node> nodes) {
        System.out.println(LocalizationUtil.getMessageWithArgument("animalsearcher.found", animalName));

        // Prints from bottom to top, as the stack is reversed
        while (nodes.size() > 1) {
            Node currentFactNode = nodes.removeLast();
            boolean isYes = currentFactNode.getYes().equals(nodes.peekLast());
            AnimalFact currentFact = (AnimalFact) currentFactNode.getData();
            String fact = isYes ? currentFact.getStringForTrue() : currentFact.getStringForFalse();
            System.out.println("- " + LocalizationUtil.getMessageWithArgument("animalsearcher.fact", fact));
        }
    }
}