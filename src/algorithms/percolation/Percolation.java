package algorithms.percolation;

import algorithms.unionFind.WeightedQuickUnionUF;
import algorithms.unionFind.UnionFind;

public class Percolation {

    private final int dimension;
    private final int nodes;
    private final UnionFind cluster;
    private final boolean[] opened;
    private int openedCount;

    public Percolation(int n) {
        dimension = n;
        nodes = dimension * dimension;
        cluster = new WeightedQuickUnionUF(nodes + 2);
        opened = new boolean[nodes];
        for (int i = 0; i < opened.length; ++i) {
            opened[i] = false;
        }
        openedCount = 0;
        initialize();
    }

    private void initialize() {
        int top = 0;
        int bottom = nodes + 1;

        for (int i = 0; i < dimension; ++i) {
            cluster.union(top, i);
            cluster.union(bottom, nodes - i);
        }
    }

    public void reset() {
        cluster.reset();
        initialize();
        for (int i = 0; i < opened.length; ++i) {
            opened[i] = false;
        }
        openedCount = 0;
    }

    public int getOpenedCount() {
        return openedCount;
    }

    public void open(int i, int j) {
        final int index = i * dimension + j;

        if (!opened[index]) {
            opened[index] = true;
            ++openedCount;

            if (i > 0) {
                if (opened[index - dimension]) {
                    cluster.union(index + 1, index - dimension + 1);
                }
            }
            if (i < dimension - 1) {
                if (opened[index + dimension]) {
                    cluster.union(index + 1, index + dimension + 1);
                }
            }
            if (j > 0) {
                if (opened[index - 1]) {
                    cluster.union(index + 1, index);
                }
            }
            if (j < dimension - 1) {
                if (opened[index + 1]) {
                    cluster.union(index + 1, index + 2);
                }
            }
        }
    }

    public boolean isOpened(int i, int j) {
        return opened[i * dimension + j];
    }

    public boolean percolates() {
        return cluster.connected(0, nodes + 1);
    }
}
