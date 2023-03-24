package animals.domain.tree;

import animals.ui.AnimalGame;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BinaryTree {
    private final static Logger LOGGER = Logger.getLogger(AnimalGame.class.getName());
    private Node root;

    public BinaryTree(Node root) {
        LOGGER.setLevel(Level.INFO);
        this.root = root;
    }

    public Node getRoot() {
        return this.root;
    }


    public void replaceNode(Node nodeToReplace, Node replacingNode) {
        if (nodeToReplace == null || replacingNode == null)
            throw new IllegalArgumentException("Node to replace or replacing node is null");
        if (nodeToReplace.getLeft() != null || nodeToReplace.getRight() != null)
            LOGGER.warning("Replacing node has children");

        if (nodeToReplace.getParent() == null) {
            LOGGER.info("Replacing root with " + replacingNode.getData().toString());
            this.root = replacingNode;
        } else {
            LOGGER.info("Replacing " + nodeToReplace.getData().toString() + " with " + replacingNode.getData().toString());
            if (nodeToReplace.getParent().getLeft().equals(nodeToReplace))
                nodeToReplace.getParent().setLeft(replacingNode);
            else
                nodeToReplace.getParent().setRight(replacingNode);
        }
    }
}
