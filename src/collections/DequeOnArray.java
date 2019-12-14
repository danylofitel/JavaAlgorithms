package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeOnArray<Item> implements Deque<Item> {

    private final static int initialCapacity = 50;
    private LimitedDeque<Item> deque;

    public DequeOnArray() {
        deque = new LimitedDeque<>(initialCapacity);
    }

    @Override
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    @Override
    public int size() {
        return deque.size();
    }

    @Override
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Null element can't be added to the deque");
        }

        enlarge();

        deque.addFirst(item);
    }

    @Override
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Null element can't be added to the deque");
        }

        enlarge();

        deque.addLast(item);
    }

    @Override
    public Item getFirst() {
        if (deque.isEmpty()) {
            throw new NoSuchElementException();
        }

        return deque.getFirst();
    }

    @Override
    public Item getLast() {
        if (deque.isEmpty()) {
            throw new NoSuchElementException();
        }

        return deque.getLast();
    }

    @Override
    public Item removeFirst() {
        if (deque.isEmpty()) {
            throw new NoSuchElementException();
        }

        Item result = deque.removeFirst();

        shrink();

        return result;
    }

    @Override
    public Item removeLast() {
        if (deque.isEmpty()) {
            throw new NoSuchElementException();
        }

        Item result = deque.removeLast();

        shrink();

        return result;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeOnArrayIterator();
    }

    private class DequeOnArrayIterator implements Iterator<Item> {

        private final Iterator<Item> arrayIterator;

        public DequeOnArrayIterator() {
            arrayIterator = deque.iterator();
        }

        @Override
        public boolean hasNext() {
            return arrayIterator.hasNext();
        }

        @Override
        public Item next() {
            return arrayIterator.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private void enlarge() {
        if (deque.isFull()) {
            LimitedDeque<Item> newArray = new LimitedDeque<>(deque.capacity() * 2);

            while (!deque.isEmpty()) {
                newArray.addLast(deque.removeFirst());
            }

            deque = newArray;
        }
    }

    private void shrink() {
        if (deque.capacity() >= deque.size() * 4 && deque.capacity() >= initialCapacity * 2) {

            LimitedDeque<Item> newArray = new LimitedDeque<>(deque.capacity() / 2);

            while (!deque.isEmpty()) {
                newArray.addLast(deque.removeFirst());
            }

            deque = newArray;
        }
    }
}
