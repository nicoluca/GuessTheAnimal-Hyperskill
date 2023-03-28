package animals.logic;

import animals.domain.animals.Animal;
import animals.domain.animals.QuestionInterface;
import animals.domain.tree.BinaryTree;
import animals.domain.tree.Node;
import animals.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class AnimalLister {
    // TODO
    public static void listAllAnimals(BinaryTree tree) {
        List<Animal> animals = new ArrayList<>();
        Node node = tree.getRoot();
        searchForAnimals(node, animals);
        List<String> animalNames = animals.stream()
                .map(Animal::getName)
                .map(StringUtil::getWithoutArticle)
                .sorted().toList();
        printAnimals(animalNames);
    }


    private static void searchForAnimals(Node<QuestionInterface> node, List<Animal> animals) {
        // Depth-first search
        if (node.getData() instanceof Animal)
            animals.add((Animal) node.getData());

        if (node.getYes() != null)
            searchForAnimals(node.getYes(), animals);
        if (node.getNo() != null)
            searchForAnimals(node.getNo(), animals);
    }

    private static void printAnimals(List<String> animalNames) {
        System.out.println("Here are the animals I know:");
        animalNames.forEach(s -> System.out.println("- " + s));
    }


}
