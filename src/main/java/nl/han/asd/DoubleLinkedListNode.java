package nl.han.asd;


public class DoubleLinkedListNode<T> {
    private T value;
    private DoubleLinkedListNode<T> next;
    private DoubleLinkedListNode<T> prev;


    public DoubleLinkedListNode(T value) {
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

    public DoubleLinkedListNode<T> getNext() {

        return next;
    }

    public void setNext(DoubleLinkedListNode<T> next) {

        this.next = next;
    }

    public DoubleLinkedListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DoubleLinkedListNode<T> prev) {

        this.prev = prev;
    }
}
