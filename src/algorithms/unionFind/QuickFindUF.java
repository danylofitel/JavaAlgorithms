package algorithms.unionFind;

public class QuickFindUF implements UnionFind {

    private final int[] id;
    private int count;

    public QuickFindUF(int n) {
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
        return id[p] == id[q];
    }

    @Override
    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        if (pid == qid) {
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
        count--;
    }

    private int find(int p) {
        return id[p];
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
