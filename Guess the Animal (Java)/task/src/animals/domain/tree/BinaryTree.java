package animals.domain.tree;

import animals.ui.AnimalGame;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BinaryTree {
    private final static Logger LOGGER = Logger.getLogger(AnimalGame.class.getName());
    private Node root;
    private Node current;

    public BinaryTree(Node root) {
        LOGGER.setLevel(Level.INFO);
        this.root = root;
    }

    public Node getRoot() {
        return this.root;
    }

    public Node getNo(Node node) {
        isTraversalPossible(node);
        this.current = node.getNo();
        return this.current;
    }

    public Node getYes(Node node) {
        isTraversalPossible(node);
        this.current = node.getYes();
        return this.current;
    }

    private void isTraversalPossible(Node node) {
        if (node == null)
            throw new IllegalArgumentException("Node is null");
        if (node.isLeaf())
            throw new IllegalArgumentException("Node is a leaf");
    }

    public void replaceCurrent(Node replacingNode) {
        if (replacingNode == null)
            throw new IllegalArgumentException("Replacing node is null");
        if (this.current == null)
            throw new IllegalArgumentException("Current node is null");

        if (this.current.getParent() == null) {
            LOGGER.info("Replacing root with " + replacingNode.getData().toString());
            this.root = replacingNode;
        } else {
            LOGGER.info("Replacing " + this.current.getData().toString() + " with " + replacingNode.getData().toString());
            if (this.current.getParent().getNo().equals(this.current))
                this.current.getParent().setNo(replacingNode);
            else
                this.current.getParent().setYes(replacingNode);
        }
    }
}
