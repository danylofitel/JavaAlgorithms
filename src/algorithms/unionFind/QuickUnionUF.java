package algorithms.unionFind;

public class QuickUnionUF implements UnionFind {

    private final int[] id;
    private int count;

    public QuickUnionUF(int n) {
        id = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    @Override
    public void reset() {
        count = id.length;
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        id[pRoot] = qRoot;
        count--;
    }

    private int find(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i : id) {
            str.append(i);
            str.append(" ");
        }
        return str.toString();
    }
}
