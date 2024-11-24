// ISingleLinkedList.java
package nl.han.asd;

import java.util.Iterator;

public interface ISingleLinkedList<T> extends Iterable<T> {
    void addFirst(T value);
    void addLast(T value);
    void clear();
    void insert(int index, T value);
    void delete(int pos);
    T get(int pos);
    void removeFirst();
    T getFirst();
    int getSize();
}
