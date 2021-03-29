package chapter4.shortestPath;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BellmanFordSP {
    private final double[] distTo;          // 从起点到某个顶点的路径长度
    private final DirectedEdge[] edgeTo;    // 从起点到某个顶点的最后一条边
    private final boolean[] onQ;            // 该顶点是否处于队列之中
    private final Queue<Integer> queue;     // 正在被放松的顶点
    private int cost;                       // relax()的调用次数
    private Iterable<DirectedEdge> cycle;   // edgeTo[]中是否有负权重环

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new LinkedList<>();
        for (int v = 0; v < G.V(); ++v) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        queue.add(s);
        onQ[s] = true;

        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.remove();
            onQ[v] = false;
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.add(w);
                    onQ[w] = true;
                }
            }
            if (cost++ % G.V() == 0) {
                findNegativeCycle();
            }
        }
    }

    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (DirectedEdge e : edgeTo) {
            if (e != null) {
                spt.addEdge(e);
            }
        }
        DirectedCycleEWD cycleFinder = new DirectedCycleEWD(spt);
        cycle = cycleFinder.cycle();
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] > Double.NEGATIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Deque<DirectedEdge> path = new LinkedList<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}
