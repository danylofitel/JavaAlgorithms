package collections;

import java.util.Iterator;

public class StackOnArray<Item> implements Stack<Item> {

    private final Deque<Item> deque;

    public StackOnArray() {
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
    public void push(Item item) {
        deque.addLast(item);
    }

    @Override
    public Item top() {
        return deque.getLast();
    }

    @Override
    public Item pop() {
        return deque.removeLast();
    }

    @Override
    public Iterator<Item> iterator() {
        return deque.iterator();
    }
}
