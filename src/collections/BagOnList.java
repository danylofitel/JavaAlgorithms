package collections;

import java.util.Iterator;

public class BagOnList<Item> implements Bag<Item> {

    private final Deque<Item> deque;

    public BagOnList() {
        deque = new DequeOnList<>();
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
    public void add(Item item) {
        deque.addLast(item);
    }

    @Override
    public Iterator<Item> iterator() {
        return deque.iterator();
    }
}
