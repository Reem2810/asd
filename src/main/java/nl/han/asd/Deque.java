package nl.han.asd;


public class Deque<T> {
    private DoubleLinkedList<T> list;


    public Deque() {
        this.list = new DoubleLinkedList<>();
    }


    public void InsertLeft(T element) {
        list.addFirst(element);
    }

    public void InsertRight(T element) {
        list.addLast(element);
    }

    public T DeleteLeft() {
        return list.removeFirst();
    }

    public T DeleteRight() {
        return list.removeLast();
    }

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
