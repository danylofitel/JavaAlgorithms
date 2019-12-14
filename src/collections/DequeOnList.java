package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeOnList<Item> implements Deque<Item> {

    private class Node {

        Item item;
        Node previous;
        Node next;
    }
    private Node head;
    private Node tail;
    private int size;

    public DequeOnList() {
        head = null;
        tail = null;
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

        Node newHead = new Node();
        newHead.item = item;
        newHead.previous = null;
        newHead.next = head;

        if (size != 0) {
            head.previous = newHead;
        } else {
            tail = newHead;
        }

        head = newHead;
        ++size;
    }

    @Override
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Null element can't be added to the deque.");
        }

        Node newTail = new Node();
        newTail.item = item;
        newTail.previous = tail;
        newTail.next = null;

        if (size != 0) {
            tail.next = newTail;
        } else {
            head = newTail;
        }

        tail = newTail;
        ++size;
    }

    @Override
    public Item getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return head.item;
    }

    @Override
    public Item getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return tail.item;
    }

    @Override
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item result = head.item;
        head.item = null;
        head = head.next;
        --size;
        if (size != 0) {
            head.previous = null;
        } else {
            tail = head;
        }

        return result;
    }

    @Override
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item result = tail.item;
        tail.item = null;
        tail = tail.previous;
        --size;
        if (size != 0) {
            tail.next = null;
        } else {
            head = tail;
        }

        return result;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeOnListIterator();
    }

    private class DequeOnListIterator implements Iterator<Item> {

        private Node last;
        private Node current;

        public DequeOnListIterator() {
            last = null;
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                last = current;
                current = current.next;
                return last.item;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public void remove() {
            if (last == null) {
                throw new NoSuchElementException();
            }

            if (last.previous != null) {
                last.previous.next = last.next;
            }

            if (last.next != null) {
                last.next.previous = last.previous;
            }

            last.item = null;
            last.previous = null;
            last.next = null;
            last = null;
            --size;

            if (size == 0) {
                head = null;
                tail = null;
            }
        }
    }
}
