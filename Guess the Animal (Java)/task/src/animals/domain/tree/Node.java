package animals.domain.tree;

// (De-)Serialization via custom (De-)Serializer
public class Node<T> {
    private T data;
    private Node<T> no;
    private Node<T> yes;

    // No-arg constructor for Jackson
    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }

    public boolean isLeaf() {
        return this.no == null && this.yes == null;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Node<T> getNo() {
        return no;
    }

    public void setNo(Node<T> no) {
        this.no = no;
    }

    public Node<T> getYes() {
        return yes;
    }

    public void setYes(Node<T> yes) {
        this.yes = yes;
    }
}