package animals.domain.tree;

public class BinaryTree {
    private final Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return this.root;
    }

    @Override
    public String toString() {
        return this.root.toString();
    }

    public void print() {
        printNode(this.root, "", false);
    }

    private static void printNode(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.getData().toString());
            printNode(node.getYes(), prefix + (isLeft ? "│   " : "    "), true);
            printNode(node.getNo(), prefix + (isLeft ? "│   " : "    "), false);
        }
    }
}
