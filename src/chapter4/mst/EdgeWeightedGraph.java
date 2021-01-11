package chapter4.mst;

import chapter1.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph {
    private final int V;        // 顶点总数
    private int E;              // 边的总数
    private Bag<Edge>[] adj;    // 邻接表

    // 创建一幅含有V个顶点的空图
    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; ++v) {
            adj[v] = new Bag<Edge>();
        }
    }

    // 从输入流中读取图
    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int e = 0; e < E; ++e) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            addEdge(new Edge(v, w, weight));
        }
    }

    // 图的顶点数
    public int V() {
        return V;
    }

    // 图的边数
    public int E() {
        return E;
    }

    // 向图中添加一条边e
    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        ++E;
    }

    // 和v相关联的所有边
    Iterable<Edge> adj(int v) {
        return adj[v];
    }

    // 图的所有边
    Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<>();
        for (int v = 0; v < V; ++v) {
            for (Edge e : adj(v)) {
                if (e.other(v) > v) b.add(e);
            }
        }
        return b;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V).append(" vertices, ").append(E).append(" edges\n");
        for (int v = 0; v < V; ++v) {
            sb.append(v).append(": ");
            for (Edge e : adj(v)) {
                sb.append(e).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
