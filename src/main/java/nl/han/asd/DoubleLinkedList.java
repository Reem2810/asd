package nl.han.asd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements a doubly linked list with basic operations.
 *
 * @param <T> The type of elements stored in the list.
 */
public class DoubleLinkedList<T> implements Iterable<T> {
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    /**
     * Constructs an empty DoublyLinkedList.
     */
    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element The element to add.
     */
    public void add(T element) {
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
    }

    /**
     * Retrieves the element at the specified index.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public T get(int index) {
        checkIndex(index);
        DoublyLinkedListNode<T> current = getNodeAt(index);
        return current.getValue();
    }

    /**
     * Updates the element at the specified index.
     *
     * @param index   The index of the element to update.
     * @param element The new element to set.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void set(int index, T element) {
        checkIndex(index);
        DoublyLinkedListNode<T> current = getNodeAt(index);
        current.setValue(element);
    }

    /**
     * Removes the element at the specified index.
     *
     * @param index The index of the element to remove.
     * @return The removed element.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public T remove(int index) {
        checkIndex(index);
        DoublyLinkedListNode<T> toRemove = getNodeAt(index);
        T removedValue = toRemove.getValue();

        if (toRemove == head && toRemove == tail) { // Only one element
            head = null;
            tail = null;
        } else if (toRemove == head) { // Removing head
            head = head.getNext();
            head.setPrev(null);
        } else if (toRemove == tail) { // Removing tail
            tail = tail.getPrev();
            tail.setNext(null);
        } else { // Removing from middle
            DoublyLinkedListNode<T> prevNode = toRemove.getPrev();
            DoublyLinkedListNode<T> nextNode = toRemove.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }

        size--;
        return removedValue;
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     *
     * @param element The element to remove.
     * @return true if the element was found and removed; false otherwise.
     */
    public boolean remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param element The element to check.
     * @return true if the list contains the element; false otherwise.
     */
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    /**
     * Finds the index of the first occurrence of the specified element.
     *
     * @param element The element to find.
     * @return The index of the element; -1 if not found.
     */
    public int indexOf(T element) {
        int index = 0;
        for (DoublyLinkedListNode<T> current = head; current != null; current = current.getNext()) {
            if ((element == null && current.getValue() == null) ||
                    (element != null && element.equals(current.getValue()))) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list has no elements; false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears all elements from the list.
     */
    public void clear() {
        DoublyLinkedListNode<T> current = head;
        while (current != null) {
            DoublyLinkedListNode<T> next = current.getNext();
            current.setPrev(null);
            current.setNext(null);
            current.setValue(null);
            current = next;
        }
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns an iterator over the elements in the list in proper sequence.
     *
     * @return An iterator over the list.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private DoublyLinkedListNode<T> current = head;
            private int expectedModCount = size; // Simple fail-fast using size

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                T value = current.getValue();
                current = current.getNext();
                return value;
            }
        };
    }

    /**
     * Returns a string representation of the list.
     *
     * @return A string representing the list elements.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        DoublyLinkedListNode<T> current = head;
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

    // Helper Methods

    /**
     * Retrieves the node at the specified index.
     *
     * @param index The index of the node to retrieve.
     * @return The node at the specified index.
     */
    private DoublyLinkedListNode<T> getNodeAt(int index) {
        // Optimize traversal by starting from head or tail based on index
        if (index < size / 2) {
            DoublyLinkedListNode<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current;
        } else {
            DoublyLinkedListNode<T> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
            return current;
        }
    }

    /**
     * Checks if the given index is within the bounds of the list.
     *
     * @param index The index to check.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
