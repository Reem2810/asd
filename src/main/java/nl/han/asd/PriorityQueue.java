package nl.han.asd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PriorityQueue<T extends Comparable<T>> implements IPriorityQueue<T>, Iterable<T> {
    private List<T> list;

    public PriorityQueue() {

        list = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        if (element == null) {
            throw new NullPointerException("Null elements are not allowed in the PriorityQueue.");
        }
        list.add(element);
        heapifyUp(list.size() - 1);
    }

    @Override
    public T peek() {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public T poll() {
        if (list.isEmpty()) {
            return null;
        }
        T root = list.get(0);
        T last = list.remove(list.size() - 1);
        if (!list.isEmpty()) {
            list.set(0, last);
            heapifyDown(0);
        }
        return root;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIdx = (index - 1) / 2;
            T current = list.get(index);
            T parent = list.get(parentIdx);
            if (current.compareTo(parent) < 0) {
                list.set(index, parent);
                list.set(parentIdx, current);
                index = parentIdx;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        int size = list.size();
        while (index < size) {
            int leftIdx = 2 * index + 1;
            int rightIdx = 2 * index + 2;
            int smallestIdx = index;

            if (leftIdx < size && list.get(leftIdx).compareTo(list.get(smallestIdx)) < 0) {
                smallestIdx = leftIdx;
            }

            if (rightIdx < size && list.get(rightIdx).compareTo(list.get(smallestIdx)) < 0) {
                smallestIdx = rightIdx;
            }

            if (smallestIdx != index) {
                T temp = list.get(index);
                list.set(index, list.get(smallestIdx));
                list.set(smallestIdx, temp);
                index = smallestIdx;
            } else {
                break;
            }
        }
    }

    public int size() {

        return list.size();
    }

    public boolean isEmpty() {

        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {

        return "PriorityQueue: " + list.toString();
    }
}
