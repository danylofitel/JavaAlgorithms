package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Vector<Item> implements Iterable<Item> {

    private final static int initialCapacity = 50;
    private Item[] array;
    private int size;

    public Vector() {
        this(0);
    }

    public Vector(int initialSize) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("Container size cannot be negative");
        }

        array = (Item[]) new Object[initialCapacity];
        size = initialSize;
    }

    public Vector(int initialSize, Item initializer) {
        this(initialSize);
        for (int i = 0; i < size; ++i) {
            array[i] = initializer;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addLast(Item item) {
        enlarge();
        array[size++] = item;
    }

    public Item getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot access elements from an empty container");
        }

        return array[0];
    }

    public Item getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot access elements from an empty container");
        }

        return array[size - 1];
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item result = array[--size];
        array[size] = null;

        shrink();

        return result;
    }

    public Item get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[i];
    }

    public void set(int i, Item value) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[i] = value;
    }

    public boolean contains(Item value) {
        for (int i = 0; i < size; ++i) {
            if (array[i] == value || array[i].equals(i)) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(Item value) {
        for (int i = 0; i < size; ++i) {
            if (array[i] == value || array[i].equals(i)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Item value) {
        for (int i = size - 1; i >= 0; --i) {
            if (array[i] == value || array[i].equals(i)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        for (int i = 0; i < size; ++i) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new VectorIterator(true);
    }

    public Iterator<Item> reverseIterator() {
        return new VectorIterator(false);
    }

    private class VectorIterator implements Iterator<Item> {

        private final boolean forward;
        private int current;

        public VectorIterator(boolean forward) {
            this.forward = forward;
            this.current = forward ? 0 : size;
        }

        @Override
        public boolean hasNext() {
            return forward ? (current < size) : (current > 0);
        }

        @Override
        public Item next() {
            return forward ? array[current++] : array[current--];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private void enlarge() {
        if (size == array.length) {
            Item[] newArray = (Item[]) new Object[array.length * 2];
            for (int i = 0; i < size; ++i) {
                newArray[i] = array[i];
                array[i] = null;
            }
            array = newArray;
        }
    }

    private void shrink() {
        if (array.length >= size * 4 && size > initialCapacity) {
            Item[] newArray = (Item[]) new Object[array.length / 2];
            for (int i = 0; i < size; ++i) {
                newArray[i] = array[i];
                array[i] = null;
            }
            array = newArray;
        }
    }
}
