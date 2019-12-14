package algorithms.graphs;

import java.util.Set;
import java.util.TreeSet;
import lib.In;

public class UndirectedGraph implements Graph {

    private final Set<Integer>[] adj;
    private int E;
    private int L;

    public UndirectedGraph(int V) {
        if (V < 0) throw new IllegalArgumentException("The number of vertices can not be negative");

        adj = (Set<Integer>[]) new Set[V];
        for (int v = 0; v < adj.length; v++) {
            adj[v] = new TreeSet<>();
        }
        E = 0;
        L = 0;
    }

    public UndirectedGraph(In in) {
        this(in.readInt());
        for (int i = 0; i < in.readInt(); ++i) {
            int v = in.readInt();
            int w = in.readInt();
            if (!adj[v].contains(w)) {
                adj[v].add(w);
                adj[w].add(v);
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
            adj[w].add(v);
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
            adj[w].remove(v);
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
    public UndirectedGraph complement() {
        UndirectedGraph complement = new UndirectedGraph(V());

        for (int i = 0; i < adj.length; ++i) {
            for (int j = 0; j < adj.length; ++j) {
                if (!adj[i].contains(j)) {
                    complement.addEdge(i, j);
                }
            }
        }
        complement.E = E;
        complement.L = L;

        return complement;
    }

    @Override
    public UndirectedGraph reverse() {
        UndirectedGraph reverse = new UndirectedGraph(V());

        for (int i = 0; i < V(); ++i) {
            for (int j = 0; j < V(); ++j) {
                reverse.addEdge(i, j);
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
