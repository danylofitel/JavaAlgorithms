package algorithms.graphs;

import collections.Bag;
import collections.BagOnArray;
import java.util.HashSet;
import java.util.Set;

public class EdgeWeightedDirectedGraph {

    private final int V;
    private int E;
    private int L;
    private final Set<DirectedEdge>[] adj;

    public EdgeWeightedDirectedGraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");

        this.V = V;
        this.E = 0;
        this.L = 0;
        adj = (Set<DirectedEdge>[]) new Set[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new HashSet<>();
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

    public int outDegree(int v) {
        if (v < 0 || v >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + v + " out of range");
        }

        return adj[v].size();
    }

    public boolean hasEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();

        if (v < 0 || v >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + v + " out of range");
        } else if (w < 0 || w >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + w + " out of range");
        }

        return adj[v].contains(e);
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();

        if (v < 0 || v >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + v + " out of range");
        } else if (w < 0 || w >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + w + " out of range");
        }

        if (!adj[v].contains(e)) {
            adj[v].add(e);
            if (v == w) {
                ++L;
            } else {
                ++E;
            }
        }

        adj[v].add(e);
    }

    public void removeEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();

        if (v < 0 || v >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + v + " out of range");
        } else if (w < 0 || w >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + w + " out of range");
        }

        if (adj[v].contains(e)) {
            adj[v].remove(e);
            if (v == w) {
                --L;
            } else {
                --E;
            }
        }
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new BagOnArray<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }
}
