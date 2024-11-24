// DynamicArrayIterator.java
package nl.han.asd;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArrayIterator<E> implements Iterator<E> {
    private final E[] elements;
    private final int size;
    private final int expectedModCount;
    private int currentIndex;
    private final int modCount;

    @SuppressWarnings("unchecked")
    public DynamicArrayIterator(E[] elements, int size, int modCount) {
        this.elements = elements;
        this.size = size;
        this.modCount = modCount;
        this.expectedModCount = modCount;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        checkForModification();
        return currentIndex < size;
    }

    @Override
    public E next() {
        checkForModification();
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements to iterate.");
        }
        return elements[currentIndex++];
    }

    private void checkForModification() {
        if (expectedModCount != modCount) {
            throw new ConcurrentModificationException("DynamicArray was modified during iteration.");
        }
    }
}
