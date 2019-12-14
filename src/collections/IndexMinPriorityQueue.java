package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMinPriorityQueue<Key extends Comparable<Key>> implements Iterable<Integer> {

    private int NMAX;        // Maximum number of elements on PQ
    private int N;           // Number of elements on PQ
    private int[] pq;        // Binary heap using 1-based indexing
    private int[] qp;        // Inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;      // keys[i] = priority of i

    public IndexMinPriorityQueue(int NMAX) {
        if (NMAX < 0) {
            throw new IllegalArgumentException();
        }
        this.NMAX = NMAX;
        keys = (Key[]) new Comparable[NMAX + 1];
        pq = new int[NMAX + 1];
        qp = new int[NMAX + 1];
        for (int i = 0; i <= NMAX; i++) {
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(int i) {
        if (i < 0 || i >= NMAX) {
            throw new IndexOutOfBoundsException();
        }
        return qp[i] != -1;
    }

    public int size() {
        return N;
    }

    public void insert(int i, Key key) {
        if (i < 0 || i >= NMAX) {
            throw new IndexOutOfBoundsException();
        }
        if (contains(i)) {
            throw new IllegalArgumentException("index is already in the priority queue");
        }
        N++;
        qp[i] = N;
        pq[N] = i;
        keys[i] = key;
        swim(N);
    }

    public int minIndex() {
        if (N == 0) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return pq[1];
    }

    public Key minKey() {
        if (N == 0) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return keys[pq[1]];
    }

    public int delMin() {
        if (N == 0) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        int min = pq[1];
        exch(1, N--);
        sink(1);
        qp[min] = -1;
        keys[pq[N + 1]] = null;
        pq[N + 1] = -1;
        return min;
    }

    public Key keyOf(int i) {
        if (i < 0 || i >= NMAX) {
            throw new IndexOutOfBoundsException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        } else {
            return keys[i];
        }
    }

    public void changeKey(int i, Key key) {
        if (i < 0 || i >= NMAX) {
            throw new IndexOutOfBoundsException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    public void decreaseKey(int i, Key key) {
        if (i < 0 || i >= NMAX) {
            throw new IndexOutOfBoundsException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        if (keys[i].compareTo(key) <= 0) {
            throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
        }
        keys[i] = key;
        swim(qp[i]);
    }

    public void increaseKey(int i, Key key) {
        if (i < 0 || i >= NMAX) {
            throw new IndexOutOfBoundsException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        if (keys[i].compareTo(key) >= 0) {
            throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
        }
        keys[i] = key;
        sink(qp[i]);
    }

    public void delete(int i) {
        if (i < 0 || i >= NMAX) {
            throw new IndexOutOfBoundsException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        int index = qp[i];
        exch(index, N--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {

        private final IndexMinPriorityQueue<Key> copy;

        public HeapIterator() {
            copy = new IndexMinPriorityQueue<>(pq.length - 1);
            for (int i = 1; i <= N; i++) {
                copy.insert(pq[i], keys[pq[i]]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMin();
        }
    }
}
