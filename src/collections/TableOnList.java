package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TableOnList<Key extends Comparable<Key>, Value> implements Table<Key, Value> {

    private Node first = null;
    private int size = 0;

    @Override
    public void put(Key key, Value val) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        } else if (val == null) {
            throw new NullPointerException("Value cannot be null");
        }

        if (isEmpty()) {
            // A pointer to the first element has to be set
            first = new Node();
            first.key = key;
            first.value = val;
            first.next = null;
            ++size;
            return;
        }

        Node temp = first;

        // Iterate through existing keys
        while (temp != null) {
            if (temp.key.equals(key)) {
                // If the key exists, only update the value
                temp.value = val;
                return;
            }
            temp = temp.next;
        }

        // A new node has to be added to the beginning of the list
        Node oldFirst = first;
        first = new Node();
        first.key = key;
        first.value = val;
        first.next = oldFirst;
        ++size;
    }

    @Override
    public Value get(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        Node temp = first;

        // Iterate through all elements
        while (temp != null) {
            if (temp.key.equals(key)) {
                // Return value if the key exists
                return temp.value;
            }

            temp = temp.next;
        }

        // Return null if the key does not exist
        return null;
    }

    @Override
    public boolean delete(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        Node previous = null;
        Node current = first;

        // Iterate through all elements
        while (current != null) {
            if (current.key.equals(key)) {
                // If suck key exists
                if (previous != null) {
                    // The element has to be removed from the list
                    previous.next = current.next;
                } else {
                    // The first element needs to be deleted
                    // The pointer to the first element needs to be changed
                    first = first.next;
                }

                current.key = null;
                current.value = null;
                current.next = null;
                --size;

                // The key has been deleted
                return true;
            }

            previous = current;
            current = current.next;
        }

        // The key has not been found
        return false;
    }

    @Override
    public boolean contains(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        return get(key) != null;
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
    public int size(Key lo, Key hi) {
        if (lo == null || hi == null || lo.compareTo(hi) > 0) {
            throw new IllegalArgumentException();
        }

        int count = 0;

        Node temp = first;

        // Iterate through all elements
        while (temp != null) {
            // Check if current key is between lower and higher bounds
            if (lo.compareTo(temp.key) <= 0 && temp.key.compareTo(hi) <= 0) {
                ++count;
            }

            temp = temp.next;
        }

        return count;
    }

    @Override
    public Key select(int k) {
        if (k < 0 || k >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node temp = first;

        // Skip elements until key #k is found
        while (rank(temp.key) != k) {
            temp = temp.next;
        }

        return temp.key;
    }

    @Override
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Key min = first.key;

        Node temp = first.next;

        // Iterate through all remaining elements
        while (temp != null) {
            if (temp.key.compareTo(min) < 0) {
                // Set max value to current key if it is smaller than its beforeCurrent value
                min = temp.key;
            }

            temp = temp.next;
        }

        return min;
    }

    @Override
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Key max = first.key;

        Node temp = first.next;

        // Iterate through all remaining elements
        while (temp != null) {
            if (temp.key.compareTo(max) > 0) {
                // Set max value to current key if it is bigger than its beforeCurrent value
                max = temp.key;
            }

            temp = temp.next;
        }

        return max;
    }

    @Override
    public Key floor(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        } else if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Key floor = first.key.compareTo(key) <= 0 ? first.key : null;

        Node temp = first.next;

        // Iterate through all remaining elements
        while (temp != null) {
            // If current key is less or equal than the key
            if (temp.key.compareTo(key) <= 0) {
                // If current key is larger than the ceiling
                if (floor == null || floor.compareTo(temp.key) < 0) {
                    // Set the ceiling value to current key
                    floor = temp.key;
                }
            }

            temp = temp.next;
        }

        return floor;
    }

    @Override
    public Key ceiling(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        } else if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Key ceiling = first.key.compareTo(key) >= 0 ? first.key : null;

        Node temp = first.next;

        // Iterate through all remaining elements
        while (temp != null) {
            // If current key is less or equal than the key
            if (temp.key.compareTo(key) >= 0) {
                // If current key is smaller than the ceiling
                if (ceiling == null || ceiling.compareTo(temp.key) > 0) {
                    // Set the ceiling value to current key
                    ceiling = temp.key;
                }
            }

            temp = temp.next;
        }

        return ceiling;
    }

    @Override
    public int rank(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        int rank = 0;

        Node temp = first;

        // Iterate through all elements
        while (temp != null) {
            if (temp.key.compareTo(key) < 0) {
                // Another key that fits conditions has been found
                ++rank;
            }

            temp = temp.next;
        }

        return rank;
    }

    @Override
    public void deleteMin() {
        delete(min());
    }

    @Override
    public void deleteMax() {
        delete(max());
    }

    @Override
    public Iterable<Key> keys() {
        return new KeyIterator();
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null || hi == null || lo.compareTo(hi) > 0) {
            throw new IllegalArgumentException();
        }

        return new KeyIterator(lo, hi);
    }

    @Override
    public Iterable<Value> values() {
        return new ValueIterator();
    }

    private class Node {

        Key key;
        Value value;
        Node next;
    }

    private class KeyIterator implements Iterator<Key>, Iterable<Key> {

        // Key bounds
        private Key lo;
        private Key hi;
        // The last returned key
        private Key lastKey;
        // Next node
        private Node next;

        public KeyIterator(Key lo, Key hi) {
            this.lo = lo;
            this.hi = hi;
            this.lastKey = null;
            this.next = first;
            findNext();
        }

        public KeyIterator() {
            this(null, null);
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            // The next key will be the last returned
            lastKey = next.key;

            // Move to the next node
            next = next.next;
            findNext();

            return lastKey;
        }

        @Override
        public void remove() {
            if (lastKey != null) {
                delete(lastKey);
                lastKey = null;
            }
        }

        @Override
        public Iterator<Key> iterator() {
            return this;
        }

        // Move to the next key that is between lo and hi
        private void findNext() {
            if (lo != null && hi != null) {
                // Skip all irrelevant keys
                while (next != null
                        && (next.key.compareTo(lo) < 0 || next.key.compareTo(hi) > 0)) {
                    next = next.next;
                }
            }
        }
    }

    private class ValueIterator implements Iterator<Value>, Iterable<Value> {

        // Next node
        private Node next;

        private ValueIterator() {
            next = first;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Value next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            // Get the next value
            Value value = next.value;

            // Move to the next node
            next = next.next;

            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Iterator<Value> iterator() {
            return this;
        }
    }
}
