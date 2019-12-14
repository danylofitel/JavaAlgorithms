package collections;

import java.util.Iterator;

public class TableTest {

    public static void main(String[] args) {
        Table<Integer, String> st = new TableOnBST<>();

        System.out.println("Is empty = " + st.isEmpty());
        System.out.println("Size = " + st.size());

        System.out.println("Iterating through keys");
        for (int i : st.keys()) {
            System.out.println(i);
        }

        System.out.println("Iterating through values");
        for (String s : st.values()) {
            System.out.println(s);
        }

        System.out.println("Inserting elements");
        for (int i = 0; i < 10; ++i) {
            st.put(i, String.valueOf(i) + "s");
            System.out.println(i + " inserted");
        }

        System.out.println("Iterating through keys");
        for (int i : st.keys()) {
            System.out.println(i);
        }

        System.out.println("Iterating through keys with bounds");
        for (int i : st.keys(st.min(), st.max())) {
            System.out.println(i);
        }

        System.out.println("Iterating through values");
        for (String s : st.values()) {
            System.out.println(s);
        }

        System.out.println("Accessing keys by index");
        for (int i = 0; i < st.size(); ++i) {
            int key = st.select(i);
            System.out.println("Key = " + key + "; value = " + st.get(key));
        }

        System.out.println("Reading contents");
        for (Integer i : st.keys()) {
            System.out.println(
                    "key = " + i
                    + "; value = " + st.get(i)
                    + "; rank = " + st.rank(i)
                    + "; floor = " + st.floor(i)
                    + "; ceiling = " + st.ceiling(i)
                    + "; contains = " + st.contains(i));
        }

        System.out.println("Ceiling for " + (st.min() - 1) + " = " + st.ceiling(st.min() - 1));
        System.out.println("Floor for " + (st.min() - 1) + " = " + st.floor(st.min() - 1));
        System.out.println("Ceiling for " + (st.min()) + " = " + st.ceiling(st.min()));
        System.out.println("Floor for " + (st.min()) + " = " + st.floor(st.min()));
        System.out.println("Ceiling for " + (st.max()) + " = " + st.ceiling(st.max()));
        System.out.println("Floor for " + (st.max()) + " = " + st.floor(st.max()));
        System.out.println("Ceiling for " + (st.max() + 1) + " = " + st.ceiling(st.max() + 1));
        System.out.println("Floor for " + (st.max() + 1) + " = " + st.floor(st.max() + 1));

        System.out.println("Min key = " + st.min());
        System.out.println("Max key = " + st.max());

        System.out.println("Removing min and max");
        st.deleteMin();
        st.deleteMax();

        System.out.println("Is empty = " + st.isEmpty());
        System.out.println("Size = " + st.size());
        System.out.println("Full size = " + st.size(st.min(), st.max()));
        System.out.println("Min key = " + st.min());
        System.out.println("Max key = " + st.max());

        System.out.println("Removing elements");
        for (int i = st.min(); !st.isEmpty() && i <= st.max(); ++i) {
            System.out.print("Removing " + st.get(i) + ": ");
            System.out.println(st.delete(i));
        }

        System.out.println("Is empty = " + st.isEmpty());
        System.out.println("Size = " + st.size());

        System.out.println("Inserting elements");
        for (int i = 0; i < 10; ++i) {
            st.put(i, String.valueOf(i));
            System.out.println(i + " inserted");
        }

        System.out.println("Is empty = " + st.isEmpty());
        System.out.println("Size = " + st.size());
        System.out.println("Full size = " + st.size(st.min(), st.max()));
        System.out.println("Min key = " + st.min());
        System.out.println("Max key = " + st.max());

        System.out.println("Removing first element using iterator");
        Iterator<Integer> itor = st.keys().iterator();
        System.out.println(itor.next() + " removed");
        itor.remove();

        System.out.println("Is empty = " + st.isEmpty());
        System.out.println("Size = " + st.size());
        System.out.println("Full size = " + st.size(st.min(), st.max()));
        System.out.println("Min key = " + st.min());
        System.out.println("Max key = " + st.max());

        System.out.println("Removing last element using iterator");
        itor = st.keys().iterator();
        int last = 0;
        while (itor.hasNext()) {
            last = itor.next();
        }
        itor.remove();
        System.out.println(last + " removed");

        System.out.println("Is empty = " + st.isEmpty());
        System.out.println("Size = " + st.size());
        System.out.println("Full size = " + st.size(st.min(), st.max()));
        System.out.println("Min key = " + st.min());
        System.out.println("Max key = " + st.max());

        System.out.println("Iterating through symbol table");
        for (int i : st.keys(st.min(), st.max())) {
            System.out.println(i);
        }

        System.out.println("Removing remaining elements using iterator");
        itor = st.keys().iterator();
        while (itor.hasNext()) {
            System.out.println(itor.next() + " removed");
            itor.remove();
        }

        System.out.println("Is empty = " + st.isEmpty());
        System.out.println("Size = " + st.size());

        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        for (int i = 0; i < 100; ++i) {
            rq.enqueue(i);
        }

        System.out.println("Inserting items in random order");
        while (!rq.isEmpty()) {
            int item = rq.dequeue();
            st.put(item, String.valueOf(item));
        }

        System.out.println("Is empty = " + st.isEmpty());
        System.out.println("Size = " + st.size());
        System.out.println("Full size = " + st.size(st.min(), st.max()));
        System.out.println("Min key = " + st.min());
        System.out.println("Max key = " + st.max());

        System.out.println("Ceiling for " + (st.min() - 1) + " = " + st.ceiling(st.min() - 1));
        System.out.println("Floor for " + (st.min() - 1) + " = " + st.floor(st.min() - 1));
        System.out.println("Ceiling for " + (st.min()) + " = " + st.ceiling(st.min()));
        System.out.println("Floor for " + (st.min()) + " = " + st.floor(st.min()));
        System.out.println("Ceiling for " + (st.max()) + " = " + st.ceiling(st.max()));
        System.out.println("Floor for " + (st.max()) + " = " + st.floor(st.max()));
        System.out.println("Ceiling for " + (st.max() + 1) + " = " + st.ceiling(st.max() + 1));
        System.out.println("Floor for " + (st.max() + 1) + " = " + st.floor(st.max() + 1));

        System.out.println("Removing all elements");
        while (!st.isEmpty()) {
            st.delete(st.select(0));
        }

        System.out.println("Is empty = " + st.isEmpty());
        System.out.println("Size = " + st.size());
    }
}
