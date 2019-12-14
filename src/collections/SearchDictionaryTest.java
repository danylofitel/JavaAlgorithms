package collections;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import lib.StdIn;
import lib.StdOut;
import lib.Stopwatch;

public class SearchDictionaryTest {

    private final static int minlen = 0;
    private static final String testFile = "tale.txt";

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        int words = 0;

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(testFile));
        System.setIn(in);

        SearchDictionary dictionary = new SearchDictionary();
        TableOnBST<String, Integer> st = new TableOnBST<>();

        Stopwatch timer = new Stopwatch();
        
        StdOut.println("Dictionary size = " + dictionary.countWords());
        StdOut.println("The dictionary contains word \"the\": " + dictionary.hasWord("the"));

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();

            if (word.length() >= minlen) {
                dictionary.addWord(word);
                st.put(word, 1);
                ++words;
            }
        }

        StdOut.println("Words = " + words);
        StdOut.println("Distinct words = " + st.size());
        StdOut.println("Dictionary size = " + dictionary.countWords());
        
        StdOut.println("The dictionary contains word \"the\": " + dictionary.hasWord("the"));

        int count = 0;
        for (String s : st.keys()) {
            if (dictionary.hasWord(s)) {
                ++count;
            }
        }

        StdOut.println("Dictionary contains " + count + " words from the file");

        StdOut.println("Collection of words from the dictionary contains "
                + ((Bag) dictionary.query("*")).size() + " words");

        StdOut.println("Looking for word \"the\"");
        for (String s : dictionary.query("the")) {
            StdOut.println(s);
        }
        StdOut.println(dictionary.hasWord("the"));
        StdOut.println(((Bag) dictionary.query("the")).size());

        StdOut.println("Looking for all words that start with \"the\"");
        for (String s : dictionary.query("the*")) {
            StdOut.println(s);
        }
        StdOut.println(((Bag) dictionary.query("the*")).size());

        StdOut.println("Deleting all words");

        for (String s : st.keys()) {
            if (dictionary.hasWord(s)) {
                dictionary.delWord(s);
            }
        }

        StdOut.println("Dictionary size = " + dictionary.countWords());
        StdOut.println("The dictionary contains word \"the\": " + dictionary.hasWord("the"));

        count = 0;
        for (String s : st.keys()) {
            if (dictionary.hasWord(s)) {
                ++count;
            }
        }

        StdOut.println("Words remaining = " + count);

        StdOut.println("Collection of words from the dictionary contains "
                + ((Bag) dictionary.query("*")).size() + " words");

        StdOut.println("Done in " + timer.elapsedTime() + " milliseconds");
    }
}
