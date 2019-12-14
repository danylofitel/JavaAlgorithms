package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPriorityQueue<Item extends Comparable<Item>> implements Queue<Item> {

    private static final int initialCapacity = 50;
    private Item[] pq;
    private int size;

    public MaxPriorityQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }

        pq = (Item[]) new Comparable[capacity + 1];
        size = 0;
    }

    public MaxPriorityQueue() {
        this(initialCapacity);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Item front() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return pq[1];
    }

    @Override
    public Item back() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return pq[size];
    }

    @Override
    public void pushBack(Item item) {
        enlarge();
        pq[++size] = item;
        swim(size);
    }

    @Override
    public Item popFront() {
        Item max = pq[1];
        exch(1, size--);
        sink(1);
        pq[size + 1] = null;
        shrink();
        return max;
    }

    @Override
    public Iterator<Item> iterator() {
        return new MinPriorityQueueIterator();
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Item t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void enlarge() {
        if (size == pq.length - 1) {
            Item[] largerPQ = (Item[]) new Comparable[2 * pq.length];
            for (int i = 1; i <= size; ++i) {
                largerPQ[i] = pq[i];
                pq[i] = null;
            }
            pq = largerPQ;
        }
    }

    private void shrink() {
        if (size * 4 < pq.length && pq.length >= initialCapacity * 2) {
            Item[] smallerPQ = (Item[]) new Comparable[pq.length / 2];
            for (int i = 1; i <= size; ++i) {
                smallerPQ[i] = pq[i];
                pq[i] = null;
            }
            pq = smallerPQ;
        }
    }

    private class MinPriorityQueueIterator implements Iterator<Item> {

        private int current;

        public MinPriorityQueueIterator() {
            current = 1;
        }

        @Override
        public boolean hasNext() {
            return current <= size;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                return pq[current++];
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
