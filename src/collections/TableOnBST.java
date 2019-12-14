package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TableOnBST<Key extends Comparable<Key>, Value> implements Table<Key, Value> {

    // The root node
    private Node root;

    @Override
    public void put(Key key, Value val) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        } else if (val == null) {
            throw new NullPointerException("Value cannot be null");
        }

        root = put(root, key, val);
    }

    @Override
    public Value get(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        // Start with the root x
        Node x = root;

        // Continue search until no nodes remain
        while (x != null) {
            // Compare the key to the x key
            int cmp = key.compareTo(x.key);

            if (cmp < 0) {
                // Move to the left son
                x = x.left;
            } else if (cmp > 0) {
                // Move to the right son
                x = x.right;
            } else if (cmp == 0) {
                // The key has been found
                return x.val;
            }
        }

        // The key does not exist
        return null;
    }

    @Override
    public boolean delete(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        int oldSize = size();

        root = delete(root, key);

        return size() < oldSize;
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
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public int size(Key lo, Key hi) {
        if (lo == null || hi == null || lo.compareTo(hi) > 0) {
            throw new IllegalArgumentException();
        }

        Queue<Key> q = new QueueOnList<>();
        inOrder(root, q, lo, hi);
        return q.size();
    }

    @Override
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IndexOutOfBoundsException();
        }

        Stack<Key> s = new StackOnList<>();
        reverseOrder(root, s, k + 1);
        return s.top();
    }

    @Override
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return min(root).key;
    }

    @Override
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return max(root).key;
    }

    @Override
    public Key floor(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        } else if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    @Override
    public Key ceiling(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        } else if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node x = ceiling(root, key);
        return x == null ? null : x.key;
    }

    @Override
    public int rank(Key key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        return rank(key, root);
    }

    @Override
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        root = deleteMin(root);
    }

    @Override
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        root = deleteMax(root);
    }

    @Override
    public Iterable<Key> keys() {
        Queue<Key> q = new QueueOnList<>();
        inOrder(root, q);
        return new BinarySearchTreeKeys(q);
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null || hi == null || lo.compareTo(hi) > 0) {
            throw new IllegalArgumentException();
        }

        Queue<Key> q = new QueueOnList<>();
        inOrder(root, q, lo, hi);
        return new BinarySearchTreeKeys(q);
    }

    @Override
    public Iterable<Value> values() {
        Queue<Value> q = new QueueOnList<>();
        valuesInOrder(root, q);
        return new BinarySearchTreeValues(q);
    }

    private class Node {

        // Key
        private Key key;
        // Value
        private Value val;
        // Left son
        private Node left;
        // Right son
        private Node right;
        // Size of the subtree
        private int count;

        private Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            this.left = null;
            this.right = null;
            this.count = 1;
        }
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val);
        }

        // Compare the key to current key
        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else if (cmp == 0) {
            x.val = val;
        }

        // Update the count
        x.count = 1 + size(x.left) + size(x.right);

        return x;
    }

    private Node min(Node x) {
        return x.left == null ? x : min(x.left);
    }

    private Node max(Node x) {
        return x.right == null ? x : max(x.right);
    }

    private int size(Node x) {
        return x == null ? 0 : x.count;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }

        // Compare the key to current key
        int cmp = key.compareTo(x.key);

        if (cmp == 0) {
            // The floor has been found
            return x;
        } else if (cmp < 0) {
            // The floor is in the left subtree
            return floor(x.left, key);
        } else {
            // Either the floor is in the right subtree or the current key is the floor
            Node t = floor(x.right, key);
            return t != null ? t : x;
        }
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }

        // Compare the key to current key
        int cmp = key.compareTo(x.key);

        if (cmp == 0) {
            // The ceiling has been found
            return x;
        } else if (cmp > 0) {
            // The floor is in the left subtree
            return ceiling(x.right, key);
        } else {
            // Either the floor is in the right subtree or the current key is the floor
            Node t = ceiling(x.left, key);
            return t != null ? t : x;
        }
    }

    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }

        // Compare the key to current key
        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            // Current key is larger, the result is in the left subtree
            return rank(key, x.left);
        } else if (cmp > 0) {
            // Current key is smaller
            // The current key together with all smaller keys is a part of the result
            // The rest of the keys are in the right subtree
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            // The resut is the number of keys smaller than the current key
            return size(x.left);
        }
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }

        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }

        x.right = deleteMax(x.right);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            } else if (x.left == null) {
                return x.right;
            }

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    private void inOrder(Node x, Queue<Key> q) {
        if (x == null) {
            return;
        }

        inOrder(x.left, q);
        q.pushBack(x.key);
        inOrder(x.right, q);
    }

    private void inOrder(Node x, Queue<Key> q, Key lo, Key hi) {
        if (x == null) {
            return;
        }

        // Compare lower and upper bounds to the current key
        int loCmpX = lo.compareTo(x.key);
        int hiCmpX = hi.compareTo(x.key);

        // Search in the left subtree
        if (loCmpX < 0) {
            inOrder(x.left, q);
        }

        // Check if the current key is between bounds
        if (loCmpX <= 0 && hiCmpX >= 0) {
            q.pushBack(x.key);
        }

        // Search in the right subtree
        if (hiCmpX > 0) {
            inOrder(x.right, q);
        }
    }

    private void reverseOrder(Node x, Stack<Key> s, int size) {
        if (x == null) {
            return;
        }

        if (s.size() < size) {
            reverseOrder(x.left, s, size);

            if (s.size() < size) {
                s.push(x.key);

                if (s.size() < size) {
                    reverseOrder(x.right, s, size);
                }
            }
        }
    }

    private void valuesInOrder(Node x, Queue<Value> q) {
        if (x == null) {
            return;
        }

        valuesInOrder(x.left, q);
        q.pushBack(x.val);
        valuesInOrder(x.right, q);
    }

    private class BinarySearchTreeKeys implements Iterator<Key>, Iterable<Key> {

        private final Iterator<Key> iterator;
        private Key lastKey;

        private BinarySearchTreeKeys(Queue<Key> queue) {
            iterator = queue.iterator();
            lastKey = null;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            // Save the last key and return it
            lastKey = iterator.next();
            return lastKey;
        }

        @Override
        public void remove() {
            if (lastKey == null) {
                throw new NoSuchElementException();
            }

            delete(lastKey);
            lastKey = null;
        }

        @Override
        public Iterator<Key> iterator() {
            return this;
        }
    }

    private class BinarySearchTreeValues implements Iterator<Value>, Iterable<Value> {

        private final Iterator<Value> iterator;
        private Value lastValue;

        private BinarySearchTreeValues(Queue<Value> queue) {
            iterator = queue.iterator();
            lastValue = null;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Value next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            // Save the last value and return it
            lastValue = iterator.next();
            return lastValue;
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
