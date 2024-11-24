
package nl.han.asd;

public class SingleLinkedListNode<T> implements ISingleLinkedListNode<T> {
    private T value;
    private ISingleLinkedListNode<T> next;

    public SingleLinkedListNode(T value) {
        this.value = value;
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
    public ISingleLinkedListNode<T> getNext() {
        return next;
    }

    @Override
    public void setNext(ISingleLinkedListNode<T> next) {
        this.next = next;
    }
}
