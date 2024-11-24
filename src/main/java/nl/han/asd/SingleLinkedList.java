// SingleLinkedList.java
package nl.han.asd;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class SingleLinkedList<T> implements ISingleLinkedList<T> {
    private ISingleLinkedListNode<T> first = null;
    private ISingleLinkedListNode<T> tail = null; // Tail pointer
    private int size = 0; // Size counter
    private int modCount = 0; // Modification count

    @Override
    public void addFirst(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Null values are not allowed in the list.");
        }
        SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(value);
        newNode.setNext(first);
        first = newNode;

        if (tail == null) { // List was empty before adding
            tail = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public void addLast(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Null values are not allowed in the list.");
        }
        SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(value);
        if (first == null) { // List is empty
            first = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public void clear() {
        first = null;
        tail = null;
        size = 0;
        modCount++;
    }

    @Override
    public void insert(int index, T value) {
        if (value == null) {
            throw new IllegalArgumentException("Null values are not allowed in the list.");
        }
        // Validate the index
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(value);

        // Inserting at the beginning
        if (index == 0) {
            newNode.setNext(first);
            first = newNode;
            if (tail == null) { // Update tail if the list was empty
                tail = newNode;
            }
            size++;
            modCount++;
            return;
        }

        ISingleLinkedListNode<T> current = first;
        int count = 0;

        // Traverse to the node just before the desired index
        while (count < index - 1) {
            current = current.getNext();
            count++;
        }

        // Insert the new node
        newNode.setNext(current.getNext());
        current.setNext(newNode);

        // If inserted at the end, update the tail
        if (current == tail) {
            tail = newNode;
        }

        size++;
        modCount++;
    }

    @Override
    public void delete(int pos) {
        // Validate the position
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("Position: " + pos + ", Size: " + size);
        }

        // Removing the first element
        if (pos == 0) {
            removeFirst();
            return;
        }

        ISingleLinkedListNode<T> current = first;
        int count = 0;

        // Traverse to the node just before the one to remove
        while (count < pos - 1) {
            current = current.getNext();
            count++;
        }

        // Remove the node
        ISingleLinkedListNode<T> toRemove = current.getNext();
        current.setNext(toRemove.getNext());

        // If removing the last node, update the tail
        if (toRemove == tail) {
            tail = current;
        }

        size--;
        modCount++;
    }

    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("Position: " + pos + ", Size: " + size);
        }
        ISingleLinkedListNode<T> current = first;
        int count = 0;

        while (count < pos) {
            current = current.getNext();
            count++;
        }

        return current.getValue();
    }

    @Override
    public void removeFirst() {
        if (first == null) {
            throw new NoSuchElementException("List is empty");
        }

        first = first.getNext();

        if (first == null) { // List became empty after removal
            tail = null;
        }

        size--;
        modCount++;
    }

    @Override
    public T getFirst() {
        if (first == null) {
            throw new NoSuchElementException("List is empty");
        }
        return first.getValue();
    }

    @Override
    public int getSize() {
        return size;
    }

    protected int getModCount() {
        return modCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new SingleLinkedListIterator<>(this, first);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ISingleLinkedListNode<T> current = first;
        while (current != null) {
            sb.append(current.getValue());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
