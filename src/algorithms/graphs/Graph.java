package algorithms.graphs;

public interface Graph {

    boolean hasEdge(int v, int w);

    void addEdge(int v, int w);

    void removeEdge(int v, int w);

    int degree(int v);

    Iterable<Integer> adjVertices(int v);

    int V();

    int E();

    int L();

    Graph complement();

    Graph reverse();
}
