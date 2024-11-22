package nl.han.asd;

/**
 * Represents a node in a doubly linked list.
 *
 * @param <T> The type of element stored in the node.
 */
public class DoublyLinkedListNode<T> {
    private T value;
    private DoublyLinkedListNode<T> next;
    private DoublyLinkedListNode<T> prev;

    /**
     * Constructs a new node with the specified value.
     *
     * @param value The value to store in the node.
     */
    public DoublyLinkedListNode(T value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    // Getters and Setters

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public DoublyLinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(DoublyLinkedListNode<T> next) {
        this.next = next;
    }

    public DoublyLinkedListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DoublyLinkedListNode<T> prev) {
        this.prev = prev;
    }
}
