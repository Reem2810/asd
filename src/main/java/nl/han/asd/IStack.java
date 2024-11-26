package nl.han.asd;

public interface IStack <T>{

    void push(T value);
    T pop();
    T peek();

    T top();

    boolean isEmpty();

    int size();
    void pushAll(T[] newElements);
}
