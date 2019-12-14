package algorithms.sorting;

import java.util.Comparator;

public class SortingAlgorithms {

    public static boolean isSorted(Comparable[] a) {
        return Sort.isSorted(a, 0, a.length);
    }

    public static boolean isSorted(Object[] a, Comparator c) {
        return Sort.isSorted(a, c, 0, a.length);
    }

    public static void bubbleSort(Comparable[] a) {
        Sort sort = new BubbleSort();
        sort.sort(a);
    }

    public static void bubbleSort(Object[] a, Comparator c) {
        Sort sort = new BubbleSort();
        sort.sort(a, c);
    }

    public static void insertionSort(Comparable[] a) {
        Sort sort = new InsertionSort();
        sort.sort(a);
    }

    public static void insertionSort(Object[] a, Comparator c) {
        Sort sort = new InsertionSort();
        sort.sort(a, c);
    }

    public static void selectionSort(Comparable[] a) {
        Sort sort = new SelectionSort();
        sort.sort(a);
    }

    public static void selectionSort(Object[] a, Comparator c) {
        Sort sort = new SelectionSort();
        sort.sort(a, c);
    }

    public static void shellSort(Comparable[] a) {
        Sort sort = new ShellSort();
        sort.sort(a);
    }

    public static void shellSort(Object[] a, Comparator c) {
        Sort sort = new ShellSort();
        sort.sort(a, c);
    }

    public static void mergeSort(Comparable[] a) {
        Sort sort = new MergeSort();
        sort.sort(a);
    }

    public static void mergeSort(Object[] a, Comparator c) {
        Sort sort = new MergeSort();
        sort.sort(a, c);
    }

    public static void heapSort(Comparable[] a) {
        Sort sort = new HeapSort();
        sort.sort(a);
    }

    public static void heapSort(Object[] a, Comparator c) {
        Sort sort = new HeapSort();
        sort.sort(a, c);
    }

    public static void quickSort(Comparable[] a) {
        Sort sort = new QuickSort();
        sort.sort(a);
    }

    public static void quickSort(Object[] a, Comparator c) {
        Sort sort = new QuickSort();
        sort.sort(a, c);
    }

    public static void quick3Sort(Comparable[] a) {
        Sort sort = new Quick3Sort();
        sort.sort(a);
    }

    public static void quick3Sort(Object[] a, Comparator c) {
        Sort sort = new Quick3Sort();
        sort.sort(a, c);
    }
}
