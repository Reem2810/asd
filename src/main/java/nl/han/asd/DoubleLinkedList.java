package nl.han.asd;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements IDoubleLinkedList<T> {
    private IDoubleLinkedListNode<T> head;
    private IDoubleLinkedListNode<T> tail;
    private int size;
    private int modCount;

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

        IDoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element);

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

        IDoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element);

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

    public void addAll(T[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        for (int i = 0; i < elements.length; i++) {
            T element = elements[i];
            if (element == null) {
                throw new IllegalArgumentException("Null elements are not allowed in the list. Null found at index " + i + ".");
            }
            addLast(element);
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from an empty list.");
        }

        T value = head.getValue();

        if (head == tail) {
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

        if (head == tail) {
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
        IDoubleLinkedListNode<T> current = getNodeAt(index);
        return current.getValue();
    }

    @Override
    public void set(int index, T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null values are not allowed in the list.");
        }
        checkIndex(index);
        IDoubleLinkedListNode<T> current = getNodeAt(index);
        current.setValue(element);
        modCount++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        IDoubleLinkedListNode<T> toRemove = getNodeAt(index);
        T removedValue = toRemove.getValue();

        if (toRemove == head && toRemove == tail) {
            head = null;
            tail = null;
        } else if (toRemove == head) {
            head = head.getNext();
            head.setPrev(null);
        } else if (toRemove == tail) {
            tail = tail.getPrev();
            tail.setNext(null);
        } else {
            IDoubleLinkedListNode<T> prevNode = toRemove.getPrev();
            IDoubleLinkedListNode<T> nextNode = toRemove.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }

        size--;
        modCount++;
        return removedValue;
    }

    @Override
    public boolean remove(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null elements are not allowed in the list.");
        }
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null elements are not allowed in the list.");
        }
        return indexOf(element) != -1;
    }

    @Override
    public int indexOf(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null elements are not allowed in the list.");
        }
        int index = 0;
        for (IDoubleLinkedListNode<T> current = head; current != null; current = current.getNext()) {
            if (element.equals(current.getValue())) {
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
        IDoubleLinkedListNode<T> current = head;
        while (current != null) {
            IDoubleLinkedListNode<T> next = current.getNext();
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
        return new DoubleLinkedListIterator<>(this, head);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        IDoubleLinkedListNode<T> current = head;
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

    private IDoubleLinkedListNode<T> getNodeAt(int index) {
        if (index < size / 2) {
            IDoubleLinkedListNode<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current;
        } else {
            IDoubleLinkedListNode<T> current = tail;
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

    protected int getModCount() {

        return modCount;
    }
}
