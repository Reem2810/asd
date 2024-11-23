package nl.han.asd;

import java.util.ArrayList;
import java.util.List;


public class PriorityQueue<T extends Comparable<T>> implements IPriorityQueue<T> {
    private List<T> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    @Override
    public T peek() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    @Override
    public T poll() {
        if (heap.isEmpty()) {
            return null;
        }
        T root = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return root;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIdx = (index - 1) / 2;
            T current = heap.get(index);
            T parent = heap.get(parentIdx);
            if (current.compareTo(parent) < 0) { // Min-Heap
                heap.set(index, parent);
                heap.set(parentIdx, current);
                index = parentIdx;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();
        while (index < size) {
            int leftIdx = 2 * index + 1;
            int rightIdx = 2 * index + 2;
            int smallestIdx = index;

            if (leftIdx < size && heap.get(leftIdx).compareTo(heap.get(smallestIdx)) < 0) {
                smallestIdx = leftIdx;
            }

            if (rightIdx < size && heap.get(rightIdx).compareTo(heap.get(smallestIdx)) < 0) {
                smallestIdx = rightIdx;
            }

            if (smallestIdx != index) {
                T temp = heap.get(index);
                heap.set(index, heap.get(smallestIdx));
                heap.set(smallestIdx, temp);
                index = smallestIdx;
            } else {
                break;
            }
        }
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
