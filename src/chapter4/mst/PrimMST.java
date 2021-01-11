package chapter4.mst;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class PrimMST implements MST{
    public PrimMST(EdgeWeightedGraph G) {

    }

    @Override
    public Iterable<Edge> edges() {
        return null;
    }

    @Override
    public double weight() {
        return 0;
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
