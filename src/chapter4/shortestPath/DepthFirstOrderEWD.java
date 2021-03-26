package chapter4.shortestPath;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DepthFirstOrderEWD {
    private final boolean[] marked;
    private final Queue<Integer> pre;         // 前序排列
    private final Queue<Integer> post;        // 后序排列
    private final Deque<Integer> reversePost; // 逆后序排列

    public DepthFirstOrderEWD(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new LinkedList<>();

        for (int v = 0; v < G.V(); ++v) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        pre.add(v);

        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
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
