package chapter4.directed;

import chapter1.Bag;
import edu.princeton.cs.algs4.In;

public class Digraph {
    private final int V;  // 顶点数
    private int E;    // 边数
    private Bag<Integer>[] adj;

    // 创建一幅含有V个顶点但没有边的有向图
    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; ++v) {
            adj[v] = new Bag<Integer>();
        }
    }

    // 从输入流in中读取一幅有向图
    public Digraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int e = 0; e < E; ++e) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
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

    // 向有向图中添加一条边v->w
    public void addEdge(int v, int w) {
        adj[v].add(w);
        ++E;
    }

    // 由v指出的边所连接的所有顶点
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // 该图的反向图
    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; ++v) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
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
}
