package wordnet;

import algorithms.graphs.order.DepthFirstOrder;
import algorithms.graphs.DirectedGraph;
import algorithms.graphs.order.GraphOrder;
import collections.Vector;
import lib.In;

public class WordNet {

    private final Vector<String> nouns;
    private final Vector<String> descriptions;
    private final DirectedGraph wordnet;
    private final SAP sap;

    public WordNet(String synsets, String hypernyms) {
        nouns = new Vector<>();
        descriptions = new Vector<>();

        In synsetsIn = new In(synsets);
        while (synsetsIn.hasNextLine()) {
            String[] split = synsetsIn.readLine().split(",");
            int v = Integer.parseInt(split[0]);
            if (v != nouns.size()) {
                throw new IllegalArgumentException("Invalid synset index " + v);
            } else if (nouns.isEmpty() == false && split[1].compareTo(nouns.getLast()) < 0) {
                throw new IllegalArgumentException(
                        "Invalid synset order: " + nouns.getLast().toString() + ", " + split[1]);
            }
            nouns.addLast(split[1]);
            descriptions.addLast(split[2]);
        }

        wordnet = new DirectedGraph(nouns.size());

        In hypernimsIn = new In(hypernyms);
        while (hypernimsIn.hasNextLine()) {
            String[] split = hypernimsIn.readLine().split(",");
            int v = Integer.parseInt(split[0]);
            if (v < 0 || v >= nouns.size()) {
                throw new IllegalArgumentException("Invalid synset index " + v);
            }
            for (int i = 1; i < split.length; ++i) {
                int w = Integer.parseInt(split[i]);
                if (v < 0 || v >= nouns.size()) {
                    throw new IllegalArgumentException("Invalid synset index " + w);
                }
                wordnet.addEdge(v, w);
            }
        }

        GraphOrder order = new DepthFirstOrder(wordnet);
        if (order.orderCount() < wordnet.V()) {
            throw new IllegalArgumentException("Grahp contains loops");
        }

        sap = new SAP(wordnet);
    }

    public int size() {
        return nouns.size();
    }

    public Iterable<String> nouns() {
        return nouns;
    }

    public String getNoun(int i) {
        return nouns.get(i);
    }

    public boolean isNoun(String word) {
        int index = nounIndex(word);
        return index >= 0;
    }

    public int nounIndex(String word) {
        int low = 0;
        int high = nouns.size() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable midVal = (Comparable) nouns.get(mid);
            int cmp = midVal.compareTo(word);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public int distance(int nounA, int nounB) {
        if (nounA < 0 || nounA >= wordnet.V()) {
            throw new IndexOutOfBoundsException(Integer.toString(nounA));
        } else if (nounB < 0 || nounB >= wordnet.V()) {
            throw new IndexOutOfBoundsException(Integer.toString(nounB));
        }

        return sap.length(nounA, nounB);
    }

    public int distance(String nounA, String nounB) {
        int a = nounIndex(nounA);
        if (a < 0) {
            throw new IllegalArgumentException(nounA + " is not a noun");
        }

        int b = nounIndex(nounB);
        if (b < 0) {
            throw new IllegalArgumentException(nounB + " is not a noun");
        }

        return distance(a, b);
    }

    public int sap(int nounA, int nounB) {
        if (nounA < 0 || nounA >= wordnet.V()) {
            throw new IndexOutOfBoundsException(Integer.toString(nounA));
        } else if (nounB < 0 || nounB >= wordnet.V()) {
            throw new IndexOutOfBoundsException(Integer.toString(nounB));
        }

        return sap.ancestor(nounA, nounB);
    }

    public String sap(String nounA, String nounB) {
        int left = nounIndex(nounA);
        if (left < 0) {
            throw new IllegalArgumentException(nounA + " is not a noun");
        }

        int right = nounIndex(nounB);
        if (right < 0) {
            throw new IllegalArgumentException(nounB + " is not a noun");
        }

        int sapIndex = sap(left, right);
        return sapIndex >= 0 ? nouns.get(sapIndex) : null;
    }
}
