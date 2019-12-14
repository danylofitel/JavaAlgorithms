package algorithms.graphs.connections;

import algorithms.graphs.DirectedGraph;
import algorithms.graphs.order.DepthFirstOrder;

public class StrongConnectionCount {

    private final boolean marked[];
    private final int[] id;
    private int count;

    public StrongConnectionCount(DirectedGraph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());
        for (int v : dfs.reversePostOrder()) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    private void dfs(DirectedGraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adjVertices(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }
}
