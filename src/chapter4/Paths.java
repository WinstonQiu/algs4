package chapter4;

import java.util.Deque;
import java.util.LinkedList;

public abstract class Paths {
    protected boolean[] marked;   // 对应顶点是否调用过dfs()
    protected int[] edgeTo;       // 从起点到一个顶点的已知路径上的最后一个顶点
    protected final int s;        // 起点

    protected Paths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
    }

    // 是否存在从s到v的路径
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // s到v的路径，如果不存在则返回null
    Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Deque<Integer> path = new LinkedList<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
