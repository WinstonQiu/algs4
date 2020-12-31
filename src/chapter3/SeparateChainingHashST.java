package chapter3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Queue;

public class SeparateChainingHashST<Key, Value> implements HashST<Key, Value> {
    private int N;  // 键值对总数
    private int M;  // 散列表的大小
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(4);
    }

    public SeparateChainingHashST(int cap) {
        M = cap;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; ++i) {
            st[i] = new SequentialSearchST<>();
        }
    }

    @Override
    public void put(Key key, Value val) {
        if (N >= 8*M) resize(2 * M);

        st[hash(key)].put(key, val);
        ++N;
    }

    @Override
    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    @Override
    public void delete(Key key) {
        if (st[hash(key)].delete(key)) --N;

        if (N > 0 && N <= 2 * M) resize(M / 2);
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        for (SequentialSearchST<Key, Value> s : st) {
            for (Key key : s.keys()) {
                queue.add(key);
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        if (args.length < 1) System.exit(1);

        HashST<String, Integer> st = new SeparateChainingHashST<>();
        In srchIn = new In(args[0]);

        for (int i = 0; !srchIn.isEmpty(); i++) {
            String key = srchIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }

        if (args.length < 2) return;
        StdOut.println("=====");
        In delIn = new In(args[1]);
        while (!delIn.isEmpty()) {
            String key = delIn.readString();
            st.delete(key);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        SeparateChainingHashST<Key, Value> t = new SeparateChainingHashST<>(cap);
        for (int i = 0; i < M; ++i) {
            for (Key key : st[i].keys()) {
                t.put(key, st[i].get(key));
            }
        }

        st = t.st;
        M = t.M;
        N = t.N;
    }
}
