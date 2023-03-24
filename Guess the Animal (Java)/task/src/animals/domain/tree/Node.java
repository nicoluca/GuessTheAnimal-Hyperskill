package animals.domain.tree;

public class Node<T> {
    private final T data;
    private Node<T> left;
    private Node<T> right;

    public Node(T data) {
        this.data = data;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public T getData() {
        return data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}
