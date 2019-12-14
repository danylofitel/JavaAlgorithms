package algorithms.graphs.paths;

public interface GraphPaths {

    Iterable<Integer> start();

    boolean hasPathTo(int v);

    Iterable<Integer> pathTo(int v);

    int pathLengthTo(int v);
}
