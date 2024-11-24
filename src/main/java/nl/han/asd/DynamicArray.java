// DynamicArray.java
package nl.han.asd;

import java.util.Arrays;
import java.util.Iterator;

public class DynamicArray<E> implements IDynamicArray<E> {
    private static final int DEFAULT_CAPACITY = 8;
    private E[] elements;
    private int size;
    private int capacity;
    private int modCount = 0;

    @SuppressWarnings("unchecked")
    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public DynamicArray(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.capacity = initialCapacity;
        this.elements = (E[]) new Object[capacity];
        this.size = 0;
    }

    @Override
    public void add(E element) {
        ensureCapacity();
        elements[size++] = element;
        modCount++;
    }

    @Override
    public void addAll(E[] newElements) {
        if (newElements == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        int numNew = newElements.length;
        if (numNew == 0) {
            return; // Nothing to add
        }

        while (size + numNew > capacity) {
            if (capacity > (Integer.MAX_VALUE / 2)) {
                throw new OutOfMemoryError("Cannot increase capacity beyond Integer.MAX_VALUE");
            }
            capacity *= 2;
        }

        elements = Arrays.copyOf(elements, capacity);
        modCount++;

        System.arraycopy(newElements, 0, elements, size, numNew);
        size += numNew;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(int index, E element) {
        checkIndex(index);
        elements[index] = element;
        modCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removedElement = elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // Help GC
        modCount++;
        return removedElement;
    }

    @Override
    public boolean remove(E element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
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
        Arrays.fill(elements, 0, size, null);
        size = 0;
        modCount++;
    }

    public void trimToSize() {
        if (size < capacity / 2) {
            int newCapacity = size + (int) Math.ceil(size * 0.25);
            elements = Arrays.copyOf(elements, newCapacity);
            capacity = newCapacity;
            modCount++;
        }
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            elements = Arrays.copyOf(elements, capacity);
            modCount++;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new DynamicArrayIterator<>(elements, size, modCount);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size));
    }
}
