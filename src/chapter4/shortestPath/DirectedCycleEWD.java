package chapter4.shortestPath;

import java.util.Deque;
import java.util.LinkedList;

public class DirectedCycleEWD {
    private final boolean[] marked;
    private final int[] edgeTo;
    private Deque<Integer> cycle;   // 有向环中的所有顶点（如果存在）
    private final boolean[] onStack;      // 递归调用的栈上的所有顶点

    public DirectedCycleEWD(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); ++v) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new LinkedList<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    // G是否含有有向环
    public boolean hasCycle() {
        return cycle != null;
    }

    // 有向环中的所有顶点（如果存在）
    public Iterable<Integer> cycle() {
        return cycle;
    }
}
