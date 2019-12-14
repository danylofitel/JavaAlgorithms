package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeOnArrays<Item> implements Deque<Item> {

    private static final int nodeSize = 50;

    private class Node {

        LimitedDeque<Item> items;
        Node previous;
        Node next;
    }
    private Node head;
    private Node tail;
    private int size;

    public DequeOnArrays() {
        head = new Node();
        head.items = new LimitedDeque(nodeSize);
        head.previous = null;
        head.next = null;
        tail = head;
        size = 0;
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
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Null element can't be added to the deque.");
        }

        if (head.items.size() < nodeSize) {
            head.items.addFirst(item);
        } else {
            if (head.previous == null) {
                head.previous = new Node();
                head.previous.next = head;
                head = head.previous;
                head.items = new LimitedDeque(nodeSize);
                head.previous = null;
            } else {
                head = head.previous;
            }

            head.items.addFirst(item);
        }

        ++size;
    }

    @Override
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Null element can't be added to the deque.");
        }

        if (tail.items.size() < nodeSize) {
            tail.items.addLast(item);
        } else {
            if (tail.next == null) {
                tail.next = new Node();
                tail.next.previous = tail;
                tail = tail.next;
                tail.items = new LimitedDeque(nodeSize);
                tail.next = null;
            } else {
                tail = tail.next;
            }

            tail.items.addLast(item);
        }

        ++size;
    }

    @Override
    public Item getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return head.items.getFirst();
    }

    @Override
    public Item getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return tail.items.getLast();
    }

    @Override
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item result = head.items.removeFirst();
        --size;

        if (head.items.isEmpty() && head != tail) {
            if (head.previous != null) {
                head.previous = null;
            }

            head = head.next;
        }

        return result;
    }

    @Override
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item result = tail.items.removeLast();
        --size;

        if (tail.items.isEmpty() && head != tail) {
            if (tail.next != null) {
                tail.next = null;
            }

            tail = tail.previous;
        }

        return result;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeOnListOfArraysIterator();
    }

    private class DequeOnListOfArraysIterator implements Iterator<Item> {

        private Node current;
        private Iterator<Item> currentIterator;

        public DequeOnListOfArraysIterator() {
            current = DequeOnArrays.this.head;
            currentIterator = current.items.iterator();
        }

        @Override
        public boolean hasNext() {
            return currentIterator.hasNext()
                    || (current.next != null && current.next.items.size() > 0);
        }

        @Override
        public Item next() {
            if (hasNext()) {
                if (currentIterator.hasNext()) {
                    return currentIterator.next();
                } else {
                    current = current.next;
                    currentIterator = current.items.iterator();
                    return currentIterator.next();
                }
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
