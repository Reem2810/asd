
package nl.han.asd;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StackOfArrayList<T> implements IStack<T> {
    private List<T> list;


    public StackOfArrayList() {
        this.list = new ArrayList<>();
    }

    public StackOfArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative.");
        }
        this.list = new ArrayList<>(initialCapacity);
    }

    @Override
    public void push(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Null values are not allowed in the stack.");
        }
        list.add(value);
    }


    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot pop from an empty stack.");
        }
        return list.remove(list.size() - 1);
    }


    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot peek on an empty stack.");
        }
        return list.get(list.size() - 1);
    }

    @Override
    public T top() {
        return peek();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }


    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
