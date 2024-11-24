// ISingleLinkedListNode.java
package nl.han.asd;

public interface ISingleLinkedListNode<T> {
    T getValue();
    void setValue(T value);
    ISingleLinkedListNode<T> getNext();
    void setNext(ISingleLinkedListNode<T> next);
}
