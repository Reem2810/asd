package nl.han.asd;

import java.util.NoSuchElementException;

public class DynamicStack<T> implements IStack<T> {
    private DynamicArray<T> dynamicArray;

    public DynamicStack() {
        this.dynamicArray = new DynamicArray<>(10);
    }

    public DynamicStack(int initialCapacity) {
        this.dynamicArray = new DynamicArray<>(initialCapacity);
    }


    @Override
    public void push(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Null values are not allowed in the stack.");
        }
        dynamicArray.add(value);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot pop from an empty stack.");
        }
        // Retrieve the last element
        T value = dynamicArray.get(dynamicArray.size() - 1);
        // Remove the last element
        dynamicArray.remove(dynamicArray.size() - 1);
        return value;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot peek on an empty stack.");
        }
        return dynamicArray.get(dynamicArray.size() - 1);
    }

    @Override
    public T top() {
        return peek();
    }

    @Override
    public boolean isEmpty() {
        return dynamicArray.isEmpty();
    }

    @Override
    public int size() {
        return dynamicArray.size();
    }

    @Override
    public String toString() {
        return dynamicArray.toString();
    }
}
