package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TableOnArray<Key extends Comparable<Key>, Value> implements Table<Key, Value> {

    // Initial capacity of container
    private final static int initialCapacity = 50;
    // Array of keys
    private Key[] keys;
    // Array of corresponding values
    private Value[] values;
    // Number of actual items in collection
    private int size;

    public TableOnArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Invalid capacity value " + capacity);
        }

        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
        size = 0;
    }

    public TableOnArray() {
        this(initialCapacity);
    }

    @Override
    public void put(Key key, Value val) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        } else if (val == null) {
            throw new NullPointerException("Value cannot be null");
        }

        // Make sure there's enough space for adding a new item
        if (size == keys.length) {
            resize(keys.length * 2);
        }

        // Find the right position
        int index = rank(key);

        if (keys[index] != null && keys[index].compareTo(key) == 0) {
            // The key already exists
            values[index] = val;
        } else {
            // Move all items on the right to the right
            for (int i = size; i > index; --i) {
                keys[i] = keys[i - 1];
                values[i] = values[i - 1];
            }

            // Save the new key-value pair
            keys[index] = key;
            values[index] = val;
            ++size;
        }
    }

    @Override
    public Value get(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        // Find index of key in array
        int index = indexOf(key);

        if (index >= 0) {
            // Return the corresponding value if such key exists
            return values[index];
        } else {
            // Return null if the key does not exist
            return null;
        }
    }

    @Override
    public boolean delete(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        // Find index of key in array
        int index = indexOf(key);

        // If the key exists
        if (index >= 0) {
            // Delete the key
            deleteKey(index);

            // Shrink the container if necessary
            if (size * 4 < keys.length && initialCapacity * 2 <= keys.length) {
                resize(keys.length / 2);
            }

            // The key has been deleted
            return true;
        } else {
            // The key has not been found
            return false;
        }
    }

    @Override
    public boolean contains(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        return indexOf(key) >= 0;
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

        // Find indexes of floor and ceiling
        int loIndex = ceilingIndex(lo);
        int hiIndex = floorIndex(hi);

        if (loIndex < 0 && hiIndex < 0) {
            // Existing elements and search area do not intersect
            return 0;
        } else {

            // The ceiliing is smaller than any existing key
            if (loIndex < 0) {
                //  Minimal key is the beginning of the intersection
                loIndex = 0;
            }

            // The flooor is larger than any existing key
            if (hiIndex < 0) {
                // Maximal key is the ending of the intersection
                hiIndex = size - 1;
            }

            // The number of items in intersection is the result
            return hiIndex - loIndex + 1;
        }
    }

    @Override
    public Key select(int k) {
        if (k < 0 || k >= size) {
            throw new IndexOutOfBoundsException();
        }

        return keys[k];
    }

    @Override
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return keys[0];
    }

    @Override
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return keys[size - 1];
    }

    @Override
    public Key floor(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        } else if (isEmpty()) {
            throw new NoSuchElementException();
        }

        // The index of the floor item
        int index = floorIndex(key);

        return index >= 0 ? keys[index] : null;
    }

    @Override
    public Key ceiling(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        } else if (isEmpty()) {
            throw new NoSuchElementException();
        }

        // The index of the ceiling item
        int index = ceilingIndex(key);

        return index >= 0 ? keys[index] : null;
    }

    @Override
    public int rank(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        // First and last index of the search area
        int lo = 0;
        int hi = size - 1;

        // Binary search for the index of key in container
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else if (cmp == 0) {
                return mid;
            }
        }

        return lo;
    }

    @Override
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        deleteKey(0);
    }

    @Override
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        deleteKey(size - 1);
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

    private int indexOf(Key key) {
        // The number of keys that are less than key
        int rank = rank(key);

        // Check if the key exists in container
        if (rank < size && keys[rank].compareTo(key) == 0) {
            // Return the key index in container if it exists
            return rank;
        } else {
            // The symbol table does not contain key
            return -1;
        }
    }

    private int floorIndex(Key key) {
        int index = rank(key);

        return (index < size && keys[index].compareTo(key) == 0) ? index : index - 1;
    }

    private int ceilingIndex(Key key) {
        int index = rank(key);

        return (index < size) ? index : -1;
    }

    private void deleteKey(int index) {
        // Move all items after the one being deleted to the left
        for (int i = index + 1; i < size; ++i) {
            keys[i - 1] = keys[i];
            values[i - 1] = values[i];
        }

        // Clear the last item
        --size;
        keys[size] = null;
        values[size] = null;
    }

    private void resize(int length) {
        Key[] keysCopy = (Key[]) new Comparable[length];
        Value[] valuesCopy = (Value[]) new Object[length];

        // Copy all items to the new basic container
        for (int i = 0; i < size; ++i) {
            // Copy keys and values
            keysCopy[i] = keys[i];
            valuesCopy[i] = values[i];

            // Clear the old values
            keys[i] = null;
            values[i] = null;
        }

        // Replace the basic container
        keys = keysCopy;
        values = valuesCopy;
    }

    private class KeyIterator implements Iterator<Key>, Iterable<Key> {

        // Key bounds
        private Key lo;
        private Key hi;
        // The last returned key
        private Key lastKey;
        // Index of the next key
        private int next;

        public KeyIterator(Key lo, Key hi) {
            this.lo = lo;
            this.hi = hi;
            this.lastKey = null;
            this.next = 0;
            findNext();
        }

        public KeyIterator() {
            this(null, null);
        }

        @Override
        public boolean hasNext() {
            return next < size;
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            // The next key will be the last returned
            lastKey = keys[next];

            // Move to the next node
            ++next;
            findNext();

            return lastKey;
        }

        @Override
        public void remove() {
            if (lastKey == null) {
                throw new NoSuchElementException();
            }

            if (contains(lastKey)) {
                delete(lastKey);
                lastKey = null;
                --next;
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
                while (next < size
                        && (keys[next].compareTo(lo) < 0 || keys[next].compareTo(hi) > 0)) {
                    ++next;
                }
            }
        }
    }

    private class ValueIterator implements Iterator<Value>, Iterable<Value> {

        // Index of the next key
        private int next;

        public ValueIterator() {
            next = 0;
        }

        @Override
        public boolean hasNext() {
            return next < size;
        }

        @Override
        public Value next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return values[next++];
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
