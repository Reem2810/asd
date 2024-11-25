package nl.han.asd;

public interface IDoubleLinkedListNode<T> {
    T getValue();
    void setValue(T value);
    IDoubleLinkedListNode<T> getNext();
    void setNext(IDoubleLinkedListNode<T> next);
    IDoubleLinkedListNode<T> getPrev();
    void setPrev(IDoubleLinkedListNode<T> prev);
}
