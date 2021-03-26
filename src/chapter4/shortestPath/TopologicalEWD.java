package chapter4.shortestPath;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TopologicalEWD {
    private Iterable<Integer> order;    // 顶点的拓扑顺序

    public TopologicalEWD(EdgeWeightedDigraph G) {
        DirectedCycleEWD cycleFinder = new DirectedCycleEWD(G);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrderEWD dfs = new DepthFirstOrderEWD(G);
            order = dfs.reversePost();
        }
    }

    // G是否为有向无环图
    public boolean isDAG() {
        return order != null;
    }

    // 拓扑有序的所有顶点
    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
        TopologicalEWD top = new TopologicalEWD(G);
        for (int v : top.order()) {
            StdOut.print(v + " ");
        }
        StdOut.println();
    }
}
