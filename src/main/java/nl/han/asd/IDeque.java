// IDeque.java
package nl.han.asd;

import java.util.Iterator;

public interface IDeque<T> extends Iterable<T> {
    void InsertLeft(T element);
    void InsertRight(T element);
    T DeleteLeft();
    T DeleteRight();

    void InsertAllRight(T[] values);

    int size();
    boolean isEmpty();
    Iterator<T> iterator();
}
