package algorithms.graphs.search;

import algorithms.graphs.Graph;
import collections.Stack;
import collections.StackOnArrays;

public class DepthFirstSearch implements GraphSearch {

    private final int start;
    private final int finish;
    private final int[] distTo;
    private final int[] edgeTo;
    private final boolean[] marked;
    private int markedCount;

    public DepthFirstSearch(Graph G, int start, int finish) {
        if (G == null) throw new IllegalArgumentException("Graph is null");
        if (start < 0 || start >= G.V()) throw new IndexOutOfBoundsException("Start vertex does not belong to the graph");
        if (finish < 0 || finish >= G.V()) throw new IndexOutOfBoundsException("Finish vertex does not belong to the graph");

        this.start = start;
        this.finish = finish;
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        markedCount = 0;
        dfs(G, start);
    }

    @Override
    public int start() {
        return start;
    }

    @Override
    public int finish() {
        return finish;
    }

    @Override
    public boolean hasPath() {
        return marked[finish];
    }

    @Override
    public Iterable<Integer> path() {
        if (!hasPath()) {
            return null;
        }
        Stack<Integer> path = new StackOnArrays<>();
        for (int x = finish; x != start; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(start);
        return path;
    }

    @Override
    public int pathLength() {
        return hasPath() ? distTo[finish] : -1;
    }

    @Override
    public int verticesVisited() {
        return markedCount;
    }

    private void dfs(Graph G, int s) {
        Stack<Integer> stack = new StackOnArrays<>();
        stack.push(s);
        marked[s] = true;
        ++markedCount;
        distTo[s] = 0;
        while (!stack.isEmpty() && stack.top() != finish) {
            int v = stack.pop();
            for (int w : G.adjVertices(v)) {
                if (!marked[w]) {
                    stack.push(w);
                    marked[w] = true;
                    ++markedCount;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
                if (w == finish) {
                    return;
                }
            }
        }
    }
}
