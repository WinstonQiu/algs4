package chapter4.mst;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LazyPrimMST implements MST {
    private final boolean[] marked;  // 最小生成树的顶点
    private final Queue<Edge> mst;   // 最小生成树的边
    private final PriorityQueue<Edge> pq; // 横切边，Java默认小根堆
    private double weight = 0;

    public LazyPrimMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        mst = new LinkedList<>();
        pq = new PriorityQueue<>();
        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.remove();                   // 从pq中得到权重最小的边
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;   // 跳过失效的边
            mst.add(e);                             // 将边添加到树中
            weight += e.weight();                   // 增加权重
            if (!marked[v]) visit(G, v);            // 将顶点(v或w)添加到树中
            if (!marked[w]) visit(G, w);
        }
    }

    // 标记顶点v并将所有连接v和未被标记顶点的边加入pq
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) pq.add(e);
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        return weight;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        MST mst = new LazyPrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
