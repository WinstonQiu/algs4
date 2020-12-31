package chapter2;

import edu.princeton.cs.algs4.In;

public class Merge {

    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(a, 0, a.length - 1, aux);
    }

    public static void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
        if (lo >= hi)
            return;
        if (hi - lo < 7) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(aux, lo, mid, a);
        sort(aux, mid + 1, hi, a);
        if (!Sort.less(aux[mid + 1], aux[mid])) {
            System.arraycopy(aux, lo, a, lo, hi + 1 - lo);
            return;
        }
        merge(a, lo, mid, hi, aux);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        int i = lo, j = mid + 1;
//        for (int k = lo; k <= hi; k++)
//            aux[k] = a[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (Sort.less(aux[i], aux[j]))
                a[k] = aux[i++];
            else
                a[k] = aux[j++];
        }
    }

    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert Sort.isSorted(a);
        Sort.show(a);
    }
}
