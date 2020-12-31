package chapter2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare {
    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion"))
            Insertion.sort(a);
        if (alg.equals("Selection"))
            Selection.sort(a);
        if (alg.equals("Shell"))
            Shell.sort(a);
        if (alg.equals("Merge"))
            Merge.sort(a);
        if (alg.equals("MergeBU"))
            Merge.sort(a);
        if (alg.equals("Quick"))
            Quick.sort(a);
        if (alg.equals("Quick3way"))
            Quick3way.sort(a);
        if (alg.equals("QuickFast3way"))
            QuickFast3way.sort(a);
        if (alg.equals("Heap"))
            Heap.sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
//        Integer[] a = new Integer[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
//                a[i] = StdRandom.uniform(10);
            }
            total += time(alg, a);
            assert Sort.isSorted(a);
        }
        return total;
    }

    public static double timeOrderedInput(String alg, int N, int T) {
        double total = 0.0;
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++) {
            a[i] = i;
        }
        for (int t = 0; t < T; t++) {
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        StdOut.printf("For %d random Doubles:\n", N);
        StdOut.printf("    %s uses %.4f s.\n", alg1, t1);
        StdOut.printf("    %s uses %.4f s.\n", alg2, t2);
        StdOut.printf("    %s is %.1f times faster than %s.\n", alg1, t2 / t1, alg2);

//        double t3 = timeOrderedInput(alg1, N, T);
//        double t4 = timeOrderedInput(alg2, N, T);
//        StdOut.printf("For %d ordered Integers\n    %s is", N, alg1);
//        StdOut.printf(" %.1f times faster than %s\n", t4 / t3, alg2);
    }
}
