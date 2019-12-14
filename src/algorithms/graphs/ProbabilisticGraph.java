package algorithms.graphs;

import collections.Bag;
import collections.BagOnArray;

public class ProbabilisticGraph {

    private final int V;
    private int E;
    private int L;
    private final Bag<ProbabilisticEdge>[] adj;

    public ProbabilisticGraph(int V, Iterable<ProbabilisticEdge> edges) {
        this.V = V;
        this.E = 0;
        this.L = 0;
        adj = (Bag<ProbabilisticEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new BagOnArray<>();
        }

        for (ProbabilisticEdge e : edges) {
            addEdge(e);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public int L() {
        return L;
    }

    public int degree(int v) {
        if (v < 0 || v >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + v + " out of range");
        }

        return adj[v].size();
    }

    public Iterable<ProbabilisticEdge> adj(int v) {
        return adj[v];
    }

    private void addEdge(ProbabilisticEdge e) {
        int v = e.from();
        int w = e.to();

        if (v < 0 || v >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + v + " out of range");
        } else if (w < 0 || w >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + w + " out of range");
        }

        adj[v].add(e);
        if (v == w) {
            ++L;
        } else {
            ++E;
        }

        adj[v].add(e);
    }

/*    private void checkProbabilities() {
        for (int i = 0; i < V; ++i) {
            double p = 0.0;
            for (ProbabilisticEdge e : adj[i]) {
                p += e.probability();
            }
            if (Double.compare(p, 1.0) != 0) {
                throw new IllegalArgumentException("Probabilities of edges from " + i + " do not sum up to 1 and are equal " + p);
            }
        }
    }*/
}
