package collections;

public class RandomizedQueueTest {

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        final int length = 500;

        System.out.println("Is empty = " + queue.isEmpty());
        System.out.println("Size = " + queue.size());

        System.out.println("Adding elements");
        for (int i = 0; i < length; ++i) {
            System.out.println("Inserting " + i);
            queue.enqueue(String.valueOf(i));
            System.out.println(queue.sample() + " is random");
        }

        System.out.println("Is empty = " + queue.isEmpty());
        System.out.println("Size = " + queue.size());

        System.out.println("Iterating through queue");
        for (String s : queue) {
            System.out.println(s);
        }

        System.out.println("Removing some elements");
        for (int i = 0; i < length / 2; ++i) {
            System.out.println(queue.dequeue() + " removed");
        }

        System.out.println("Is empty = " + queue.isEmpty());
        System.out.println("Size = " + queue.size());

        for (String s : queue) {
            System.out.println(s);
        }

        System.out.println("Removing all remaining elements");
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue() + " removed");
        }

        System.out.println("Is empty = " + queue.isEmpty());
        System.out.println("Size = " + queue.size());

        for (String s : queue) {
            System.out.println(s);
        }

        for (int i = 0; i < 100; ++i) {
            queue.enqueue(String.valueOf(i));
            queue.dequeue();
        }

        System.out.println("Is empty = " + queue.isEmpty());
        System.out.println("Size = " + queue.size());

        for (String s : queue) {
            System.out.println(s);
        }
    }
}
