package algorithms.graphs.minimumSpanningTree;

import algorithms.graphs.Edge;
import algorithms.graphs.EdgeWeightedUndirectedGraph;
import algorithms.unionFind.UnionFind;
import algorithms.unionFind.WeightedQuickUnionUF;
import collections.MinPriorityQueue;
import collections.Queue;
import collections.QueueOnArray;

public class KruskalMST {

    private static final double PRECISION = 1E-12;

    private double weight;
    private Queue<Edge> mst = new QueueOnArray<>();

    public KruskalMST(EdgeWeightedUndirectedGraph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        MinPriorityQueue<Edge> pq = new MinPriorityQueue<>();
        for (Edge e : G.edges()) {
            pq.pushBack(e);
        }

        UnionFind uf = new WeightedQuickUnionUF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.popFront();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) { // v-w does not create a cycle
                uf.union(v, w);  // merge v and w components
                mst.pushBack(e);  // add edge e to mst
                weight += e.weight();
            }
        }

        // check optimality conditions
        assert check(G);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }

    // check optimality conditions (takes time proportional to E V lg* V)
    private boolean check(EdgeWeightedUndirectedGraph G) {

        // check total weight
        double total = 0.0;
        for (Edge e : edges()) {
            total += e.weight();
        }
        if (Math.abs(total - weight()) > PRECISION) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", total, weight());
            return false;
        }

        // check that it is acyclic
        UnionFind uf = new WeightedQuickUnionUF(G.V());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : G.edges()) {
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest (cut optimality conditions)
        for (Edge e : edges()) {

            // all edges in MST except e
            uf = new WeightedQuickUnionUF(G.V());
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }
}