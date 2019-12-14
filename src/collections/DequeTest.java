package collections;

public class DequeTest {

    public static void main(String[] args) {
        Deque<String> deque = new DequeOnArrays<>();
        final int length = 500;
        int f = 0;
        int l = 0;

        for (String s : deque) {
            System.out.println(s);
        }

        System.out.println("Is empty = " + deque.isEmpty());
        System.out.println("Size = " + deque.size());

        System.out.println("Adding elements");
        for (int i = 0; i < length; ++i) {
            deque.addFirst(String.valueOf(--f));
            deque.addLast(String.valueOf(++l));
            System.out.println(deque.getFirst() + " inserted first");
            System.out.println(deque.getLast() + " inserted last");
        }

        System.out.println("Is empty = " + deque.isEmpty());
        System.out.println("Size = " + deque.size());

        System.out.println("Iterating through deque");
        for (String s : deque) {
            System.out.println(s);
        }

        System.out.println("Removing elements");
        for (int i = 0; i < length; ++i) {
            System.out.println(deque.removeFirst() + " removed first");
            System.out.println(deque.removeLast() + " removed last");
        }

        System.out.println("Is empty = " + deque.isEmpty());
        System.out.println("Size = " + deque.size());

        for (String s : deque) {
            System.out.println(s);
        }

        for (int i = 0; i < 100; ++i) {
            deque.addFirst(String.valueOf(--f));
            deque.addLast(String.valueOf(++l));
            deque.removeFirst();
            deque.removeLast();
        }

        System.out.println("Is empty = " + deque.isEmpty());
        System.out.println("Size = " + deque.size());

        for (String s : deque) {
            System.out.println(s);
        }
    }
}
