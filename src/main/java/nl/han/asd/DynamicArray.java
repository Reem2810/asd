// DynamicArray.java
package nl.han.asd;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class DynamicArray<E> implements IDynamicArray<E> {
    private static final int DEFAULT_CAPACITY = 8;
    private E[] elements;
    private int size;
    private int capacity;
    private int modCount = 0; // For fail-fast iterators

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
        // Double capacity until it can accommodate all new elements
        while (size + numNew > capacity) {
            if (capacity > (Integer.MAX_VALUE / 2)) {
                throw new OutOfMemoryError("Cannot increase capacity beyond Integer.MAX_VALUE");
            }
            capacity *= 2;
        }
        // Resize the underlying array once
        elements = Arrays.copyOf(elements, capacity);
        modCount++;

        // Copy all new elements
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

    /**
     * Returns the index of the first occurrence of the specified element in the dynamic array.
     *
     * @param element the element to search for
     * @return the index of the element, or -1 if not found
     */
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

    /**
     * Returns the number of elements in the dynamic array.
     *
     * @return the size of the dynamic array
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks whether the dynamic array is empty.
     *
     * @return true if the dynamic array contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all elements from the dynamic array.
     */

    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
        modCount++;
    }

    /**
     * Trims the capacity of the dynamic array to be the current size.
     * Useful for optimizing memory usage after a large number of removals.
     */
    public void trimToSize() {
        if (size < capacity / 2) {
            int newCapacity = size + (int) Math.ceil(size * 0.25);
            elements = Arrays.copyOf(elements, newCapacity);
            capacity = newCapacity;
            modCount++;
        }
    }

    /**
     * Ensures that the underlying array has sufficient capacity to add new elements.
     * If the array is full, it resizes by doubling its current capacity.
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            elements = Arrays.copyOf(elements, capacity);
            modCount++;
        }
    }

    /**
     * Checks if the given index is within the bounds of the dynamic array.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }

    /**
     * Returns an iterator over the elements in the dynamic array in proper sequence.
     * The iterator is fail-fast, throwing a ConcurrentModificationException if the dynamic array is modified during iteration.
     *
     * @return an iterator over the elements in the dynamic array
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                checkForModification();
                return currentIndex < size;
            }

            @Override
            public E next() {
                checkForModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[currentIndex++];
            }

            private void checkForModification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    /**
     * Returns a string representation of the dynamic array.
     *
     * @return a string containing all elements in the dynamic array
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size));
    }
}
