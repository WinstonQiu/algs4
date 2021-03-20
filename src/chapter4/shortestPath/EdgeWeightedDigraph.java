package chapter4.shortestPath;

import chapter1.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph {
    private final int V;            // 顶点总数
    private int E;                  // 边的总数
    private Bag<DirectedEdge>[] adj;// 邻接表

    // 含有V个顶点的空加权有向图
    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; ++v) {
            adj[v] = new Bag<>();
        }
    }

    // 从输入流中读取图的构造函数
    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int e = 0; e < E; ++e) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            addEdge(new DirectedEdge(v, w, weight));
        }
    }

    // 顶点总数
    public int V() {
        return V;
    }

    // 边的总数
    public int E() {
        return E;
    }

    // 将e添加到该加权有向图中
    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        ++E;
    }

    // 从v指出的边
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    // 该加权有向图的所有边
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> edges = new Bag<>();
        for (int v = 0; v < V; ++v) {
            for (DirectedEdge e : adj[v]) {
                edges.add(e);
            }
        }
        return edges;
    }
}
