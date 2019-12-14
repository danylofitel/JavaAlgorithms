package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import lib.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // Initial size of array
    private static final int initialCapacity = 50;
    // Base container
    private Item[] array;
    // Actual number of elements in the queue
    private int size;
    // Index of the first element
    private int head;
    // Index after the last element
    private int tail;

    public RandomizedQueue() {
        array = (Item[]) new Object[initialCapacity];
        size = 0;
        head = 0;
        tail = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Null element can't be added to the queue.");
        }

        enlarge();

        array[tail] = item;
        tail = nextIndex(tail);
        ++size;
    }

    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        int randomIndex = randomIndex();
        Item result;

        if (randomIndex == head) {
            if (size > 1) {
                head = nextItemIndex(head);
            }
        }

        result = array[randomIndex];
        array[randomIndex] = null;
        --size;

        if (size == 0) {
            head = 0;
            tail = 0;
        }

        shrink();

        return result;
    }

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return array[randomIndex()];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int current;
        private boolean initial;

        public RandomizedQueueIterator() {
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
            do {
                current = nextIndex(current);
            } while (array[current] == null && current != tail);

            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private void enlarge() {
        if (head == tail && size > 0) {
            Item[] newArray = (Item[]) new Object[size * 2 > array.length ? array.length * 2 : array.length];

            int current = head;
            int i = 0;
            do {
                newArray[i++] = array[current];
                array[current] = null;
                do {
                    current = RandomizedQueue.this.nextIndex(current);
                } while (RandomizedQueue.this.array[current] == null && current != tail);
            } while (current != tail);

            array = newArray;
            head = 0;
            tail = size;
        }
    }

    private void shrink() {
        if (array.length > size * 4 && array.length >= initialCapacity * 2) {
            Item[] newArray = (Item[]) new Object[array.length / 2];

            int current = head;
            for (int i = 0; i < size; ++i) {
                newArray[i] = array[current];
                current = nextItemIndex(current);
            }

            array = newArray;
            head = 0;
            tail = size;
        }
    }

    private int nextIndex(int index) {
        if (index < array.length - 1) {
            return index + 1;
        } else {
            return 0;
        }
    }

    private int nextItemIndex(int index) {
        int nextIndex = index;

        do {
            nextIndex = nextIndex(nextIndex);
        } while (array[nextIndex] == null);

        return nextIndex;
    }

    private int randomIndex() {
        int randomIndex = 0;
        int interval;

        if (head < tail) {
            interval = tail - head;
        } else {
            interval = array.length - head + tail;
        }

        do {
            randomIndex = StdRandom.uniform(interval);
            randomIndex = (head + randomIndex) % array.length;
        } while (array[randomIndex] == null);

        return randomIndex;
    }
}
