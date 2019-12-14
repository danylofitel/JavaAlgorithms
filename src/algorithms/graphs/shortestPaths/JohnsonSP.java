package algorithms.graphs.shortestPaths;

import algorithms.graphs.DirectedEdge;
import algorithms.graphs.EdgeWeightedDirectedGraph;

public class JohnsonSP {

    private final DijkstraSP[] shortestPaths;

    public JohnsonSP(EdgeWeightedDirectedGraph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        int vertices = G.V();
        shortestPaths = new DijkstraSP[vertices];

        EdgeWeightedDirectedGraph graph = getExtendedGraph(G);
        reWeightGraph(graph);

        for (int i = 0; i < vertices; ++i) {
            shortestPaths[i] = new DijkstraSP(graph, i);
        }
    }

    public double distTo(int v, int w) {
        return shortestPaths[v].distTo(w);
    }

    public boolean hasPathTo(int v, int w) {
        return shortestPaths[v].hasPathTo(w);
    }

    public Iterable<DirectedEdge> pathTo(int v, int w) {
        return shortestPaths[v].pathTo(w);
    }

    private static EdgeWeightedDirectedGraph getExtendedGraph(EdgeWeightedDirectedGraph G) {
        int vertices = G.V();
        EdgeWeightedDirectedGraph graph = new EdgeWeightedDirectedGraph(vertices + 1);

        for (DirectedEdge edge : G.edges()) {
            graph.addEdge(edge);
        }

        for (int i = 0; i < vertices; ++i) {
            graph.addEdge(new DirectedEdge(vertices, i, 0.0));
        }

        return graph;
    }

    private static void reWeightGraph(EdgeWeightedDirectedGraph graph) {
        BellmanFordSP sp = new BellmanFordSP(graph, graph.V() - 1);

        for (DirectedEdge edge : graph.edges()) {
            double newWeight = edge.weight() + sp.distTo(edge.from()) - sp.distTo(edge.to());
            graph.removeEdge(edge);
            graph.addEdge(new DirectedEdge(edge.from(), edge.to(), newWeight));
        }
    }
}
