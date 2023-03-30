package animals.ui.game;

import animals.domain.animals.Animal;
import animals.domain.animals.QuestionInterface;
import animals.domain.tree.BinaryTree;
import animals.domain.tree.Node;
import animals.util.LocalizationUtil;
import animals.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class AnimalLister {
    public static void listAllAnimals(BinaryTree tree) {
        List<Animal> animals = new ArrayList<>();
        Node node = tree.getRoot();
        addAnimalsToList(node, animals);
        List<String> animalNames = animals.stream()
                .map(Animal::getName)
                .map(StringUtil::getWithoutArticle)
                .sorted().toList();
        printAnimals(animalNames);
    }

    private static void addAnimalsToList(Node<QuestionInterface> node, List<Animal> animals) {
        // Depth-first search
        if (node.getData() instanceof Animal)
            animals.add((Animal) node.getData());

        if (node.getYes() != null)
            addAnimalsToList(node.getYes(), animals);
        if (node.getNo() != null)
            addAnimalsToList(node.getNo(), animals);
    }

    private static void printAnimals(List<String> animalNames) {
        System.out.println(LocalizationUtil.getMessage("animallister.title"));
        animalNames.forEach(s -> System.out.println("- " + s));
    }


}
