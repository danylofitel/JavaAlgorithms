package algorithms.graphs.cycles;

import algorithms.graphs.UndirectedGraph;
import lib.StdOut;
import lib.StdRandom;

public class UndirectedHamiltonCycleTest {

    private static final int V = 7;
    private static final int E = (V * (V - 1)) / 3;

    public static void main(String[] args) {
        UndirectedGraph G = new UndirectedGraph(V);
        int[] inDegree = new int[V];
        int[] outDegree = new int[V];
        int deficit = 0;
        while (G.E() < E) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            G.addEdge(v, w);
            if (outDegree[v] >= inDegree[v]) {
                deficit++;
            } else {
                deficit--;
            }
            outDegree[v]++;
            if (inDegree[w] >= outDegree[w]) {
                deficit++;
            } else {
                deficit--;
            }
            inDegree[w]++;
        }

        while (deficit > 0) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            if (v == w) {
                continue;
            }
            if (outDegree[v] >= inDegree[v]) {
                continue;
            }
            if (inDegree[w] >= outDegree[w]) {
                continue;
            }
            G.addEdge(v, w);
            if (outDegree[v] >= inDegree[v]) {
                deficit++;
            } else {
                deficit--;
            }
            outDegree[v]++;
            if (inDegree[w] >= outDegree[w]) {
                deficit++;
            } else {
                deficit--;
            }
            inDegree[w]++;
        }

        StdOut.println(G);
        UndirectedHamiltonCycle hamilton = new UndirectedHamiltonCycle(G);
        if (hamilton.isHamiltonian()) {
            for (int v : hamilton.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        } else {
            StdOut.println("Not hamiltonian");
        }
    }
}
