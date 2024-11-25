package nl.han.asd;

public class DoubleLinkedListNode<T> implements IDoubleLinkedListNode<T> {
    private T value;
    private IDoubleLinkedListNode<T> prev;
    private IDoubleLinkedListNode<T> next;

    public DoubleLinkedListNode(T value) {
        this.value = value;
        this.prev = null;
        this.next = null;
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
    public IDoubleLinkedListNode<T> getPrev() {
        return prev;
    }

    @Override
    public void setPrev(IDoubleLinkedListNode<T> prev) {
        this.prev = prev;
    }

    @Override
    public IDoubleLinkedListNode<T> getNext() {
        return next;
    }

    @Override
    public void setNext(IDoubleLinkedListNode<T> next) {
        this.next = next;
    }
}
