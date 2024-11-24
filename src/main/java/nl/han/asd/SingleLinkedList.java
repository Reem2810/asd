package nl.han.asd;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedList<T> implements ISingleLinkedList<T> {
    private SingleLinkedListNode<T> first = null;
    private SingleLinkedListNode<T> tail = null; // Tail pointer
    private int size = 0; // Size counter

    @Override
    public void addFirst(T value) {
        SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(value);
        newNode.setNext(first);
        first = newNode;

        if (tail == null) { // List was empty before adding
            tail = newNode;
        }

        size++;
    }

    @Override
    public void addLast(T value) {
        SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(value);
        if (first == null) { // List is empty
            first = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    @Override
    public void clear() {
        first = null;
        tail = null;
        size = 0;
    }

    @Override
    public void insert(int index, T value) {
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
            return;
        }

        SingleLinkedListNode<T> current = first;
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

        SingleLinkedListNode<T> current = first;
        int count = 0;

        // Traverse to the node just before the one to remove
        while (count < pos - 1) {
            current = current.getNext();
            count++;
        }

        // Remove the node
        SingleLinkedListNode<T> toRemove = current.getNext();
        current.setNext(toRemove.getNext());

        // If removing the last node, update the tail
        if (toRemove == tail) {
            tail = current;
        }

        size--;
    }

    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("Position: " + pos + ", Size: " + size);
        }
        SingleLinkedListNode<T> current = first;
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

    @Override
    public Iterator<T> iterator() {
        return new SingleLinkedListIterator<>(first);
    }
}