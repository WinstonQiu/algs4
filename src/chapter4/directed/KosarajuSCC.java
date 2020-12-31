package chapter4.directed;

import chapter1.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                ++count;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    // v和w是否是强连通的
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    // 图中的强连通分量的总数
    public int count() {
        return count;
    }

    // v所在的强连通分量的标识符（在0~count()-1之间）
    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));
        KosarajuSCC scc = new KosarajuSCC(G);

        int M = scc.count();
        StdOut.println(M + " components");

        Bag<Integer>[] components = (Bag<Integer>[]) new Bag[M];
        for (int i = 0; i < M; ++i) {
            components[i] = new Bag<Integer>();
        }
        for (int v = 0; v < G.V(); ++v) {
            components[scc.id(v)].add(v);
        }
        for (int i = 0; i < M; ++i) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
