package chapter4.directed;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;         // 前序排列
    private Queue<Integer> post;        // 后序排列
    private Deque<Integer> reversePost; // 逆后序排列

    public DepthFirstOrder(Digraph G) {
        marked = new boolean[G.V()];
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new LinkedList<>();

        for (int v = 0; v < G.V(); ++v) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        pre.add(v);

        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }

        post.add(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
