package chapter3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> extends ST<Key, Value>{
    private Key[] keys;
    private Value[] vals;

    // 无初始容量的有序符号表构造函数
    public BinarySearchST() {
        keys = (Key[]) new Comparable[8];
        vals = (Value[]) new Object[8];
    }

    // 带初始容量的有序符号表构造函数
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    // 将键值对存入表中，O(N)
    @Override
    public void put(Key key, Value val) {
        int i = rank(key); // 查找键
        if (i < N && keys[i].compareTo(key) == 0) { // 找到则更新值
            vals[i] = val;
        } else {
            if (N == keys.length) { // 扩大容量
                resize(2 * N);
            }
            for (int j = N; j > i; j--) { // 更大的键向后移动一格
                keys[j] = keys[j - 1];
                vals[j] = vals[j - 1];
            }// 插入新的值
            keys[i] = key;
            vals[i] = val;
            N++;
        }
    }

    // 获取指定键对应的值，若不存在返回null，O(logN)
    @Override
    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else                                      return null;
    }

    // 从表中删除指定键，O(N)
    @Override
    public void delete(Key key) {
        int i = rank(key); // 查找键
        if (i < N && keys[i].compareTo(key) == 0) { // 找到则删除键值
            for (int j = i; j < N - 1; j++) { // 更大的键向前移动一格
                keys[j] = keys[j + 1];
                vals[j] = vals[j + 1];
            }
            // 删除键值
            N--;
            keys[N] = null;
            vals[N] = null;
        }
        if (N > 0 && N == keys.length / 4) resize(N / 2);
    }

    // 最小键，O(1)
    @Override
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("Called min() with empty symbol table!");
        }
        return keys[0];
    }

    // 最大键，O(1)
    @Override
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Called max() with empty symbol table!");
        }
        return keys[N - 1];
    }

    // 小于等于指定键的最大键，O(logN)
    @Override
    public Key floor(Key key) {
        int i = rank(key); // 查找键
        if (i < size() && keys[i].compareTo(key) == 0) {
            return keys[i]; // 找到等于的键返回
        }
        if (i == 0) return null; // 没有更小的键，返回null
        else return keys[i - 1]; // 返回小于的键
    }

    // 大于等于指定键的最小键，O(logN)
    @Override
    public Key ceiling(Key key) {
        int i = rank(key); // 查找键
        if (i == N) return null; // 超出表的范围，返回null
        else return keys[i]; // 返回小于等于的键
    }

    // 二分查找，返回小于被查找键的键的数量，O(logN)
    @Override
    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else              return mid;
        }
        return lo;
    }

    // 排名为k的键(从0开始)，O(1)
    @Override
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("Called select() with invalid argument: " + k);
        }
        return keys[k];
    }

    // 指定范围内所有键，已排序
    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new LinkedList<>(Arrays.asList(keys).subList(rank(lo), rank(hi)));
        if (contains(hi)) q.add(keys[rank(hi)]);
        return q;
    }

    // 调整数组大小，O(N)
    private void resize(int max) {
        Key[] keyTemp = (Key[]) new Comparable[max];
        Value[] valTemp = (Value[]) new Comparable[max];
        for (int i = 0; i < N; i++) {
            keyTemp[i] = keys[i];
            valTemp[i] = vals[i];
        }
        keys = keyTemp;
        vals = valTemp;
    }

    public static void main(String[] args) {
        ST<String, Integer> st;
        st = new BinarySearchST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
