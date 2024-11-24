// DoubleLinkedListNode.java
package nl.han.asd;

public class DoubleLinkedListNode<T> implements IDoubleLinkedListNode<T> {
    private T value;
    private IDoubleLinkedListNode<T> next;
    private IDoubleLinkedListNode<T> prev;

    public DoubleLinkedListNode(T value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public IDoubleLinkedListNode<T> getNext() {
        return next;
    }

    @Override
    public void setNext(IDoubleLinkedListNode<T> next) {
        this.next = next;
    }

    @Override
    public IDoubleLinkedListNode<T> getPrev() {
        return prev;
    }

    @Override
    public void setPrev(IDoubleLinkedListNode<T> prev) {
        this.prev = prev;
    }
}
