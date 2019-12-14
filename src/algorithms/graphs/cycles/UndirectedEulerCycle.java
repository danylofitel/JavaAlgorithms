package algorithms.graphs.cycles;

import algorithms.graphs.UndirectedGraph;
import collections.Stack;
import collections.StackOnArrays;
import java.util.HashSet;
import java.util.Set;

public class UndirectedEulerCycle {

    private final Stack<Integer> cycle;
    private final boolean hasEulerCycle;
    private Set<Integer>[] visited;
    private int visitedCount;

    public UndirectedEulerCycle(UndirectedGraph G) {
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

    private static boolean allDegreesFair(UndirectedGraph G) {
        for (int i = 0; i < G.V(); ++i) {
            if ((G.degree(i) - (G.hasEdge(i, i) ? 1 : 0)) % 2 == 1) {
                return false;
            }
        }

        return true;
    }

    private void findEulerCycle(UndirectedGraph G) {
        // Visited edjes
        visited = (Set<Integer>[]) new HashSet[G.E()];
        for (int i = 0; i < visited.length; ++i) {
            visited[i] = new HashSet<>();
        }
        visitedCount = 0;

        // Find vertex with nonzero outDegree as start of potential Eulerian cycle
        int s = 0;
        for (int v = 0; v < G.V(); v++) {
            if (G.degree(v) > 0) {
                s = v;
                break;
            }
        }

        cycle.push(s);
        dfs(G, s);
        assert cycle.top().equals(s);
    }

    private void dfs(UndirectedGraph G, int s) {
        for (int i : G.adjVertices(s)) {
            if (visitedCount == G.E()) {
                return;
            } else if (i == s) {
                continue;
            }

            if (!visited[i].contains(s)) {
                visited[i].add(s);
                visited[s].add(i);
                ++visitedCount;
                cycle.push(i);
                dfs(G, i);
                if (visitedCount < G.E()) {
                    cycle.pop();
                    --visitedCount;
                    visited[i].remove(s);
                    visited[s].remove(i);
                }
            }
        }
    }
}
