package algorithms.graphs.paths;

import algorithms.graphs.Graph;
import algorithms.graphs.UndirectedGraph;
import algorithms.graphs.paths.BreadthFirstPaths;
import algorithms.graphs.paths.GraphPaths;
import lib.In;
import lib.StdOut;

public class GraphPathsTest {

    private static final String testFile = "tinyCG.txt";

    public static void main(String[] args) {
        In in = new In(testFile);
        Graph G = new UndirectedGraph(in);
        StdOut.println(G);
        int s = Integer.parseInt("0");
        GraphPaths gp = new BreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (gp.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : gp.pathTo(v)) {
                    if (x == s) {
                        StdOut.print(x);
                    } else {
                        StdOut.print("-" + x);
                    }
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }
        }
    }
}
