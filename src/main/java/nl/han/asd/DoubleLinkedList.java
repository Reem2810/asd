// DoubleLinkedList.java
package nl.han.asd;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class DoubleLinkedList<T> implements IDoubleLinkedList<T> {
    private DoubleLinkedListNode<T> head;
    private DoubleLinkedListNode<T> tail;
    private int size;
    private int modCount; // For fail-fast iterators

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.modCount = 0;
    }

    @Override
    public void add(T element) {
        addLast(element);
    }


    @Override
    public void addFirst(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null values are not allowed in the list.");
        }

        DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }

        size++;
        modCount++;
    }


    @Override
    public void addLast(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null values are not allowed in the list.");
        }

        DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }

        size++;
        modCount++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from an empty list.");
        }

        T value = head.getValue();

        if (head == tail) { // Only one element
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }

        size--;
        modCount++;
        return value;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from an empty list.");
        }

        T value = tail.getValue();

        if (head == tail) { // Only one element
            head = null;
            tail = null;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
        }

        size--;
        modCount++;
        return value;
    }


    @Override
    public T get(int index) {
        checkIndex(index);
        DoubleLinkedListNode<T> current = getNodeAt(index);
        return current.getValue();
    }

    @Override
    public void set(int index, T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null values are not allowed in the list.");
        }
        checkIndex(index);
        DoubleLinkedListNode<T> current = getNodeAt(index);
        current.setValue(element);
        modCount++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        DoubleLinkedListNode<T> toRemove = getNodeAt(index);
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
            DoubleLinkedListNode<T> prevNode = toRemove.getPrev();
            DoubleLinkedListNode<T> nextNode = toRemove.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }

        size--;
        modCount++;
        return removedValue;
    }

    @Override
    public boolean remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    @Override
    public int indexOf(T element) {
        int index = 0;
        for (DoubleLinkedListNode<T> current = head; current != null; current = current.getNext()) {
            if ((element == null && current.getValue() == null) ||
                    (element != null && element.equals(current.getValue()))) {
                return index;
            }
            index++;
        }
        return -1;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        DoubleLinkedListNode<T> current = head;
        while (current != null) {
            DoubleLinkedListNode<T> next = current.getNext();
            current.setPrev(null);
            current.setNext(null);
            current.setValue(null);
            current = next;
        }
        head = null;
        tail = null;
        size = 0;
        modCount++;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private DoubleLinkedListNode<T> current = head;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("List modified during iteration.");
                }
                return current != null;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("List modified during iteration.");
                }
                if (current == null) {
                    throw new NoSuchElementException("No more elements to iterate.");
                }
                T value = current.getValue();
                current = current.getNext();
                return value;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        DoubleLinkedListNode<T> current = head;
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

    private DoubleLinkedListNode<T> getNodeAt(int index) {
        // Optimize traversal by starting from head or tail based on index
        if (index < size / 2) {
            DoubleLinkedListNode<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current;
        } else {
            DoubleLinkedListNode<T> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
            return current;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
