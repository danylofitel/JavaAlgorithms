package algorithms.sorting;

import java.util.Comparator;

public class BubbleSort extends Sort {

    @Override
    public void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    @Override
    public void sort(Object[] a, Comparator c) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1], c)) {
                    exch(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}
