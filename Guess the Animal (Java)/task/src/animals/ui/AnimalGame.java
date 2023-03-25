package animals.ui;

import animals.domain.animals.Animal;
import animals.domain.animals.AnimalFact;
import animals.domain.Constants;
import animals.domain.animals.QuestionInterface;
import animals.domain.tree.BinaryTree;
import animals.domain.tree.Node;
import animals.storage.FileManager;
import animals.util.CLIUtil;
import animals.util.FormatUtil;
import animals.util.StringUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AnimalGame {
    private final static Logger LOGGER = Logger.getLogger(AnimalGame.class.getName());
    private final FileManager fileManager;

    private BinaryTree tree;
    private Node<QuestionInterface> currentNode;

    public AnimalGame(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void start() {
        LOGGER.setLevel(Level.INFO);
        LOGGER.info("Starting game");
        greet();
        
        Animal animal = new Animal(CLIUtil.getString());
        this.currentNode = new Node<>(animal);
        this.tree = new BinaryTree(this.currentNode);

        playGame();
        saveGame();
    }

    private void playGame() {
        secondGreet();
        CLIUtil.getString(); // wait for user to press enter
        this.currentNode = this.tree.getRoot();

        while (true) {
            LOGGER.info("Current node is " + this.currentNode.getData().toString());
            boolean isCorrect = CLIUtil.isYesAnswer(this.currentNode.getData().getQuestion());

            if (this.currentNode.isLeaf() && isCorrect) {
                System.out.println(Constants.correctGuess());
                break;
            } else if (this.currentNode.isLeaf() && !isCorrect) {
                System.out.println(Constants.getNegativeResponse());
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

    private void saveGame() {
        this.fileManager.save(this.tree.getRoot());
    }

    private void addToTree() {
        if (!this.currentNode.isLeaf() || !(this.currentNode.getData() instanceof Animal))
            throw new IllegalArgumentException("Node must be a leaf and an animal.");

        Animal animal1 = (Animal) this.currentNode.getData();
        Animal animal2 = new Animal(CLIUtil.getString());
        AnimalFact distinguishingFact = getDistinguishingFact(animal1, animal2);
        boolean trueForAnimal2 = CLIUtil.isYesAnswer(animal2.getFactQuestion());

        addNodesToTree(distinguishingFact, animal1, animal2, trueForAnimal2);

        FormatUtil.printNode(this.currentNode);
    }

    private void addNodesToTree(AnimalFact distinguishingFact, Animal animal1, Animal animal2, boolean trueForAnimal2) {
        this.currentNode.setData(distinguishingFact);
        Node<QuestionInterface> newAnimal1Node = new Node<>(animal1);
        Node<QuestionInterface> newAnimal2Node = new Node<>(animal2);
        this.currentNode.setNo(trueForAnimal2 ? newAnimal1Node : newAnimal2Node);
        this.currentNode.setYes(trueForAnimal2 ? newAnimal2Node : newAnimal1Node);
    }

    private void wantToPlayAgain() {
        String prompt = Constants.getPlayAgain();
        if (CLIUtil.isYesAnswer(prompt))
            playGame();
    }

    private static void greet() {
        System.out.println(Constants.getGameGreeting());
    }

    private static void secondGreet() {
        System.out.println(Constants.getSecondGameGreeting());
    }

    private static AnimalFact getDistinguishingFact(Animal animal1, Animal animal2) {
        String prompt = FormatUtil.distinguishingFactPrompt(animal1, animal2);
        String distinguishingFact = CLIUtil.getString(prompt);

        while (!StringUtil.sentenceIsFact(distinguishingFact))
            distinguishingFact = CLIUtil.getString(prompt);

        return AnimalFact.generateFromString(distinguishingFact);
    }
}
