package collections;

import java.util.Iterator;

public class QueueOnArray<Item> implements Queue<Item> {

    private final Deque<Item> deque;

    public QueueOnArray() {
        deque = new DequeOnArray<>();
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
    public Item front() {
        return deque.getFirst();
    }

    @Override
    public Item back() {
        return deque.getLast();
    }

    @Override
    public void pushBack(Item item) {
        deque.addLast(item);
    }

    @Override
    public Item popFront() {
        return deque.removeFirst();
    }

    @Override
    public Iterator<Item> iterator() {
        return deque.iterator();
    }
}
