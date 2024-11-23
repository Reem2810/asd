package nl.han.asd;


public class Deque<T> implements IDeque<T>{
    private DoubleLinkedList<T> list;


    public Deque() {

        this.list = new DoubleLinkedList<>();
    }

@Override
    public void InsertLeft(T element) {

        list.addFirst(element);
    }

    @Override
    public void InsertRight(T element) {

        list.addLast(element);
    }
@Override
    public T DeleteLeft() {

        return list.removeFirst();
    }
@Override
    public T DeleteRight() {

        return list.removeLast();
    }
@Override
    public int size() {

        return list.size();
    }
@Override
    public boolean isEmpty() {

        return list.isEmpty();
    }

    @Override
    public String toString() {

        return list.toString();
    }
}
