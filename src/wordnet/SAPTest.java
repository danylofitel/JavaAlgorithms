package wordnet;

import algorithms.graphs.DirectedGraph;
import lib.In;
import lib.StdIn;
import lib.StdOut;

public class SAPTest {

    private static final String digraphFilename = "digraph1.txt";
    
    public static void main(String[] args) {
        In in = new In(digraphFilename);
        DirectedGraph G = new DirectedGraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
