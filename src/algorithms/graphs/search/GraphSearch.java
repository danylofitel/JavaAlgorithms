package algorithms.graphs.search;

public interface GraphSearch {

    int start();
    
    int finish();
    
    boolean hasPath();

    Iterable<Integer> path();

    int pathLength();

    int verticesVisited();
}
