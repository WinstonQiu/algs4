package chapter4.undirected;

import chapter1.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {
    private final int V;  // 顶点数
    private int E;    // 边数
    private Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; ++v) {
            adj[v] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int e = 0; e < E; ++e) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    // 向图中添加一条边v-w
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        ++E;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V).append(" vertices, ").append(E).append(" edges\n");
        for (int v = 0; v < V; ++v) {
            sb.append(v).append(": ");
            for (int w : adj(v)) {
                sb.append(w).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // 计算v的度数
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) ++degree;
        return degree;
    }

    // 计算所有顶点的最大度数
    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); ++v) {
            int d;
            if ((d = degree(G, v)) > max) max = d;
        }
        return max;
    }

    // 计算所有顶点的平均度数
    public static double avgDegree(Graph G) {
        return 2.0 * G.E() / G.V();
    }

    // 计算自环的个数
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); ++v) {
            for (int w : G.adj(v)) {
                if (v == w) ++count;
            }
        }
        return count / 2;
    }


}
