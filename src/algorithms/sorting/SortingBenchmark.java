package algorithms.sorting;

import lib.StdRandom;
import lib.Stopwatch;

public class SortingBenchmark {

    public static void main(String[] args) {
        final int size = 400000;
        final int iterations = 10;

        Long[] array = new Long[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (long) i;
        }

        System.out.println("Size = " + size + ", iterations = " + iterations);

        //testSort(new BubbleSort(), array, iterations);
        //testSort(new InsertionSort(), array, iterations);
        //testSort(new SelectionSort(), array, iterations);
        testSort(new ShellSort(), array, iterations);
        testSort(new MergeSort(), array, iterations);
        testSort(new HeapSort(), array, iterations);
        testSort(new QuickSort(), array, iterations);
        testSort(new Quick3Sort(), array, iterations);
    }

    private static void testSort(Sort sortMethod, Comparable[] array, int iterations) {
        int fails = 0;

        int elapsedTimeInMilliseconds = 0;

        for (int iteration = 0; iteration < iterations; ++iteration) {
            StdRandom.shuffle(array);

            Stopwatch timer = new Stopwatch();
            sortMethod.sort(array);
            elapsedTimeInMilliseconds += timer.elapsedTime();

            if (!SortingAlgorithms.isSorted(array)) {
                ++fails;
            }
        }

        System.out.println(sortMethod.getClass().getSimpleName()
                + " finished in "
                + elapsedTimeInMilliseconds + " milliseconds with "
                + fails + " fails");
    }
}
