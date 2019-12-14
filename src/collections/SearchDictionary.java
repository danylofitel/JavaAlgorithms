package collections;

import java.util.HashMap;
import java.util.Map;

public class SearchDictionary {

    // A root node with first letters of all words
    private final Node root;

    // Number of words in the dictionary
    private int wordCount;

    public SearchDictionary() {
        // Create a root node corresponding to an empty word indicating that the word is missing
        root = new Node(false);
        // No words have been added yet
        wordCount = 0;
    }

    public void addWord(String word) {
        // Current node, starting with the root
        Node node = root;
        // Current character
        Character c = null;

        // A sequence of nodes corresponding to the word must be traversed or created
        for (int i = 0; i < word.length(); ++i) {
            // Next character
            c = word.charAt(i);

            if (node.nodes.containsKey(c)) {
                // Move to the node with the corresponding next character if it exists
                node = node.nodes.get(c);
            } else {
                // Otherwise create a new node for such path and move to it
                Node newNode = new Node(false);
                node.nodes.put(c, newNode);
                node = newNode;
            }
        }

        // Insert the character to the node and update word count if the word was missing from the dictionary
        if (node.exists == false) {
            node.exists = true;
            ++wordCount;
        }
    }

    public String delWord(String word) {
        // Find the node with the last character of the word
        Node node = find(word);

        // Remove the character from the node and update word count if the dictionary contains the word
        if (node != null && node.exists == true) {
            node.exists = false;
            --wordCount;
            return word;
        } else {
            return null;
        }
    }

    public boolean hasWord(String word) {
        // Find the node for the last character of the word
        Node target = find(word);
        // The word is present if the node is not null and its character value is not null
        return target != null && target.exists == true;
    }

    public Iterable<String> query(String query) {
        // Check if the search query is valid
        if (!validQuery(query)) {
            throw new IllegalArgumentException("Invalid query " + query);
        }

        // A bag of words satisfying the query
        Bag<String> results = new BagOnList<>();

        // Current node
        Node node = root;
        // Current character
        Character c = null;

        // Follow the path corresponding to the query until the joker character has been met
        for (int i = 0; i < query.length() && !(query.charAt(i) == '*'); ++i) {
            // Next character
            c = query.charAt(i);

            // Move to the next node if it exists
            if (node.nodes.containsKey(c)) {
                node = node.nodes.get(c);
            } else {
                // Otherwise return results right away
                return results;
            }
        }

        // If the query ends with a trailing joker character
        if (query.charAt(query.length() - 1) == '*') {
            // Add all words with same beginning to the bag
            String base = query.substring(0, query.length() - 1);
            collectWords(base, node, results);
            return results;
        } else if (node.exists == true) {
            // Else add current word to the query if it exists
            results.add(query);
        }

        // Return a collection of items satisfying the query
        return results;
    }

    public int countWords() {
        return wordCount;
    }

    private Node find(String word) {
        // Current node
        Node node = root;
        // Current character value
        Character c = null;

        // Traverse through the node path corresponding to the word
        for (int i = 0; i < word.length(); ++i) {
            // Next character value
            c = word.charAt(i);

            // Move to the next node if it exists
            if (node.nodes.containsKey(c)) {
                node = node.nodes.get(c);
            } else {
                // Otherwise return null if the full path has not been created yet
                return null;
            }
        }

        // Return the last node if the full path to it exists
        return node;
    }

    private void collectWords(String base, Node root, Bag<String> result) {
        // Add base string if the node 
        if (root.exists == true) {
            result.add(base);
        }

        // Traverse all subtrees recursively
        for (Character c : root.nodes.keySet()) {
            collectWords(base + c, root.nodes.get(c), result);
        }
    }

    private static boolean validQuery(String query) {
        // A query must be a non-empty string
        if (query == null || query.length() < 1) {
            return false;
        }

        // The trailing joker character can be located only at the end of the query
        for (int i = 0; i < query.length() - 1; ++i) {
            if (query.charAt(i) == '*') {
                return false;
            }
        }

        return true;
    }

    private static class Node {

        // Indicates whether a word corresponding to the path to current node is present
        private boolean exists;
        // Maps each next character to a corresponding node
        private final Map<Character, Node> nodes;

        private Node(boolean exists) {
            // Initialize the word indicator
            this.exists = exists;
            // Create an empty map
            this.nodes = new HashMap<>();
        }
    }
}
