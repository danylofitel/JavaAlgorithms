package algorithms.graphs;

import java.util.Set;
import java.util.TreeSet;
import lib.In;

public class DirectedGraph implements Graph {

    private final Set<Integer>[] adj;
    private int E;
    private int L;

    public DirectedGraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");

        adj = (Set<Integer>[]) new Set[V];
        for (int v = 0; v < adj.length; v++) {
            adj[v] = new TreeSet<>();
        }
        E = 0;
        L = 0;
    }

    public DirectedGraph(UndirectedGraph G) {
        this(G.V());
        for (int i = 0; i < G.V(); ++i) {
            for (int j : G.adjVertices(i)) {
                adj[i].add(j);
            }
        }
        E = 2 * G.V();
        L = G.L();
    }

    public DirectedGraph(In in) {
        this(in.readInt());
        int e = in.readInt();
        for (int i = 0; i < e; ++i) {
            int v = in.readInt();
            int w = in.readInt();
            if (!adj[v].contains(w)) {
                adj[v].add(w);
                if (v != w) {
                    ++E;
                } else {
                    ++L;
                }
            }
        }
    }

    @Override
    public boolean hasEdge(int v, int w) {
        return adj[v].contains(w);
    }

    @Override
    public void addEdge(int v, int w) {
        if (v < 0 || v >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + v + " out of range");
        } else if (w < 0 || w >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + w + " out of range");
        }

        if (!adj[v].contains(w)) {
            adj[v].add(w);
            if (v != w) {
                ++E;
            } else {
                ++L;
            }
        }
    }

    @Override
    public void removeEdge(int v, int w) {
        if (v < 0 || v >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + v + " out of range");
        } else if (w < 0 || w >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + w + " out of range");
        }

        if (adj[v].contains(w)) {
            adj[v].remove(w);
            if (v != w) {
                --E;
            } else {
                --L;
            }
        }
    }

    @Override
    public int degree(int v) {
        if (v < 0 || v >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + v + " out of range");
        }

        return adj[v].size();
    }

    @Override
    public Iterable<Integer> adjVertices(int v) {
        if (v < 0 || v >= adj.length) {
            throw new IllegalArgumentException("Vertex index " + v + " out of range");
        }

        return adj[v];
    }

    @Override
    public int V() {
        return adj.length;
    }

    @Override
    public int E() {
        return E;
    }

    @Override
    public int L() {
        return L;
    }

    @Override
    public DirectedGraph complement() {
        DirectedGraph complement = new DirectedGraph(V());

        for (int i = 0; i < adj.length; ++i) {
            for (int j = 0; j < adj.length; ++j) {
                if (!adj[i].contains(j)) {
                    complement.adj[i].add(j);
                }
            }
        }
        complement.E = E;
        complement.L = L;

        return complement;
    }

    @Override
    public DirectedGraph reverse() {
        DirectedGraph reverse = new DirectedGraph(V());

        for (int i = 0; i < adj.length; ++i) {
            for (int j = 0; j < adj.length; ++j) {
                if (adj[i].contains(j)) {
                    reverse.adj[j].add(i);
                }
            }
        }
        reverse.E = E;
        reverse.L = L;

        return reverse;
    }

    @Override
    public String toString() {
        String representation = "";

        representation += "|V| = " + V() + "\n";
        representation += "|E| = " + E() + "\n";
        representation += "|L| = " + L() + "\n";

        for (int i = 0; i < adj.length; ++i) {
            representation += i + ":";

            for (int j : adj[i]) {
                representation += " " + j;
            }
            representation += "\n";
        }

        return representation;
    }
}
