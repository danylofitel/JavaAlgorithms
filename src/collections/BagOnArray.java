package collections;

import java.util.Iterator;

public class BagOnArray<Item> implements Bag<Item> {
    
    private final Deque<Item> deque;
    
    public BagOnArray() {
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
    public void add(Item item) {
        deque.addLast(item);
    }
    
    @Override
    public Iterator<Item> iterator() {
        return deque.iterator();
    }
}
