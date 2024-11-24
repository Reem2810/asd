package nl.han.asd;

public class SingleLinkedListNode<T> {
    private T value;
    private SingleLinkedListNode<T> next;

    public SingleLinkedListNode(T value) {
        this.value = value;
    }

    public void setNext(SingleLinkedListNode<T> next) {
        this.next = next;
    }

    public SingleLinkedListNode<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}