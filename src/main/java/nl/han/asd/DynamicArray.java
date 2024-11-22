
package nl.han.asd;
import java.util.Arrays;
import java.util.Iterator;

public class DynamicArray<E> implements Iterable<E> {
    private E[] elements;
    private int size;
    private int capacity;
    private int modCount = 0;

    public DynamicArray(){
        this(1);
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


    public void add(E element) {
        ensureCapacity();
        elements[size++] = element;
        modCount++;
    }

    public void addAll(E[] newElements) {
        for (E element : newElements) {
            add(element);
        }
    }

    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    public void set(int index, E element) {
        checkIndex(index);
        elements[index] = element;
    }

    public E remove(int index) {
        checkIndex(index);
        E removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        modCount++;
        return removedElement;
    }

    public boolean remove(E element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    public boolean contains(E element) {

        return indexOf(element) != -1;
    }

    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (element == null && elements[i] == null || element != null && element.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            elements = Arrays.copyOf(elements, capacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
        modCount++;
    }


    public void trimToSize() {
        // Only trim if the size is less than half the capacity
        if (size < capacity / 2) {
            // Compute the new capacity as size + 25% of size (rounded up)
            int newCapacity = size + (int) Math.ceil(size * 0.25);
            // Adjust the internal array to the new capacity
            elements = Arrays.copyOf(elements, newCapacity);
            // Update the capacity field
            capacity = newCapacity;
        }
    }



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
                    throw new java.util.NoSuchElementException();
                }
                return elements[currentIndex++];
            }

            private void checkForModification() {
                if (modCount != expectedModCount) {
                    throw new java.util.ConcurrentModificationException();
                }
            }
        };
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size));
    }
}