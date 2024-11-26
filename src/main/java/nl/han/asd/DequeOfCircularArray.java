// DequeOfCircularArray is the choice for implementing a Deque the other implementation is just for curiosity
package nl.han.asd;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeOfCircularArray<T> implements IDeque<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int head;
    private int tail;
    private int size;

    @SuppressWarnings("unchecked")
    public DequeOfCircularArray() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        head = 0;
        tail = 0;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public DequeOfCircularArray(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        elements = (T[]) new Object[initialCapacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public void InsertLeft(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null values are not allowed in the deque.");
        }
        ensureCapacity();
        head = (head - 1 + elements.length) % elements.length;
        elements[head] = element;
        size++;
    }

    @Override
    public void InsertRight(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null values are not allowed in the deque.");
        }
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
    }

    @Override
    public T DeleteLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot delete from an empty deque.");
        }
        T element = elements[head];
        elements[head] = null; // Help garbage collection
        head = (head + 1) % elements.length;
        size--;
        return element;
    }

    @Override
    public T DeleteRight() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot delete from an empty deque.");
        }
        tail = (tail - 1 + elements.length) % elements.length;
        T element = elements[tail];
        elements[tail] = null; // Help garbage collection
        size--;
        return element;
    }
@Override
    public void InsertAllRight(T[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        for (T value : values) {
            if (value == null) {
                throw new IllegalArgumentException("Null values are not allowed in the deque.");
            }
            InsertRight(value);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            if (newCapacity < 0) { // Overflow check
                throw new OutOfMemoryError("Cannot increase capacity beyond Integer.MAX_VALUE");
            }
            T[] newElements = (T[]) new Object[newCapacity];

            // Copy elements to the new array starting from head
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[(head + i) % elements.length];
            }

            elements = newElements;
            head = 0;
            tail = size;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator<>(elements, head, size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CircularArrayDeque: [");
        for (int i = 0; i < size; i++) {
            sb.append(elements[(head + i) % elements.length]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
