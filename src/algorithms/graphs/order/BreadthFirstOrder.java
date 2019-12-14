package algorithms.graphs.order;

import algorithms.graphs.DirectedEdge;
import algorithms.graphs.EdgeWeightedDirectedGraph;
import algorithms.graphs.Graph;
import collections.Deque;
import collections.DequeOnArrays;
import collections.Queue;
import collections.QueueOnArrays;

public class BreadthFirstOrder implements GraphOrder {

    private final boolean[] marked;
    private final Deque<Integer> post;
    private final Deque<Integer> reversePost;

    public BreadthFirstOrder(Graph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        marked = new boolean[G.V()];
        post = new DequeOnArrays<>();
        reversePost = new DequeOnArrays<>();
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                bfs(G, v);
            }
        }
    }

    public BreadthFirstOrder(EdgeWeightedDirectedGraph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        marked = new boolean[G.V()];
        post = new DequeOnArrays<>();
        reversePost = new DequeOnArrays<>();
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                bfs(G, v);
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

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new QueueOnArrays<>();
        queue.pushBack(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.popFront();
            post.addLast(v);
            reversePost.addFirst(v);
            for (int w : G.adjVertices(v)) {
                if (!marked[w]) {
                    queue.pushBack(w);
                    marked[w] = true;
                }
            }
        }
    }

    private void bfs(EdgeWeightedDirectedGraph G, int s) {
        Queue<Integer> queue = new QueueOnArrays<>();
        queue.pushBack(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.popFront();
            post.addLast(v);
            reversePost.addFirst(v);
            for (DirectedEdge e : G.adj(v)) {
                int w = e.to();
                if (!marked[w]) {
                    queue.pushBack(w);
                    marked[w] = true;
                }
            }
        }
    }
}
