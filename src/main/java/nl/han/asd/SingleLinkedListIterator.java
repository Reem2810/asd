package nl.han.asd;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedListIterator<T> implements Iterator<T> {
    private SingleLinkedListNode<T> current;

    public SingleLinkedListIterator(SingleLinkedListNode<T> first) {
        current = first;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        SingleLinkedListNode<T> tempo = current;
        current = current.getNext();
        return tempo.getValue();
    }
}