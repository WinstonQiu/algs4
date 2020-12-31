package chapter2;

import edu.princeton.cs.algs4.In;

public class Insertion {

    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            Comparable t = a[i];
            int j;
            for (j = i; j > 0 && Sort.less(t, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = t;
        }
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            Comparable t = a[i];
            int j;
            for (j = i; j > lo && Sort.less(t, a[j - 1]); j--)
                a[j] = a[j - 1];
            a[j] = t;
        }
    }

    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert Sort.isSorted(a);
        Sort.show(a);
    }
}
