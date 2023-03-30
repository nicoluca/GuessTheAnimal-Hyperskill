package animals.util;

import animals.domain.animals.Animal;
import animals.domain.animals.AnimalFact;
import animals.domain.animals.QuestionInterface;
import animals.domain.tree.Node;

public class PrintUtil {
    public static String distinguishingFactPrompt(Animal animal1, Animal animal2) {
        return LocalizationUtil.getMessageWithArguments("formatutil.factprompt", animal1.toString(), animal2.toString());
    }

    public static void printNode(Node<QuestionInterface> node) {
        if (!node.isLeaf()) {
            printAnimalFacts(node);
            System.out.println(LocalizationUtil.getMessage("formatutil.distinguish"));
            System.out.println("- " + node.getData().getQuestion());
        }

        if (!node.getNo().isLeaf())
            printNode(node.getNo());
        if (!node.getYes().isLeaf())
            printNode(node.getYes());
    }

    private static void printAnimalFacts(Node node) {
        if (node.getNo().isLeaf() || node.getYes().isLeaf()) {
            System.out.println(LocalizationUtil.getMessage("formatutil.learnt"));
            if (node.getNo().isLeaf())
                printAnimalFact(node, node.getNo());
            if (node.getYes().isLeaf())
                printAnimalFact(node, node.getYes());
        }
    }

    private static void printAnimalFact(Node node, Node child) {
        String out = LocalizationUtil.getMessage("formatutil.definitearticle") + " " + StringUtil.getWithoutArticle(((Animal) child.getData()).toString()) + " ";
        if (child.equals(node.getNo()))
            out += ((AnimalFact) node.getData()).getStringForFalse();
        else if (child.equals(node.getYes()))
            out += ((AnimalFact) node.getData()).getStringForTrue();
        System.out.println("- " + out);
    }
}
