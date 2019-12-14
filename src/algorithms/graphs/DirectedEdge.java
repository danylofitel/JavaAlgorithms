package algorithms.graphs;

public class DirectedEdge implements Comparable<DirectedEdge> {

    private final int v;
    private final int w;
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }

    @Override
    public int compareTo(DirectedEdge that) {
        if      (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return +1;
        else                                    return  0;
    }

    @Override
    public boolean equals(Object other) {
        return other != null
                && this.getClass().equals(other.getClass())
                && v == ((DirectedEdge) other).v
                && w == ((DirectedEdge) other).w;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.v;
        hash = 61 * hash + this.w;
        return hash;
    }
}
