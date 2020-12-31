package chapter2;

import edu.princeton.cs.algs4.In;

public class Selection {

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (Sort.less(a[j], a[min])) {
                    min = j;
                }
            }
            Sort.exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert Sort.isSorted(a);
        Sort.show(a);
    }
}
