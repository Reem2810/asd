// IDynamicArray.java
package nl.han.asd;

import java.util.Iterator;

public interface IDynamicArray<E> extends Iterable<E> {
    void add(E element);
    void addAll(E[] newElements);
    E get(int index);
    void set(int index, E element);
    E remove(int index);
    boolean remove(E element);
    boolean contains(E element);
    int indexOf(E element);
    int size();
    boolean isEmpty();
    void clear();
    Iterator<E> iterator(); // Optional, as Iterable already includes this
}
