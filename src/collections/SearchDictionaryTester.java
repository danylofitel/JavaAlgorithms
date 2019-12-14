package collections;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import lib.StdIn;
import lib.StdOut;

public class SearchDictionaryTester {

    private final static int minlen = 0;
    private static final String testFile = "tale.txt";

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(testFile));
        InputStream userInputStream = System.in;
        System.setIn(in);

        SearchDictionary dictionary = new SearchDictionary();

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();

            if (word.length() >= minlen) {
                dictionary.addWord(word);
            }
        }

        System.setIn(userInputStream);

        StdOut.println("Dictionary size = " + dictionary.countWords());

        String searchString = "";
        Scanner scanner = new Scanner(userInputStream);
        do {
            StdOut.println("Please type search query or empty string to exit");
            searchString = scanner.nextLine();
            if (!searchString.isEmpty()) {
                Iterable<String> results = dictionary.query(searchString);
                StdOut.println(((Bag) results).size() + " results");
                for (String s : dictionary.query(searchString)) {
                    StdOut.println(s);
                }
            }
            StdOut.println();
        } while (!searchString.isEmpty());
    }
}
