package algorithms.graphs.shortestPaths;

import algorithms.graphs.DirectedEdge;
import algorithms.graphs.EdgeWeightedDirectedGraph;
import algorithms.graphs.topological.Topological;

public class AcyclicSP {

    private final double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private final DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path

    public AcyclicSP(EdgeWeightedDirectedGraph G, int s) {
        if (G == null) throw new IllegalArgumentException("Graph is null");
        if (s < 0 || s >= G.V()) throw new IndexOutOfBoundsException("Start vertex does not belong to the graph");

        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Topological topological = new Topological(G);
        for (int v : topological.order()) {
            for (DirectedEdge e : G.adj(v)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }
}
