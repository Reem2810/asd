// DequeIterator.java
package nl.han.asd;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeIterator<T> implements Iterator<T> {
    private final T[] elements;
    private final int start;
    private final int size;
    private int current;

    public DequeIterator(T[] elements, int start, int size) {
        this.elements = elements;
        this.start = start;
        this.size = size;
        this.current = 0;
    }

    @Override
    public boolean hasNext() {
        return current < size;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements to iterate.");
        }
        T element = elements[(start + current) % elements.length];
        current++;
        return element;
    }
}
