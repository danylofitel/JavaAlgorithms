package algorithms.graphs.shortestPaths;

import algorithms.graphs.EdgeWeightedDirectedGraph;
import algorithms.graphs.ProbabilisticEdge;
import algorithms.graphs.ProbabilisticGraph;

public class ProbabilisticGuaranteedSP extends DijkstraSP {

    public ProbabilisticGuaranteedSP(ProbabilisticGraph g, int s) {
        super(shortestGuaranteedPath(g), s);
    }

    private static EdgeWeightedDirectedGraph shortestGuaranteedPath(ProbabilisticGraph g) {
        EdgeWeightedDirectedGraph deterministicGraph = new EdgeWeightedDirectedGraph(g.V());
        ProbabilisticEdge[] minEdges = new ProbabilisticEdge[g.V()];

        for (int v = 0; v < g.V(); ++v) {
            // Clear the array of edges
            for (int i = 0; i < minEdges.length; ++i) {
                minEdges[i] = null;
            }

            // For each vertex find the edge with minimal value
            for (ProbabilisticEdge e : g.adj(v)) {
                ProbabilisticEdge current = minEdges[e.to()];
                if (current == null
                        || current.weight() < e.weight()
                        || (current.weight() == e.weight() && current.probability() < e.probability())) {
                    minEdges[e.to()] = e;
                }
            }

            // Add edges to a non-probabilistic edge-weighted graph
            for (int i = 0; i < minEdges.length; ++i) {
                if (minEdges[i] != null) deterministicGraph.addEdge(minEdges[i]);
            }
        }

        return deterministicGraph;
    }
}
