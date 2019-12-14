package algorithms.graphs.connections;

import algorithms.graphs.Graph;

public class ConnectionCount {

    private final boolean[] marked;
    private final int[] id;
    private int count;

    public ConnectionCount(Graph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adjVertices(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }
}
