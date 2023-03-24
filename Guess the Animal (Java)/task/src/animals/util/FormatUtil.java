package animals.util;

import animals.domain.animals.Animal;
import animals.domain.animals.AnimalFact;
import animals.domain.animals.QuestionInterface;
import animals.domain.tree.Node;

public class FormatUtil {
    public static String distinguishingFactPrompt(Animal animal1, Animal animal2) {
        return "Specify a fact that distinguishes " + animal1.toString() + " from " + animal2.toString() + ".\n" +
                "The sentence should satisfy one of the following templates:\n" +
                "- It can ...\n" +
                "- It has ...\n" +
                "- It is a/an ...\n";
    }

    public static void printNode(Node<QuestionInterface> node) {
        if (!node.isLeaf()) {
            printAnimalFacts(node);
            System.out.println("I can distinguish these animals by asking the question:");
            System.out.println("- " + node.getData().getQuestion());
        }

        if (!node.getLeft().isLeaf())
            printNode(node.getLeft());
        if (!node.getRight().isLeaf())
            printNode(node.getRight());
    }

    private static void printAnimalFacts(Node node) {
        if (node.getLeft().isLeaf() || node.getRight().isLeaf()) {
            System.out.println("I have learned the following facts about animals:");
            if (node.getLeft().isLeaf())
                printAnimalFact(node, node.getLeft());
            if (node.getRight().isLeaf())
                printAnimalFact(node, node.getRight());
        }
    }

    private static void printAnimalFact(Node node, Node child) {
        String out = "The " + StringUtil.getWithoutArticle(((Animal) child.getData()).toString()) + " ";
        if (child.equals(node.getLeft()))
            out += ((AnimalFact) node.getData()).getStringForFalse();
        else if (child.equals(node.getRight()))
            out += ((AnimalFact) node.getData()).getStringForTrue();
        System.out.println("- " + out);
    }
}
