package algorithms.sorting;

import java.util.Comparator;
import lib.StdRandom;

public class QuickSort extends Sort {

    private final static int CUTOFF = 10;

    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    @Override
    public void sort(Object[] a, Comparator c) {
        StdRandom.shuffle(a);
        sort(a, c, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {
            InsertionSort.sort(a, lo, hi);
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static void sort(Object[] a, Comparator c, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {
            InsertionSort.sort(a, c, lo, hi);
            return;
        }
        int j = partition(a, c, lo, hi);
        sort(a, c, lo, j - 1);
        sort(a, c, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo])) {
                if (i == hi) {
                    break;
                }
            }
            while (less(a[lo], a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static int partition(Object[] a, Comparator c, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo], c)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(a[lo], a[--j], c)) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
}
