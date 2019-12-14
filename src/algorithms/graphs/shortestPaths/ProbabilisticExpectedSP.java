package algorithms.graphs.shortestPaths;

import algorithms.graphs.DirectedEdge;
import algorithms.graphs.EdgeWeightedDirectedGraph;
import algorithms.graphs.ProbabilisticEdge;
import algorithms.graphs.ProbabilisticGraph;

public class ProbabilisticExpectedSP extends DijkstraSP {

    public ProbabilisticExpectedSP(ProbabilisticGraph g, int s) {
        super(shortestExpectedPath(g), s);
    }

    private static EdgeWeightedDirectedGraph shortestExpectedPath(ProbabilisticGraph g) {
        EdgeWeightedDirectedGraph deterministicGraph = new EdgeWeightedDirectedGraph(g.V());
        Double[] minEdges = new Double[g.V()];

        for (int v = 0; v < g.V(); ++v) {
            // Clear the array of edges
            for (int i = 0; i < minEdges.length; ++i) {
                minEdges[i] = null;
            }

            // For each vertex calculate the expected weight of edges to it
            for (ProbabilisticEdge e : g.adj(v)) {
                Double current = minEdges[e.to()];
                if (minEdges[e.to()] == null) {
                    minEdges[e.to()] = 0.0;
                }
                minEdges[e.to()] += e.weight() * e.probability();
            }

            // Add edges to a non-probabilistic edge-weighted graph
            for (int i = 0; i < minEdges.length; ++i) {
                if (minEdges[i] != null) {
                    deterministicGraph.addEdge(new DirectedEdge(v, i, minEdges[i]));
                }
            }
        }

        return deterministicGraph;
    }
}
