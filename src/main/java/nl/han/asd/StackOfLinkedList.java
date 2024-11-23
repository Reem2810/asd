package nl.han.asd;

public class StackOfLinkedList<T> implements IStack<T> {

    ILinkedList<T> list;

    public StackOfLinkedList() {

        list = new LinkedList<>();
    }

    @Override
    public void push(T value) {

        list.addFirst(value);
    }

    @Override
    public T pop() {
        T tmp = list.getFirst();
        list.delete(0);
        return tmp;
    }

    @Override
    public T peek() {

        return list.getFirst();
    }

    @Override
    public T top() {
        return peek();
    }

    @Override
    public boolean isEmpty() {

        return list.getSize() == 0;
    }
    @Override
    public int size() {
        return list.getSize();
    }
}