package algorithms.graphs.degrees;

import algorithms.graphs.DirectedGraph;
import algorithms.graphs.Graph;
import collections.Vector;

public class Degrees {

    private final int[] inDegrees;
    private final int[] outDegrees;
    private final Vector<Integer> sources;
    private final Vector<Integer> sinks;
    private boolean isMap;

    public Degrees(DirectedGraph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        inDegrees = new int[G.V()];
        outDegrees = new int[G.V()];
        sources = new Vector<>();
        sinks = new Vector<>();

        findDegrees(G);
    }

    public int inDegree(int v) {
        return inDegrees[v];
    }

    public int outDegree(int v) {
        return outDegrees[v];
    }

    public Iterable<Integer> sources() {
        return sources;
    }

    public Iterable<Integer> sinks() {
        return sinks;
    }

    public boolean isMap() {
        return isMap;
    }

    private void findDegrees(Graph G) {
        for (int from = 0; from < G.V(); ++from) {
            for (int to : G.adjVertices(from)) {
                ++inDegrees[to];
                ++outDegrees[from];
            }
        }

        isMap = true;
        for (int i = 0; i < G.V(); ++i) {
            if (inDegrees[i] == 0) {
                sources.addLast(i);
            } else if (outDegrees[i] == 0) {
                sinks.addLast(i);
            }

            if (isMap && outDegrees[i] != 1) {
                isMap = false;
            }
        }
    }
}
