package algorithms.graphs.paths;

import algorithms.graphs.Graph;
import collections.Queue;
import collections.QueueOnArrays;
import collections.Stack;
import collections.StackOnArrays;
import collections.Vector;

public class BreadthFirstPaths implements GraphPaths {

    private final Iterable<Integer> start;
    private final int[] distTo;
    private final int[] edgeTo;
    private final boolean[] marked;

    public BreadthFirstPaths(Graph G, int s) {
        if (G == null) throw new IllegalArgumentException("Graph is null");
        if (s < 0 || s >= G.V()) throw new IndexOutOfBoundsException("Start vertex does not belong to the graph");

        Vector<Integer> v = new Vector<>();
        v.addLast(s);
        this.start = v;
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        bfs(G, s);
    }

    public BreadthFirstPaths(Graph G, Iterable<Integer> s) {
        if (G == null) throw new IllegalArgumentException("Graph is null");
        if (s == null) throw new IllegalArgumentException("Start vertices are null");

        this.start = s;
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        bfs(G, s);
    }

    @Override
    public Iterable<Integer> start() {
        return start;
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new StackOnArrays<>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x);
        return path;
    }

    @Override
    public int pathLengthTo(int v) {
        return hasPathTo(v) ? distTo[v] : -1;
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new QueueOnArrays<>();
        queue.pushBack(s);
        marked[s] = true;
        distTo[s] = 0;
        while (!queue.isEmpty()) {
            int v = queue.popFront();
            for (int w : G.adjVertices(v)) {
                if (!marked[w]) {
                    queue.pushBack(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    private void bfs(Graph G, Iterable<Integer> sources) {
        Queue<Integer> q = new QueueOnArrays<>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.pushBack(s);
        }
        while (!q.isEmpty()) {
            int v = q.popFront();
            for (int w : G.adjVertices(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.pushBack(w);
                }
            }
        }
    }
}
