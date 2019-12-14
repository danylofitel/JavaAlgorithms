package algorithms.graphs.search;

import algorithms.graphs.Graph;
import collections.Queue;
import collections.QueueOnArrays;
import collections.Stack;
import collections.StackOnArrays;

public class BreadthFirstSearch implements GraphSearch {

    private final int start;
    private final int finish;
    private final int[] distTo;
    private final int[] edgeTo;
    private final boolean[] marked;
    private int markedCount;

    public BreadthFirstSearch(Graph G, int start, int finish) {
        if (G == null) throw new IllegalArgumentException("Graph is null");
        if (start < 0 || start >= G.V()) throw new IndexOutOfBoundsException("Start vertex does not belong to the graph");
        if (finish < 0 || finish >= G.V()) throw new IndexOutOfBoundsException("Finish vertex does not belong to the graph");

        this.start = start;
        this.finish = finish;
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        markedCount = 0;
        bfs(G, start);
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

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new QueueOnArrays<>();
        queue.pushBack(s);
        marked[s] = true;
        ++markedCount;
        distTo[s] = 0;
        while (!queue.isEmpty() && queue.front() != finish) {
            int v = queue.popFront();
            for (int w : G.adjVertices(v)) {
                if (!marked[w]) {
                    queue.pushBack(w);
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
