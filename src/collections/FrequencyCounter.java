package collections;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import lib.StdIn;
import lib.StdOut;
import lib.Stopwatch;

public class FrequencyCounter {

    private final static int minlen = 0;
    private static final String testFile = "tale.txt";

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        int distinct = 0, words = 0;

        Table<String, Integer> st = new TableOnBST<>();

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(testFile));
        System.setIn(in);

        Stopwatch timer = new Stopwatch();

        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();

            if (key.length() < minlen) {
                continue;
            }

            ++words;

            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                ++distinct;
            }
        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words  = " + words);

        System.out.println("Done in " + timer.elapsedTime() + " milliseconds");
    }
}
