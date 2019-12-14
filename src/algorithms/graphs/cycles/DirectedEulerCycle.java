package algorithms.graphs.cycles;

import algorithms.graphs.degrees.Degrees;
import algorithms.graphs.DirectedGraph;
import collections.Stack;
import collections.StackOnArrays;
import java.util.Iterator;

public class DirectedEulerCycle {

    private final Stack<Integer> cycle;
    private boolean hasEulerCycle;

    public DirectedEulerCycle(DirectedGraph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        if (!allDegreesFair(G)) {
            cycle = null;
            hasEulerCycle = false;
            return;
        }

        cycle = new StackOnArrays<>();
        hasEulerCycle = true;
        findEulerCycle(G);
    }

    public Iterable<Integer> cycle() {
        if (hasEulerCycle) {
            return cycle;
        } else {
            return null;
        }
    }

    public boolean isEulerian() {
        return hasEulerCycle;
    }

    private static boolean allDegreesFair(DirectedGraph G) {
        Degrees degrees = new Degrees(G);

        for (int i = 0; i < G.V(); ++i) {
            if (degrees.inDegree(i) != degrees.outDegree(i)) {
                return false;
            }
        }

        return true;
    }

    private void findEulerCycle(DirectedGraph G) {
        // Create local view of adjacency lists
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++) {
            adj[v] = G.adjVertices(v).iterator();
        }

        // Find vertex with nonzero degree as start of potential Eulerian cycle
        int s = 0;
        for (int v = 0; v < G.V(); v++) {
            if (adj[v].hasNext()) {
                s = v;
                break;
            }
        }

        // Depth first search
        Stack<Integer> stack = new StackOnArrays<>();
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            cycle.push(v);
            int w = v;
            while (adj[w].hasNext()) {
                stack.push(w);
                w = adj[w].next();
            }
            if (w != v) {
                hasEulerCycle = false;
            }
        }

        for (int v = 0; v < G.V(); v++) {
            if (adj[v].hasNext()) {
                hasEulerCycle = false;
            }
        }
    }
}
