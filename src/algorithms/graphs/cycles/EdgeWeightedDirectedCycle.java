package algorithms.graphs.cycles;

import algorithms.graphs.DirectedEdge;
import algorithms.graphs.EdgeWeightedDirectedGraph;
import collections.Stack;
import collections.StackOnArrays;

public class EdgeWeightedDirectedCycle {

    private final boolean[] marked;         // marked[v] = has vertex v been marked?
    private final DirectedEdge[] edgeTo;    // edgeTo[v] = previous edge on path to v
    private final boolean[] onStack;        // onStack[v] = is vertex on the stack?
    private Stack<DirectedEdge> cycle;      // directed cycle (or null if no such cycle)

    public EdgeWeightedDirectedCycle(EdgeWeightedDirectedGraph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
        assert check(G);
    }

    private void dfs(EdgeWeightedDirectedGraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();

            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new StackOnArrays<>();
                while (e.from() != w) {
                    cycle.push(e);
                    e = edgeTo[e.from()];
                }
                cycle.push(e);
            }
        }

        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

    private boolean check(EdgeWeightedDirectedGraph G) {
        if (hasCycle()) {
            DirectedEdge first = null, last = null;
            for (DirectedEdge e : cycle()) {
                if (first == null) {
                    first = e;
                }
                if (last != null) {
                    if (last.to() != e.from()) {
                        System.err.printf("cycle edges %s and %s not incident\n", last, e);
                        return false;
                    }
                }
                last = e;
            }

            if (last.to() != first.from()) {
                System.err.printf("cycle edges %s and %s not incident\n", last, first);
                return false;
            }
        }

        return true;
    }
}
