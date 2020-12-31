package chapter3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class LinearProbingHashST<Key, Value> implements HashST<Key, Value> {

    private int N = 0;      // 键值对总数
    private int M;          // 线性探测表的大小
    private Key[] keys;     // 键
    private Value[] vals;   // 值

    public LinearProbingHashST() {
        this(16);
    }

    public LinearProbingHashST(int cap) {
        M = cap;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    @Override
    public void put(Key key, Value val) {
        if (N >= M / 2) resize(2 * M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }

        keys[i] = key;
        vals[i] = val;
        ++N;
    }

    @Override
    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) return vals[i];
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i])) i = (i + 1) % M;
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;

        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            --N;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        --N;
        if (N > 0 && N == M / 8) resize(M / 2);
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<Key> keys() {
        return () -> new Iterator<Key>() {
            private int ptr = 0;

            {
                while (ptr < M && keys[ptr] == null) ++ptr;
            }

            @Override
            public boolean hasNext() {
                return ptr < M && keys[ptr] != null;
            }

            @Override
            public Key next() {
                Key retKey = keys[ptr++];
                while (ptr < M && keys[ptr] == null) ++ptr;
                return retKey;
            }
        };
    }

    public static void main(String[] args) {
        if (args.length < 1) System.exit(1);

        HashST<String, Integer> st = new LinearProbingHashST<>();
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
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(cap);
        for (int i = 0; i < M; ++i) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }

        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

}
