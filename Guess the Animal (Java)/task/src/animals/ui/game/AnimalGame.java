package animals.ui.game;

import animals.Main;
import animals.domain.animals.Animal;
import animals.domain.animals.AnimalFact;
import animals.util.*;
import animals.domain.animals.QuestionInterface;
import animals.domain.tree.BinaryTree;
import animals.domain.tree.Node;

public class AnimalGame implements Runnable {
    private static AnimalGame instance;
    private final BinaryTree tree;
    private Node<QuestionInterface> currentNode;

    private AnimalGame(BinaryTree binaryTree) {
        this.tree = binaryTree;
    }

    public static AnimalGame getInstance() {
        if (instance == null)
            instance = new AnimalGame(GameManager.getInstance().getTree());
        return instance;
    }

    @Override
    public void run() {
        playGame();
    }

    private void playGame() {
        greetForSavedGame();
        this.currentNode = this.tree.getRoot();

        while (true) {
            Main.LOGGER.info("Current node is " + this.currentNode.getData().toString());
            Main.LOGGER.info("Current node is a leaf: " + this.currentNode.isLeaf());

            boolean isCorrect = CLIUtil.isYesAnswer(this.currentNode.getData().getQuestion());

            if (this.currentNode.isLeaf() && isCorrect) {
                System.out.println(LocalizationUtil.getMessage("constants.success"));
                break;
            } else if (this.currentNode.isLeaf() && !isCorrect) {
                System.out.println(LocalizationUtil.getMessage("constants.negative"));
                addToTree();
                break;
            } else if (isCorrect) {
                this.currentNode = this.currentNode.getYes();
            } else {
                this.currentNode = this.currentNode.getNo();
            }
        }

        wantToPlayAgain();
    }

    private void addToTree() {
        if (!this.currentNode.isLeaf() || !(this.currentNode.getData() instanceof Animal))
            throw new IllegalArgumentException("Node must be a leaf and an animal.");

        Animal animal1 = (Animal) this.currentNode.getData();
        Animal animal2 = new Animal(CLIUtil.getString());
        AnimalFact distinguishingFact = getDistinguishingFact(animal1, animal2);
        boolean trueForAnimal2 = CLIUtil.isYesAnswer(animal2.getFactQuestion());

        addNodesToTree(distinguishingFact, animal1, animal2, trueForAnimal2);

        PrintUtil.printNode(this.currentNode);
    }

    private void addNodesToTree(AnimalFact distinguishingFact, Animal animal1, Animal animal2, boolean trueForAnimal2) {
        this.currentNode.setData(distinguishingFact);
        Node<QuestionInterface> newAnimal1Node = new Node<>(animal1);
        Node<QuestionInterface> newAnimal2Node = new Node<>(animal2);
        this.currentNode.setNo(trueForAnimal2 ? newAnimal1Node : newAnimal2Node);
        this.currentNode.setYes(trueForAnimal2 ? newAnimal2Node : newAnimal1Node);
    }

    private void wantToPlayAgain() {
        String prompt = LocalizationUtil.getMessage("constants.playagain");
        if (CLIUtil.isYesAnswer(prompt)) {
            playGame();
        }
    }

    private static AnimalFact getDistinguishingFact(Animal animal1, Animal animal2) {
        String prompt = PrintUtil.distinguishingFactPrompt(animal1, animal2);
        String distinguishingFact = CLIUtil.getString(prompt);

        while (!StringUtil.sentenceIsFact(distinguishingFact))
            distinguishingFact = CLIUtil.getString(prompt);

        return AnimalFact.generateFromString(distinguishingFact);
    }

    private void greetForSavedGame() {
        System.out.println(LocalizationUtil.getMessage("constants.savedgamegreeting"));
        CLIUtil.getString(); // wait for user to press enter
    }

}
