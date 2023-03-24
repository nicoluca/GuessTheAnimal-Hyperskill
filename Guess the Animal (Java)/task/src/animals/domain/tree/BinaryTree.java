package animals.domain.tree;

import animals.ui.AnimalGame;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BinaryTree {
    private final static Logger LOGGER = Logger.getLogger(AnimalGame.class.getName());
    private Node root;
    private int size;

    public BinaryTree(Node root) {
        LOGGER.setLevel(Level.INFO);
        this.root = root;
        this.size = 1;
    }

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void replaceLeaf(Node leafToReplace, Node replacingNode) {
        LOGGER.info("Replacing " + leafToReplace.getData().toString() + " with " + replacingNode.getData());
        leafToReplace = replacingNode;
        if (this.root.equals(leafToReplace))
            LOGGER.info("Replacing root with " + replacingNode.getData().toString());
            this.root = replacingNode;

        this.size += 2;

    }

    public int getSize() {
        return size;
    }
}
