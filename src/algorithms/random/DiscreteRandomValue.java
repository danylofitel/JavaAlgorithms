package algorithms.random;

public class DiscreteRandomValue<Value> {

    private static final double precision = 0.00001;
    private final int size;
    private final Value[] values;
    private final double[] probabilityDensity;
    private final double[] probabilityCumulative;

    public DiscreteRandomValue(Value[] values, double[] probabilities) {
        if (!checkDistribution(probabilities)) {
            throw new IllegalArgumentException("Invalid probabilities");
        }

        this.size = values.length;
        this.values = values;
        this.probabilityDensity = probabilities;
        this.probabilityCumulative = new double[probabilityDensity.length];

        probabilityCumulative[0] = probabilityDensity[0];
        for (int i = 1; i < size - 1; ++i) {
            probabilityCumulative[i] = probabilityCumulative[i - 1] + probabilityDensity[i];
        }
        probabilityCumulative[size - 1] = 1.0;
    }

    public Value next() {
        return values[rank(Math.random())];
    }

    private boolean checkDistribution(double[] probabilities) {
        double sum = 0.0;
        for (double p : probabilities) {
            if (p < 0.0) {
                return false;
            }
            sum += p;
        }

        return Math.abs(sum - 1.0) <= precision;
    }

    private int rank(double val) {
        int lo = 0;
        int hi = size;
        while (hi - lo > 1) {
            int mid = lo + (hi - lo) / 2;
            if (val >= probabilityCumulative[mid - 1]) {
                lo = mid;
            } else if (val < probabilityCumulative[mid]) {
                hi = mid;
            }
        }
        return lo;
    }
}
