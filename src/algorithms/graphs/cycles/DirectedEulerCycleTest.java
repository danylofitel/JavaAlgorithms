package algorithms.graphs.cycles;

import algorithms.graphs.DirectedGraph;
import lib.StdOut;
import lib.StdRandom;

public class DirectedEulerCycleTest {

    private static final int V = 4;
    private static final int E = (V * (V - 1)) / 2;

    public static void main(String[] args) {
        DirectedGraph G = new DirectedGraph(V);
        int[] indegree = new int[V];
        int[] outdegree = new int[V];
        int deficit = 0;
        for (int i = 0; G.E() < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            G.addEdge(v, w);
            if (outdegree[v] >= indegree[v]) {
                deficit++;
            } else {
                deficit--;
            }
            outdegree[v]++;
            if (indegree[w] >= outdegree[w]) {
                deficit++;
            } else {
                deficit--;
            }
            indegree[w]++;
        }

        while (deficit > 0) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            if (v == w) {
                continue;
            }
            if (outdegree[v] >= indegree[v]) {
                continue;
            }
            if (indegree[w] >= outdegree[w]) {
                continue;
            }
            G.addEdge(v, w);
            if (outdegree[v] >= indegree[v]) {
                deficit++;
            } else {
                deficit--;
            }
            outdegree[v]++;
            if (indegree[w] >= outdegree[w]) {
                deficit++;
            } else {
                deficit--;
            }
            indegree[w]++;
        }

        StdOut.println(G);
        DirectedEulerCycle euler = new DirectedEulerCycle(G);
        if (euler.isEulerian()) {
            for (int v : euler.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        } else {
            StdOut.println("Not eulerian");
        }
    }
}
