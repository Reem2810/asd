package nl.han.asd;


public class DequeOfDoubleLinkedArray<T> implements IDeque<T>{
    private DoubleLinkedList<T> list;


    public DequeOfDoubleLinkedArray() {

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
    public boolean isEmpty() {

        return list.isEmpty();
    }

    @Override
    public String toString() {

        return list.toString();
    }
}
