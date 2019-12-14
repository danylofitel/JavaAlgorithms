package collections;

import java.util.Iterator;

public class LimitedDequeTest {

    public static void main(String[] args) {
        final int length = 500;
        LimitedDeque<Integer> array = new LimitedDeque<>(length);

        System.out.println("Is empty = " + array.isEmpty());
        System.out.println("Is full = " + array.isFull());
        System.out.println("Capacity = " + array.capacity());
        System.out.println("Size = " + array.size());

        for (int i = 0; i < length; i += 2) {
            array.addFirst(i);
            array.addLast(i + 1);
        }

        System.out.println("Is empty = " + array.isEmpty());
        System.out.println("Is full = " + array.isFull());
        System.out.println("Capacity = " + array.capacity());
        System.out.println("Size = " + array.size());

        Iterator<Integer> itor = array.iterator();
        while (itor.hasNext()) {
            System.out.println(itor.next());
        }

        for (int i = 0; i < length; i += 2) {
            System.out.println(array.removeFirst());
            System.out.println(array.removeLast());
        }

        System.out.println("Is empty = " + array.isEmpty());
        System.out.println("Is full = " + array.isFull());
        System.out.println("Capacity = " + array.capacity());
        System.out.println("Size = " + array.size());

        itor = array.iterator();
        while (itor.hasNext()) {
            System.out.println(itor.next());
        }
    }
}
