package algorithms.random;

public class DiscreteDistributionTest {

    public static void main(String[] args) {
        final int size = 5;
        final int iterations = 100000000;

        Integer[] values = new Integer[size];
        double[] probabilities = new double[size];
        int[] results = new int[size];

        double sum = 0.0;

        for (int i = 0; i < size; ++i) {
            values[i] = i;
            probabilities[i] = Math.random();
            results[i] = 0;
            sum += probabilities[i];
        }

        for (int i = 0; i < size; ++i) {
            probabilities[i] /= sum;
        }

        DiscreteRandomValue<Integer> dd = new DiscreteRandomValue<>(values, probabilities);
        for (int i = 0; i < iterations; ++i) {
            ++results[dd.next()];
        }

        System.out.println("Total iterations: " + iterations);
        for (int i = 0; i < size; ++i) {
            System.out.println("p = " + probabilities[i] + ", n = " + results[i]);
        }
    }
}
