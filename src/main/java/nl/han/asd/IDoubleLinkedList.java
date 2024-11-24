// IDoubleLinkedList.java
package nl.han.asd;

import java.util.Iterator;

public interface IDoubleLinkedList<T> extends Iterable<T> {
    void add(T element);
    void addFirst(T element);
    void addLast(T element);
    T removeFirst();
    T removeLast();
    T get(int index);
    void set(int index, T element);
    T remove(int index);
    boolean remove(T element);
    boolean contains(T element);
    int indexOf(T element);
    int size();
    boolean isEmpty();
    void clear();
    Iterator<T> iterator();
}
