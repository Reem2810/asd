// IDeque.java
package nl.han.asd;

import java.util.Iterator;

public interface IDeque<T> extends Iterable<T> {
    void InsertLeft(T element);
    void InsertRight(T element);
    T DeleteLeft();
    T DeleteRight();
    int size();
    boolean isEmpty();
    Iterator<T> iterator();
}
