package chapter2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

public class QuickFast3way {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + 7) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int i = lo, j = hi + 1;
        int p = lo, q = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (Sort.less(a[++i], v))
                if (i == hi)
                    break;
            while (Sort.less(v, a[--j]))
                if (j == lo)
                    break;

            if (i == j && Sort.equal(a[i], v))
                Sort.exch(a, ++p, i);
            if (i >= j)
                break;

            Sort.exch(a, i, j);
            if (Sort.equal(a[i], v))
                Sort.exch(a, ++p, i);
            if (Sort.equal(a[j], v))
                Sort.exch(a, j, --q);
        }

        i = j + 1;
        for (int k = lo; k <= p; k++)
            Sort.exch(a, k, j--);
        for (int k = hi; k >= q; k--)
            Sort.exch(a, k, i++);

        sort(a, lo, j);
        sort(a, i, hi);
    }

    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert Sort.isSorted(a);
        Sort.show(a);
    }
}
