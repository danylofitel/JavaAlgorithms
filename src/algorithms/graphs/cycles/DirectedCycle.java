package algorithms.graphs.cycles;

import algorithms.graphs.DirectedGraph;
import collections.Stack;
import collections.StackOnArrays;

public class DirectedCycle {

    private final boolean[] marked;     // marked[v] = has vertex v been marked?
    private final int[] edgeTo;         // edgeTo[v] = previous vertex on path to v
    private final boolean[] onStack;    // onStack[v] = is vertex on the stack?
    private Stack<Integer> cycle;       // directed cycle (or null if no such cycle)

    public DirectedCycle(DirectedGraph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(DirectedGraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adjVertices(v)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new StackOnArrays<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }

        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    private boolean check(DirectedGraph G) {
        if (hasCycle()) {
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) {
                    first = v;
                }
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle begins with %d and ends with %d\n", first, last);
                return false;
            }
        }

        return true;
    }
}
