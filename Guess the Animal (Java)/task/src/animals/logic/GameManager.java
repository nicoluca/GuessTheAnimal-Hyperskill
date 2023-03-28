package animals.logic;

import animals.Main;
import animals.domain.GameConstants;
import animals.domain.animals.Animal;
import animals.domain.animals.QuestionInterface;
import animals.domain.tree.BinaryTree;
import animals.domain.tree.Node;
import animals.storage.FileManager;
import animals.util.CLIUtil;

import java.io.IOException;

public class GameManager {
    private static GameManager instance;
    private FileManager fileManager;

    private BinaryTree tree;

    public static GameManager getInstance() {
        if (instance == null)
            instance = new GameManager(Main.fileManager);
        return instance;
    }

    private GameManager(FileManager fileManager) {
        this.fileManager = fileManager;
        if (fileManager.savedGameAvailable())
            loadGame();
        else
            initializeNewGame();
    }

    public synchronized BinaryTree getTree() {
        return this.tree;
    }

    private void initializeNewGame() {
        Main.LOGGER.info("No saved game found. Starting new game, querying for first animal.");
        greetForNewGame();
        Animal animal = new Animal(CLIUtil.getString());
        Node<QuestionInterface> firstNode = new Node<>(animal);
        this.tree = new BinaryTree(firstNode);
        secondGreet();
    }

    private static void greetForNewGame() {
        Main.LOGGER.info("Starting new game.");
        System.out.println(GameConstants.getGameGreeting());
    }


    private static void secondGreet() {
        System.out.println(GameConstants.getSecondGameGreeting());
    }


    public void saveGame() {
        this.fileManager.save(this.tree.getRoot());
    }


    private void loadGame() {
        if (!fileManager.savedGameAvailable())
            throw new IllegalStateException("No saved game available.");

        Main.LOGGER.info("Saved game found. Starting saved game.");
        Node<QuestionInterface> firstNode;

        try {
            firstNode = (Node<QuestionInterface>) this.fileManager.load();
            this.tree = new BinaryTree(firstNode);
        } catch (IOException e) {
            Main.LOGGER.severe("Error loading saved game.");
            throw new RuntimeException(e);
        } catch (ClassCastException e) {
            Main.LOGGER.severe("Not a valid saved game.");
            throw new RuntimeException(e);
        }
        Main.LOGGER.info("Saved game loaded successfully.");
        Main.LOGGER.info("Root node is " + firstNode.getData().toString());
    }

}
