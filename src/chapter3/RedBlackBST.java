package chapter3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Queue;

public class RedBlackBST<Key extends Comparable<Key>, Value> extends ST<Key, Value>{
    private static final boolean RED   = true;  // 红
    private static final boolean BLACK = false; // 黑

    private Node root;

    private class Node {
        private Key key;            // 键
        private Value val;          // 值
        private Node left, right;   // 左右子树
        private int N;              // 子树中的结点总数
        private boolean color;      // 由其父节点指向它的链接的颜色

        public Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    // 测试结点与其父节点之间链接的颜色
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    // 左旋转，输入为较小的结点，输出为较大的结点
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    // 右旋转，和左旋转相反，将Left和Right互换
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    // 子结点由红变黑，父结点由黑变红
    // 相当于临时4-结点变为两个2-结点，并将中键转移到父结点
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else           return x.N;
    }

    @Override
    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)      x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }

    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)      x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }

    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) { // 标准的插入操作，和父结点用红链接相连
            return new Node(key, val, 1, RED);
        }
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;

        // 红黑树多出的三条语句
        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    @Override
    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    @Override
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0)  return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else           return x;
    }

    @Override
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0)  return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else           return x;
    }

    @Override
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if      (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else            return x;
    }

    @Override
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return 1 + size(x.left) + rank(x.right, key);
        else              return size(x.left);
    }

    private void moveFlipColors(Node h) {
        h.color = BLACK;
        h.left.color = RED;
        h.right.color = RED;
    }

    private Node balance(Node x) {
        if (isRed(x.right)) x = rotateLeft(x);

        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // 假设结点h为红色，h.left和h.left.left都是黑色
    // 将h.left或h.left的子结点之一变红
    private Node moveRedLeft(Node h) {
        moveFlipColors(h); // 从父结点借一个结点，变为4-结点
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    @Override
    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return null;
        if (!isRed(x.left) && !isRed(x.left.left)) // 判断左子结点是不是2-结点
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return balance(x);
    }

    @Override
    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node x, Key key) {
        if (key.compareTo(x.key) < 0) {
            if (!isRed(x.left) && !isRed(x.left.left)) x = moveRedLeft(x);
            x.left = delete(x.left, key);
        } else {
            if (isRed(x.left)) x = rotateRight(x);
            if (key.compareTo(x.key) == 0 && (x.right == null)) return null;
            if (!isRed(x.right) && !isRed(x.right.left)) x = moveRedRight(x);
            if (key.compareTo(x.key) == 0) {
                x.val = get(x.right, min(x.right).key);
                x.key = min(x.right).key;
                x.right = deleteMin(x.right);
            }
            else x.right = delete(x.right, key);
        }
        return balance(x);
    }

    // 假设结点h为红色，h.right和h.right.left都是黑色
    // 将h.right或h.right的子结点之一变红
    private Node moveRedRight(Node h) {
        moveFlipColors(h); // 从父结点借一个结点，变为4-结点
        if (!isRed(h.left.left)) {
            h = rotateRight(h);
        }
        return h;
    }

    @Override
    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node x) {
        if (isRed(x.left)) x = rotateRight(x);
        if (x.right == null) return null;
        if (!isRed(x.right) && !isRed(x.right.left)) x = moveRedRight(x);
        x.right = deleteMax(x.right);
        return balance(x);
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public static void main(String[] args) {
        if (args.length < 1) System.exit(1);

        ST<String, Integer> st = new RedBlackBST<>();
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
        for (int i = 0; !delIn.isEmpty(); i++) {
            String key = delIn.readString();
            st.delete(key);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
