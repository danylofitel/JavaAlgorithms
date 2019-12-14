package algorithms.graphs;

public class Edge implements Comparable<Edge> {

    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        if (v < 0) throw new IndexOutOfBoundsException("Vertex name must be a non-negative integer");
        if (w < 0) throw new IndexOutOfBoundsException("Vertex name must be a non-negative integer");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");

        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    @Override
    public int compareTo(Edge that) {
        if      (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return +1;
        else                                    return  0;
    }

    @Override
    public boolean equals(Object other) {
        return other != null
                && this.getClass().equals(other.getClass())
                && v == ((Edge) other).v
                && w == ((Edge) other).w;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.v;
        hash = 61 * hash + this.w;
        return hash;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}
