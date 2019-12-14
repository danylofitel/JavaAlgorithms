package algorithms.sorting;

import java.util.Comparator;

public abstract class Sort {

    public abstract void sort(Comparable[] array);

    public abstract void sort(Object[] a, Comparator c);

    public static boolean isSorted(Comparable[] a, int l, int m) {
        for (int i = l + 1; i < m; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSorted(Object[] a, Comparator c, int l, int m) {
        for (int i = l + 1; i < m; i++) {
            if (less(a[i], a[i - 1], c)) {
                return false;
            }
        }
        return true;
    }

    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected static boolean less(Object v, Object w, Comparator c) {
        return c.compare(v, w) < 0;
    }

    protected static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
