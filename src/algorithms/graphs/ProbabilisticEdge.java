package algorithms.graphs;

public class ProbabilisticEdge extends DirectedEdge {

    private final double probability;

    public ProbabilisticEdge(int v, int w, double weight, double probability) {
        super(v, w, weight);
        if (probability < 0.0 || probability > 1.0) {
            throw new IllegalArgumentException("Invalid probability value " + probability);
        }
        this.probability = probability;
    }

    public double probability() {
        return probability;
    }
}
