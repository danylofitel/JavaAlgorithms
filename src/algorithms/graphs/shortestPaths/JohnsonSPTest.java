package algorithms.graphs.shortestPaths;

import algorithms.graphs.DirectedEdge;
import algorithms.graphs.EdgeWeightedDirectedGraph;

public class JohnsonSPTest {
    public static void main(String[] args) {
        int V = 6;
        EdgeWeightedDirectedGraph graph = new EdgeWeightedDirectedGraph(V);
        graph.addEdge(new DirectedEdge(0, 1, -2));
        graph.addEdge(new DirectedEdge(1, 2, -1));
        graph.addEdge(new DirectedEdge(2, 0, 4));
        graph.addEdge(new DirectedEdge(2, 3, 2));
        graph.addEdge(new DirectedEdge(2, 4, -3));
        graph.addEdge(new DirectedEdge(5, 3, 1));
        graph.addEdge(new DirectedEdge(5, 4, -4));

        JohnsonSP sp = new JohnsonSP(graph);

        for (int from = 0; from < V; ++from) {
            for (int to = 0; to < V; ++to) {
                boolean printPath = from != to && sp.hasPathTo(from, to);
                if (printPath) {
                    System.out.println(from + " -> " + to + ": " + sp.distTo(from, to));
                }
            }
        }
    }
}
