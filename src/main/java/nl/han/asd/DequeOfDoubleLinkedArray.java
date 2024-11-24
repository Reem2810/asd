// DequeOfDoubleLinkedArray.java
package nl.han.asd;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeOfDoubleLinkedArray<T> implements IDeque<T> {
    private DoubleLinkedList<T> list;

    public DequeOfDoubleLinkedArray() {
        this.list = new DoubleLinkedList<>();
    }

    @Override
    public void InsertLeft(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null values are not allowed in the deque.");
        }
        list.addFirst(element);
    }

    @Override
    public void InsertRight(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null values are not allowed in the deque.");
        }
        list.addLast(element);
    }

    @Override
    public T DeleteLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot delete from an empty deque.");
        }
        return list.removeFirst();
    }

    @Override
    public T DeleteRight() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot delete from an empty deque.");
        }
        return list.removeLast();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
