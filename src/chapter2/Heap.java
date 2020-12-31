package chapter2;

import edu.princeton.cs.algs4.In;

public class Heap {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N / 2 - 1; k >= 0; k--) {
            sink(a, k, N);
        }
        while (--N > 0) {
            Sort.exch(a, 0, N);
            sink(a, 0, N);
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k + 1 < N) {
            int j = 2 * k + 1;
            if (j + 1 < N && Sort.less(a[j], a[j + 1]))
                j++;
            if (!Sort.less(a[k], a[j]))
                break;
            Sort.exch(a, k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert Sort.isSorted(a);
        Sort.show(a);
    }
}
