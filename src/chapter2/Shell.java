package chapter2;

import edu.princeton.cs.algs4.In;

public class Shell {

    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                Comparable t = a[i];
                int j;
                for (j = i; j >= h && Sort.less(t, a[j - h]); j -= h) {
                    a[j] = a[j - h];
                }
                a[j] = t;
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert Sort.isSorted(a);
        Sort.show(a);
    }
}
