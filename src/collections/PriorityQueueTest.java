package collections;

public class PriorityQueueTest {

    public static void main(String[] args) {
        final int n = 5000;

        RandomizedQueue<Integer> rq = new RandomizedQueue();
        for (int i = 0; i < n; ++i) {
            rq.enqueue(i);
        }

        Queue<Integer> pq = new MinPriorityQueue<>();

        System.out.println("Is empty = " + pq.isEmpty());
        System.out.println("Size = " + pq.size());

        System.out.println("Adding elements");
        while (!rq.isEmpty()) {
            int item = rq.dequeue();
            System.out.println(item + " inserted");
            pq.pushBack(item);
        }

        System.out.println("Is empty = " + pq.isEmpty());
        System.out.println("Size = " + pq.size());

        System.out.println("Iterating through priority queue");
        for (int i : pq) {
            System.out.println(i);
        }

        System.out.println("Is empty = " + pq.isEmpty());
        System.out.println("Size = " + pq.size());

        System.out.println("Removing elements");
        while (!pq.isEmpty()) {
            System.out.println(pq.popFront());
        }

        System.out.println("Is empty = " + pq.isEmpty());
        System.out.println("Size = " + pq.size());

        System.out.println("Iterating through priority queue");
        for (int i : pq) {
            System.out.println(i);
        }
    }
}
