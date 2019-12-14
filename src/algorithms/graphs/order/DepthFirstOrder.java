package algorithms.graphs.order;

import algorithms.graphs.DirectedEdge;
import algorithms.graphs.EdgeWeightedDirectedGraph;
import algorithms.graphs.Graph;
import collections.Deque;
import collections.DequeOnArrays;

public class DepthFirstOrder implements GraphOrder {

    private final boolean[] marked;
    private final Deque<Integer> post;
    private final Deque<Integer> reversePost;

    public DepthFirstOrder(Graph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        marked = new boolean[G.V()];
        post = new DequeOnArrays<>();
        reversePost = new DequeOnArrays<>();
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    public DepthFirstOrder(EdgeWeightedDirectedGraph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        marked = new boolean[G.V()];
        post = new DequeOnArrays<>();
        reversePost = new DequeOnArrays<>();
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    @Override
    public Iterable<Integer> postOrder() {
        return post;
    }

    @Override
    public Iterable<Integer> reversePostOrder() {
        return reversePost;
    }

    @Override
    public int orderCount() {
        return reversePost.size();
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        post.addLast(v);
        reversePost.addFirst(v);
        for (int w : G.adjVertices(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    private void dfs(EdgeWeightedDirectedGraph G, int v) {
        marked[v] = true;
        post.addLast(v);
        reversePost.addFirst(v);
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }
}
