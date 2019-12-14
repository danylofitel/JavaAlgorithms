package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LimitedDeque<Item> implements Deque<Item> {

    private final Item[] array;
    private int size;
    private int head;
    private int tail;

    public LimitedDeque(int size) {
        this.array = (Item[]) new Object[size];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public int capacity() {
        return array.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Null element can't be added to the container.");
        } else if (size == array.length) {
            throw new IndexOutOfBoundsException("Array is full.");
        }

        head = previousIndex(head);
        array[head] = item;
        ++size;
    }

    @Override
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Null element can't be added to the container.");
        } else if (size == array.length) {
            throw new IndexOutOfBoundsException("Array is full.");
        }

        array[tail] = item;
        tail = nextIndex(tail);
        ++size;
    }

    @Override
    public Item getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return array[head];
    }

    @Override
    public Item getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return array[previousIndex(tail)];
    }

    @Override
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item result = array[head];
        array[head] = null;
        head = nextIndex(head);
        --size;

        return result;
    }

    @Override
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        tail = previousIndex(tail);
        Item result = array[tail];
        array[tail] = null;
        --size;

        return result;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LimitedDequeIterator();
    }

    private class LimitedDequeIterator implements Iterator<Item> {

        private int current;
        private boolean initial;

        public LimitedDequeIterator() {
            current = head;
            initial = true;
        }

        @Override
        public boolean hasNext() {
            return (current != tail) || (initial && size == array.length);
        }

        @Override
        public Item next() {
            initial = false;
            Item result = array[current];
            current = nextIndex(current);
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private int nextIndex(int index) {
        if (index < array.length - 1) {
            return index + 1;
        } else {
            return 0;
        }
    }

    private int previousIndex(int index) {
        if (index > 0) {
            return index - 1;
        } else {
            return array.length - 1;
        }
    }
}
