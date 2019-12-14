package algorithms.graphs.paths;

import algorithms.graphs.Graph;
import collections.*;

public class DepthFirstPaths implements GraphPaths {

    private final Iterable<Integer> start;
    private final int[] distTo;
    private final int[] edgeTo;
    private final boolean[] marked;

    public DepthFirstPaths(Graph G, int s) {
        if (G == null) throw new IllegalArgumentException("Graph is null");
        if (s < 0 || s >= G.V()) throw new IndexOutOfBoundsException("Start vertex does not belong to the graph");

        Vector<Integer> v = new Vector<>();
        v.addLast(s);
        start = v;
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DepthFirstPaths(Graph G, Iterable<Integer> s) {
        if (G == null) throw new IllegalArgumentException("Graph is null");
        if (s == null) throw new IllegalArgumentException("Start vertices are null");

        start = s;
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
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

    private void dfs(Graph G, int s) {
        Stack<Integer> stack = new StackOnArrays<>();
        stack.push(s);
        marked[s] = true;
        distTo[s] = 0;
        while (!stack.isEmpty()) {
            int v = stack.pop();
            for (int w : G.adjVertices(v)) {
                if (!marked[w]) {
                    stack.push(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    private void dfs(Graph G, Iterable<Integer> sources) {
        Stack<Integer> stack = new StackOnArrays<>();
        for (int s : sources) {
            stack.push(s);
            marked[s] = true;
            distTo[s] = 0;
        }
        while (!stack.isEmpty()) {
            int v = stack.pop();
            for (int w : G.adjVertices(v)) {
                if (!marked[w]) {
                    stack.push(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }
}
