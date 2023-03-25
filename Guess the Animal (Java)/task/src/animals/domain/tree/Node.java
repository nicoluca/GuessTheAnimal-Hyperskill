package animals.domain.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Node<T> {
    private final T data;
    private Node<T> no;
    private Node<T> yes;
    private Node<T> parent;

    public Node(T data) {
        this.data = data;
    }

    @JsonIgnore
    public boolean isLeaf() {
        return this.no == null && this.yes == null;
    }

    public T getData() {
        return data;
    }

    public Node<T> getNo() {
        return no;
    }

    public void setNo(Node<T> no) {
        this.no = no;
        this.no.setParent(this);
    }

    private void setParent(Node<T> parentNode) {
        this.parent = parentNode;
    }

    public Node<T> getParent() {
        return parent;
    }

    public Node<T> getYes() {
        return yes;
    }

    public void setYes(Node<T> yes) {
        this.yes = yes;
        this.yes.setParent(this);
    }

}
