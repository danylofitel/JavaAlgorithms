package algorithms.graphs.topological;

import algorithms.graphs.DirectedGraph;
import algorithms.graphs.cycles.EdgeWeightedDirectedCycle;
import algorithms.graphs.EdgeWeightedDirectedGraph;
import algorithms.graphs.cycles.DirectedCycle;
import algorithms.graphs.order.DepthFirstOrder;

public class Topological {

    private Iterable<Integer> order;

    public Topological(DirectedGraph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        DirectedCycle finder = new DirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePostOrder();
        }
    }

    public Topological(EdgeWeightedDirectedGraph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePostOrder();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean hasOrder() {
        return order != null;
    }
}
