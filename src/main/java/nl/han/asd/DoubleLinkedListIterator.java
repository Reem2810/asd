// DoubleLinkedListIterator.java
package nl.han.asd;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class DoubleLinkedListIterator<T> implements Iterator<T> {
    private IDoubleLinkedListNode<T> current;
    private final int expectedModCount;
    private final DoubleLinkedList<T> list;

    public DoubleLinkedListIterator(DoubleLinkedList<T> list, IDoubleLinkedListNode<T> first) {
        this.current = first;
        this.list = list;
        this.expectedModCount = list.getModCount();
    }

    @Override
    public boolean hasNext() {
        if (expectedModCount != list.getModCount()) {
            throw new ConcurrentModificationException("List modified during iteration.");
        }
        return current != null;
    }

    @Override
    public T next() {
        if (expectedModCount != list.getModCount()) {
            throw new ConcurrentModificationException("List modified during iteration.");
        }
        if (current == null) {
            throw new NoSuchElementException("No more elements to iterate.");
        }
        T value = current.getValue();
        current = current.getNext();
        return value;
    }
}
