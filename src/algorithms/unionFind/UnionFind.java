package algorithms.unionFind;

public interface UnionFind {

    void reset();

    int count();

    boolean connected(int p, int q);

    void union(int p, int q);
}
