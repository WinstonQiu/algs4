package chapter4.directed;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Topological {
    private Iterable<Integer> order;    // 顶点的拓扑顺序

    public Topological(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
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
        Digraph G = new Digraph(new In(args[0]));
        Topological top = new Topological(G);
        for (int v : top.order()) {
            StdOut.print(v + " ");
        }
        StdOut.println();
    }
}
