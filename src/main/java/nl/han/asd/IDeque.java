// IDeque.java
package nl.han.asd;

public interface IDeque<T> {
    void InsertLeft(T element);
    void InsertRight(T element);
    T DeleteLeft();
    T DeleteRight();
    int size();
    boolean isEmpty();
}
