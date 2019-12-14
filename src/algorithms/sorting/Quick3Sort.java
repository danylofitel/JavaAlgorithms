package algorithms.sorting;

import java.util.Comparator;
import lib.StdRandom;

public class Quick3Sort extends Sort {

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
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static void sort(Object[] a, Comparator c, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {
            InsertionSort.sort(a, c, lo, hi);
            return;
        }
        int lt = lo, gt = hi;
        Object v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = c.compare(a[i], v);
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, c, lo, lt - 1);
        sort(a, c, gt + 1, hi);
    }
}
