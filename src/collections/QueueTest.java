package collections;

public class QueueTest {

    public static void main(String[] args) {
        Queue<String> queue = new QueueOnArrays<>();
        final int length = 500;

        System.out.println("Is empty = " + queue.isEmpty());
        System.out.println("Size = " + queue.size());

        System.out.println("Adding elements");
        for (int i = 0; i < length; ++i) {
            queue.pushBack(String.valueOf(i));
            System.out.println(queue.front() + " is first");
            System.out.println(queue.back() + " inserted");
        }

        System.out.println("Is empty = " + queue.isEmpty());
        System.out.println("Size = " + queue.size());

        System.out.println("Iterating through queue");
        for (String s : queue) {
            System.out.println(s);
        }

        System.out.println("Removing elements");
        for (int i = 0; i < length; ++i) {
            System.out.println(queue.popFront() + " removed");
        }

        System.out.println("Is empty = " + queue.isEmpty());
        System.out.println("Size = " + queue.size());

        for (String s : queue) {
            System.out.println(s);
        }

        for (int i = 0; i < 100; ++i) {
            queue.pushBack(String.valueOf(i));
            queue.popFront();
        }

        System.out.println("Is empty = " + queue.isEmpty());
        System.out.println("Size = " + queue.size());

        for (String s : queue) {
            System.out.println(s);
        }
    }
}
