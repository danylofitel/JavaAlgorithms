package algorithms.percolation;

import lib.StdRandom;
import lib.Stopwatch;

public class PercolationStats {

    private double mean;
    private double stdev;

    public PercolationStats(int dimension, int tests) {
        run(dimension, tests);
    }

    private void run(int dimension, int tests) {
        final int nodes = dimension * dimension;
        mean = 0;
        stdev = 0;

        Percolation percolation = new Percolation(dimension);

        // Results of each experiment
        double[] result = new double[tests];
        for (int i = 0; i < result.length; ++i) {
            result[i] = 0;
        }

        // Create a random sequence of indexes
        int[] randomSequence = new int[nodes];
        for (int i = 0; i < nodes; ++i) {
            randomSequence[i] = i;
        }

        System.out.println("Calculating...");
        Stopwatch timer = new Stopwatch();

        // Run all tests
        for (int test = 0; test < tests; ++test) {
            // Reset the cluster
            percolation.reset();

            // Randomize the sequence of indexes
            StdRandom.shuffle(randomSequence);

            // Randomly open nodes until the system percolates
            int i = 0;
            while (!percolation.percolates()) {
                int next = randomSequence[i++];
                percolation.open(next / dimension, next % dimension);
            }

            // Get result of current experiment
            result[test] = ((double) percolation.getOpenedCount()) / ((double) nodes);

            // Update the sum of results
            mean += result[test];
        }

        // Calculate mean value
        mean /= tests;

        // Calculate standard deviation
        for (int i = 0; i < tests; ++i) {
            stdev += Math.pow(result[i] - mean, 2);
        }
        stdev /= tests;
        stdev = Math.sqrt(stdev);

        System.out.println("Done in " + timer.elapsedTime() + " milliseconds");
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stdev;
    }

    public static void main(String[] args) {
        final int dimension = 250;
        final int tests = 100;

        System.out.println("Running percolation test");
        System.out.println("Dimension = " + dimension);
        System.out.println("Number of tests = " + tests);

        PercolationStats stats = new PercolationStats(dimension, tests);
        final double left = stats.mean() - stats.stddev();
        final double right = stats.mean() + stats.stddev();

        System.out.println("Mean = " + stats.mean());
        System.out.println("Standard deviation = " + stats.stddev());
        System.out.println("95% confidence interval = [" + left + ", " + right + "]");
    }
}
