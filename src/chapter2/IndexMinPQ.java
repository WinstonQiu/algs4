package chapter2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> {
    private int maxN;
    private int N;
    private int[] pq;
    private int[] qp;
    private Key[] keys;

    // 创建一个最大容量为maxN的优先队列，索引的取值范围为0至maxN-1
    public IndexMinPQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        N = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) qp[i] = -1;
    }

    // 插入一个元素，将它和索引k相关联
    public void insert(int k, Key key) {
        validateIndex(k);
        if (contains(k)) throw new IllegalArgumentException("index is already in the priority queue");
        N++;
        qp[k] = N;
        pq[N] = k;
        keys[k] = key;
        swim(N);
    }

    // 将索引为k的元素设为item
    public void change(int k, Key key) {
        validateIndex(k);
        if (!contains(k)) throw new NoSuchElementException("index is not in the priority queue");
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    // 是否存在索引为k的元素
    public boolean contains(int k) {
        validateIndex(k);
        return qp[k] != -1;
    }

    // 删去索引k及其相关联的元素
    public void delete(int k) {
        validateIndex(k);
        if (!contains(k)) throw new NoSuchElementException("index is not in the priority queue");
        int index = qp[k];
        exch(index, N--);
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
    }

    // 返回最小元素
    public Key min() {
        if (N == 0) throw new NoSuchElementException("Priority queue underflow");
        return keys[pq[1]];
    }

    // 返回最小元素的索引
    public int minIndex() {
        if (N == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    // 删除最小元素并返回它的索引
    public int delMin() {
        if (N == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[1];
        exch(1, N--);
        sink(1);
        qp[min] = -1;
        keys[min] = null;
        pq[N + 1] = -1;
        return min;
    }

    // 优先队列是否为空
    public boolean isEmpty() {
        return N == 0;
    }

    // 优先队列中的元素数量
    public int size() {
        return N;
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void validateIndex(int k) {
        if (k < 0) throw new IllegalArgumentException("index is negative: " + k);
        if (k >= maxN) throw new IllegalArgumentException("index >= capacity: " + k);
    }

    public static void merge(In[] streams) {
        int N = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<>(N);

        for (int i = 0; i < N; i++) {
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }

        while (!pq.isEmpty()) {
            StdOut.println(pq.min());
            int i = pq.delMin();
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }
    }

    public static void main(String[] args) {
        int N = args.length;
        In[] streams = new In[N];
        for (int i = 0; i < N; i++) {
            streams[i] = new In(args[i]);
        }
        merge(streams);
    }
}
