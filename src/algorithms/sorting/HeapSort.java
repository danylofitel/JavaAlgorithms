package algorithms.sorting;

import java.util.Comparator;

public class HeapSort extends Sort {

    @Override
    public void sort(Comparable[] a) {
        int n = a.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }
        while (n > 1) {
            exch(a, 0, n - 1);
            sink(a, 1, --n);
        }
    }

    @Override
    public void sort(Object[] a, Comparator c) {
        int n = a.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(a, c, k, n);
        }
        while (n > 1) {
            exch(a, 0, n - 1);
            sink(a, c, 1, --n);
        }
    }

    private static void sink(Comparable[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(a[j - 1], a[j])) {
                j++;
            }
            if (!less(a[k - 1], a[j - 1])) {
                break;
            }
            exch(a, k - 1, j - 1);
            k = j;
        }
    }

    private static void sink(Object[] a, Comparator c, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(a[j - 1], a[j], c)) {
                j++;
            }
            if (!less(a[k - 1], a[j - 1], c)) {
                break;
            }
            exch(a, k - 1, j - 1);
            k = j;
        }
    }
}
