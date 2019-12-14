package labyrinth;

import algorithms.graphs.search.BreadthFirstSearch;
import algorithms.graphs.search.DepthFirstSearch;
import algorithms.graphs.Graph;
import algorithms.graphs.search.GraphSearch;

public class Labyrinth {

    private final int start;
    private final int finish;
    private final GraphSearch dfs;
    private final GraphSearch bfs;

    public Labyrinth(Graph G, int start) {
        this.start = start;
        this.finish = (int) (Math.random() * G.V());
        dfs = new DepthFirstSearch(G, start, finish);
        bfs = new BreadthFirstSearch(G, start, finish);
    }

    public int start() {
        return start;
    }

    public int finish() {
        return finish;
    }

    public GraphSearch dfs() {
        return dfs;
    }

    public GraphSearch bfs() {
        return bfs;
    }
}
