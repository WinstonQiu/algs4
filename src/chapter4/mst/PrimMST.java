package chapter4.mst;

import chapter1.Bag;
import chapter2.IndexMinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class PrimMST implements MST{
    private final Edge[] edgeTo;          // 距离树最近的边
    private final double[] distTo;        // distTo[w]=edgeTo[w].weight()
    private final boolean[] marked;       // 如果v在树中则为true
    private final IndexMinPQ<Double> pq;  // 有效的横切边

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); ++v) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(G.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);              // 用顶点0和权重0初始化pq
        while (!pq.isEmpty()) {
            visit(G, pq.delMin());          // 将最近的顶点添加到树中
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);

            if (marked[w]) continue;        // v-w失效
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        Bag<Edge> mst = new Bag<>();
        for (int v = 1; v < edgeTo.length; ++v) {
            mst.add(edgeTo[v]);
        }
        return mst;
    }

    @Override
    public double weight() {
        double weight = 0.0;
        for (double w : distTo) {
            weight += w;
        }
        return weight;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        MST mst = new PrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
