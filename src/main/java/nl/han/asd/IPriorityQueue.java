package nl.han.asd;

public interface IPriorityQueue<T> {
    void add(T element);
    T peek();
    T poll();

    void addAll(T[] elements);
}
