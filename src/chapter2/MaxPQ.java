package chapter2;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ() {
        pq = (Key[]) new Comparable[1];
    }

    public MaxPQ(int max) {
        pq = (Key[]) new Comparable[max + 1];
    }

    public MaxPQ(Key[] a) {
        pq = (Key[]) new Comparable[a.length + 1];
        for (Key v : a) {
            this.insert(v);
        }
    }

    public void insert(Key v) {
        if (N == pq.length - 1)
            resize(2 * N + 1);
        pq[++N] = v;
        swim(N);
    }

    public Key max() {
        return pq[1];
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        if (N > 0 && N == (pq.length - 1)/ 4)
            resize(pq.length / 2);
        return max;
    }

    public boolean isempty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void resize(int max) {
        Key[] temp = (Key[]) new Comparable[max + 1];
        System.arraycopy(pq, 1, temp, 1, N);
        pq = temp;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        MaxPQ<Transaction> pq = new MaxPQ<Transaction>();
        while (StdIn.hasNextLine()) {
            pq.insert(new Transaction(StdIn.readLine()));
            if(pq.size() > M)
                pq.delMax();
        }

        Stack<Transaction> stack = new Stack<Transaction>();
        while (!pq.isempty())
            stack.push(pq.delMax());
        for (Transaction t : stack)
            StdOut.println(t);


    }
}
